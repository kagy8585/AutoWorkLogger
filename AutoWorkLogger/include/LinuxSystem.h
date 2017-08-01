/*
 * LinuxSystem.h
 *
 *  Created on: Jul 29, 2017
 *      Author: kagy
 */

#ifndef INCLUDE_LINUXSYSTEM_H_
#define INCLUDE_LINUXSYSTEM_H_

#if defined(__linux__)

#include "System.h"

#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/stat.h>


/**
 * All Linux dependent basic features are contained here.
 */
class LinuxSystem : public System {
public:

	/**
	 * The init function that should transform the current process into a daemon.
	 * @param workDir is the path of the working directory.
	 */
	virtual void init(const std::string workDir);

	/**
	 * Sleeps the current thread for a give number of seconds.
	 * @param seconds is the sleep period in seconds.
	 */
	virtual void sleepSeconds(const unsigned int seconds)
	{
		sleep(seconds);
	}

	/**
	 * Mandatory virtual destructor.
	 */
	virtual ~LinuxSystem() {}
};

#endif //defined(__linux__)

#endif /* INCLUDE_LINUXSYSTEM_H_ */
