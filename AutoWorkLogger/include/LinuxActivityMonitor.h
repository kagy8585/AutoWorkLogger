
#ifndef LINUXACTIVITYMONITOR_H
#define LINUXACTIVITYMONITOR_H

#include "ActivityMonitor.h"

#include <string>
#include <stdexcept>
#include <pthread.h>

#include <assert.h>
#include <time.h>
#include <linux/input.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>
#include <pthread.h>

/**
 * Linux filed descriptor based activity monitor.
 */
class LinuxActivityMonitor : public ActivityMonitor
{
private:

	//The background thread
	pthread_t thread;
	// File descriptor used to access the input device.
	int fd;
	//the last time user activity was detected
	volatile time_t lastActiveTime;
	//determines if the background thread is running.
	volatile bool isRunning;

public:

	/**
	 * Creates a linux activity monitor based on input devices.
	 * @param  inputDevice The linux input device used to monitor the activity.
	 */
	LinuxActivityMonitor (std::string & inputDevice);

	/**
	 * Starts the activity monitor.
	 */
	virtual void start();

	/**
	 * Stops the activity monitor.
	 */
	virtual void stop();

	/**
	 * Background method that runs on a diferent thread.
	 * @param vp is a void pointer as argument.
	 * @return is a void pointer to the result.
	 */
	friend void * ::backgroundMethod(void * vp);


	/**
	 * Get the value of lastActiveTime
	 * @return the value of lastActiveTime
	 */
	virtual inline time_t getLastActivity () const
	{
		return lastActiveTime;
	}

	/**
	 * Destructor.
	 */
	virtual ~LinuxActivityMonitor();

};

#endif // LINUXACTIVITYMONITOR_H
