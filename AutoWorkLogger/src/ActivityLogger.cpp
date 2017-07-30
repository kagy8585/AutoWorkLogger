#include "ActivityLogger.h"

/**
 * Keyword that determines when the user is active.
 */
const std::string ActivityLogger::ACTIVE_KEYWORD="active";

/**
 * Creates a new activity logger.
 * @param  logFilePath The file path to the activity log. If the activity log does
 * not exist, it is created.
 * @param  monitorFactory The factory used to create the activity monitor for the
 * logger.
 */
ActivityLogger::ActivityLogger(std::ostream & logStream, const ActivityMonitorFactory & monitorFactory)
{
	pLog=&logStream;
	pMonitor=monitorFactory.createActivityMonitor();
	if (pMonitor==NULL)
		throw std::logic_error("The activity monitor can not be created!");
	canRun=false;
}

/**
 * Starts the data logging on the main thread.
 */
void ActivityLogger::startLogging()
{
	if (!canRun)
	{
		time_t curentTime, lastActiveTime;

		canRun=true;
		assert(pMonitor);
		pMonitor->start();
		while (canRun)
		{
			time(&curentTime);
			lastActiveTime=pMonitor->getLastActivity();
			//check that the user is active
			if (difftime(curentTime, lastActiveTime)<PERIOD_S)
			{
				//printing the last activity time to the log file.
				//ctime puts \n symbol, no need to put it explicitly
				(*pLog) << ACTIVE_KEYWORD << " " << ctime(&lastActiveTime) << std::flush;
			}
			//wait for the las period
			sleep(PERIOD_S);
		}
	}
}


/**
 * Stops the logging. This method should be called from an other thread.
 */
void ActivityLogger::stopLogging()
{
	assert(pMonitor);
	pMonitor->stop();
	canRun=false;
}

/**
 * Destructor that closes the output file at the end.
 */
ActivityLogger::~ActivityLogger()
{
	if (pMonitor!=NULL)
	{
		delete pMonitor;
		pMonitor=NULL;
	}

}
