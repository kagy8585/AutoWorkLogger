/*
 * System.h
 *
 *  Created on: Jul 29, 2017
 *      Author: kagy
 */

#ifndef INCLUDE_SYSTEM_H_
#define INCLUDE_SYSTEM_H_

#include <fstream>
#include <stdlib.h>

/**
 * Abstract class representing all operating system dependent tasks.
 */
class System;

class System {
public:
	/**
	 * Pointer to the single system in used set by other things.
	 */
	static System * pSystem;

	/**
	 * The log where all things should be printed.
	 */
	std::fstream log;

	/**
	 * Initializes the program for the system.
	 */
	virtual void init()=0;

	/**
	 * The mandatory virtual destructor.
	 */
	virtual ~System(){}

};



#endif /* INCLUDE_SYSTEM_H_ */
