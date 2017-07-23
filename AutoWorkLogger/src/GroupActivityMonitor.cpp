#include "GroupActivityMonitor.h"

/**
 * Creates a new empty group activity monitor.
 */
GroupActivityMonitor::GroupActivityMonitor()
{
	isRunning=false;
}

/**
 * Starts the activity monitor.
 */
void GroupActivityMonitor::start()
{
	if (!isRunning)
	{
		isRunning=true;
		for (unsigned int i=0; i<monitors.size(); i++)
			monitors[i]->start();
	}
}

/**
 * Stops the activity monitor.
 */
void GroupActivityMonitor::stop()
{
	if (isRunning)
	{
		isRunning=false;
		for (unsigned int i=0; i<monitors.size(); i++)
			monitors[i]->stop();
	}
}

/**
 * Get the value of lastActiveTime.
 * @return the value of lastActiveTime.
 */
time_t GroupActivityMonitor::getLastActivity() const
{
	time_t maxTime=0, t;

	//getting the maximum time
	for (unsigned int i=0; i<monitors.size(); i++)
	{
		t=monitors[i]->getLastActivity();
		if (t>maxTime)
			maxTime=t;
	}
	return maxTime;
}

/**
 * Deletes all the member monitors.
 */
GroupActivityMonitor::~GroupActivityMonitor()
{
	ActivityMonitor * pMonitor;

	//deleting the existing monitors
	for (unsigned int i=0; i<monitors.size(); i++)
	{
		pMonitor=monitors[i];
		if (pMonitor!=NULL)
			delete pMonitor;
	}
	monitors.clear();
}
