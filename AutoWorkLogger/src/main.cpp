/*
 * main.cpp
 *
 *  Created on: Jul 23, 2017
 *      Author: kagy
 */

#include <stdexcept>
#include <fstream>
#include <stdlib.h>

#include "System.h"
#include <LinuxSystem.h>
#include "LinuxActivityMonitorFactory.h"
#include "GroupActivityMonitor.h"
#include "ActivityLogger.h"

//The location of the log file.
const std::string LOG_FILE="./autoworklog.log";

//Error code for situations when the log file can not be opened, and
//therefore the error messages can not be written in the log file.
const int LOG_ERROR_CODE=-99;

int main(int argc, char ** argv)
{
	if (argc!=2)
	{
		std::cout << "Usage "<<argv[0] << " <working directory path>\n";
		exit(0);
	}
	else
	{
		LinuxSystem sys;

		//initializing the system
		sys.init(std::string(argv[1]));
		//opening the log file if possible
		//in append mode
		sys.log.open(LOG_FILE.c_str(), std::ios_base::app | std::ios_base::out);

		if (sys.log.is_open())
		{
			try
			{
				//starting the logging
				LinuxActivityMonitorFactory factory;
				//creating a simp0le logger
				ActivityLogger logger(sys.log, factory);

				logger.startLogging();
			}
			catch (std::exception & e)
			{
				sys.log<<e.what();
				return -1;
			}
			return 0;
		}
		else
			return LOG_ERROR_CODE;
	}
}

