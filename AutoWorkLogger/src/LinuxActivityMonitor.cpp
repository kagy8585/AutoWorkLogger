#include "LinuxActivityMonitor.h"

/**
 * Creates a Linux activity monitor based on input devices.
 * @param  inputDevice The linux input device used to monitor the activity.
 */
LinuxActivityMonitor::LinuxActivityMonitor (std::string & inputDevice)
{
	//the thread is not running
	thread=0;
	isRunning=false;
	//saving the time as the last active time.
	lastActiveTime=time(NULL);
	fd=open(inputDevice.c_str(), O_RDONLY);
	//testing the input device.
	if (fd<0)
		throw std::invalid_argument("Invalid input device \""+inputDevice+"\".\n");
}



/**
 * Background method that runs on a diferent thread.
 * @param vp is a void pointer as argument.
 * @return is a void pointer to the result.
 */
void * backgroundMethod(void * vp)
{	//geting the class
	LinuxActivityMonitor * pMonitor=(LinuxActivityMonitor*)(vp);
	struct input_event event;
	int r;

	assert(pMonitor!=NULL);
	while (pMonitor->isRunning)
	{
		//blocking until an event is read
		r=read(pMonitor->fd, &event, sizeof(struct input_event));
		//saving the time of the last change
		if (r>0)
			pMonitor->lastActiveTime=time(NULL);
	}
	return NULL;
}

/**
 * Starts the activity logger.
 */
void LinuxActivityMonitor::start()
{
	if (!isRunning)
	{
		//testing that the file descriptor is valid
		if (fd<0)
			throw std::logic_error("Can not start activity monitor twice!");
		isRunning=true;
		if (pthread_create(&thread, NULL, backgroundMethod, this)!=0)
		{
			isRunning=false;
			throw std::logic_error("Can not create background thread!");
		}
	}
}

/**
 * Stops the activity logger.
 */
void LinuxActivityMonitor::stop()
{
	if (isRunning)
	{
		isRunning=false;
		//closing the input file, in order to stop the read
		if (fd>0)
			close(fd);
		fd=-1;
		//joining the thread and waiting its return value
		pthread_join(thread, NULL);
		thread=0;
	}
}

/**
 * Destructor.
 */
LinuxActivityMonitor::~LinuxActivityMonitor()
{
	if (isRunning)
		stop();
}

