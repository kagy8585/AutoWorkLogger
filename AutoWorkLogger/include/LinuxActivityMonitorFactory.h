
#ifndef LINUXACTIVITYMONITORFACTORY_H
#define LINUXACTIVITYMONITORFACTORY_H

#include "ActivityMonitorFactory.h"
#include "LinuxActivityMonitor.h"
#include "GroupActivityMonitor.h"
#include "System.h"


#include <string>
#include <iostream>
#include <stdexcept>
#include <vector>

#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <dirent.h>



/**
 * Full activity monitor for Linux.
 * It listens to all input devices, including keyboard and mouse.
 */
class LinuxActivityMonitorFactory : public ActivityMonitorFactory
{
private:
	/**
	 * Path to the Linux input devices.
	 */
	static const std::string INPUT_DEVICE_PATH;

	/**
	 * Collects the Linux input devices.
	 * @return a vector containing all input devices.
	 */
	std::vector<std::string> static collectInputDevices();
public:
	/**
	 * Basic Linux activity monitor.
	 */
	LinuxActivityMonitorFactory () {}


	/**
	 * Creates a full activity monitor, that listens to all input devices.
	 * @return ActivityMonitor * is the new allocated activity monitor.
	 * This pointer should be deallocated later.
	 */
	virtual ActivityMonitor * createActivityMonitor() const;


	/**
	 * Empty Destructor
	 */
	virtual ~LinuxActivityMonitorFactory () {}
};

#endif // LINUXACTIVITYMONITORFACTORY_H
