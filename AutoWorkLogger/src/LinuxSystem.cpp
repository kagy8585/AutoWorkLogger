/*
 * LinuxSystem.cpp
 *
 *  Created on: Jul 30, 2017
 *      Author: kagy
 */

#include "LinuxSystem.h"

/**
 * The init function that should transform the current process into a daemon.
 */
void LinuxSystem::init()
{
	System::pSystem=this;
}


