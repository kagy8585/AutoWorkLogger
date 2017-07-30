/*
 * LinuxSystem.h
 *
 *  Created on: Jul 29, 2017
 *      Author: kagy
 */

#ifndef INCLUDE_LINUXSYSTEM_H_
#define INCLUDE_LINUXSYSTEM_H_

#include "System.h"

/**
 * All Linux dependent basic features are contained here.
 */
class LinuxSystem : public System {
public:

	/**
	 * The init function that should transform the current process into a daemon.
	 */
	virtual void init();

	/**
	 * Mandatory virtual destructor.
	 */
	virtual ~LinuxSystem() {}
};



#endif /* INCLUDE_LINUXSYSTEM_H_ */
