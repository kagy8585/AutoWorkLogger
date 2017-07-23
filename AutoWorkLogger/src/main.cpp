/*
 * main.cpp
 *
 *  Created on: Jul 23, 2017
 *      Author: kagy
 */

#include <iostream>
#include <stdexcept>


#include "LinuxActivityMonitorFactory.h"
#include "GroupActivityMonitor.h"
#include "ActivityLogger.h"

const std::string LOG_FILE="./autoworklog.log";

int main(void)
{
	try
	{
		//starting the logging
		LinuxActivityMonitorFactory factory;
		//creating a simp0le logger
		ActivityLogger logger(LOG_FILE, factory);

		logger.startLogging();
	}
	catch (std::exception & e)
	{
		std::cerr<<e.what();
	}

	return 0;
}

