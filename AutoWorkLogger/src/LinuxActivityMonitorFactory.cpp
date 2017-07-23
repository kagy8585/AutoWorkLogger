#include "LinuxActivityMonitorFactory.h"

/**
 * Path to the Linux input devices.
 */
const std::string LinuxActivityMonitorFactory::INPUT_DEVICE_PATH="/dev/input";

/**
 * Collects the Linux input devices.
 * @return a vector containing all input devices.
 */
std::vector<std::string> LinuxActivityMonitorFactory::collectInputDevices()
{
	std::vector<std::string> devices;

	DIR * pd;
	struct dirent * pDirent;
	struct stat st;
	std::string devicePath, deviceName;
	int r;

	//opening the devices directory
	pd=opendir(INPUT_DEVICE_PATH.c_str());
	if (pd==NULL)
		throw std::logic_error("Can not open \""+INPUT_DEVICE_PATH+"\"!");
	//reading the entries form the devices directory
	pDirent=readdir(pd);
	while (pDirent!=NULL)
	{
		deviceName=std::string(pDirent->d_name);
		//testing the device name
		if (deviceName!="." && deviceName!="..")
		{
			devicePath=INPUT_DEVICE_PATH+"/"+deviceName;
			r=lstat(devicePath.c_str(), &st);
			if (r<0)
				std::cerr<<"Can not open input device \""<<devicePath<<"\"!\n";
			else
			{
				//testing that it is not a directory
				if (!S_ISDIR(st.st_mode))
					devices.push_back(devicePath);
			}
		}
		//reading the entries from the devices folder one by one
		pDirent=readdir(pd);
	}
	closedir(pd);
	return devices;
}

/**
 * Creates a full activity monitor, that listens to all input devices.
 * @return ActivityMonitor * is the new allocated activity monitor.
 * This pointer should be deallocated later.
 */
ActivityMonitor * LinuxActivityMonitorFactory::createActivityMonitor() const
{
	std::vector<std::string> devices=collectInputDevices();
	GroupActivityMonitor * pGroupMonitor=new GroupActivityMonitor();
	LinuxActivityMonitor * pMonitor;

	if (pGroupMonitor==NULL)
		throw std::logic_error("Out of memory!");
	//creating a linux activity monitor for each device
	for (unsigned int i=0; i<devices.size(); i++)
	{
		pMonitor=new LinuxActivityMonitor(devices[i]);
		if (pMonitor==NULL)
			throw std::logic_error("Out of memory!");
		pGroupMonitor->addMonitor(pMonitor);
	}

	return pGroupMonitor;
}
