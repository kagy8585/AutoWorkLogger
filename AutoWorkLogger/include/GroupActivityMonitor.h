
#ifndef GROUPACTIVITYMONITOR_H
#define GROUPACTIVITYMONITOR_H


#include "ActivityMonitor.h"

#include <string>
#include <vector>

/**
 * Creates an activity monitor, that monitors multiple
 * activity monitpors in a group.
 */
class GroupActivityMonitor : public ActivityMonitor
{
private:
	// The vector of activity monitors in the composite.
	std::vector<ActivityMonitor *> monitors;
	//Determines if the monitor is running
	volatile bool isRunning;

public:
	/**
	 * Creates a new empty group activity monitor.
	 */
	GroupActivityMonitor ();

	/**
	 * Adds a new monitor to the group.
	 * When the group activity monitor is deleted, all the member variables are deleted.
	 * @param pMonitor pointer to the new monitor.
	 */
	inline void addMonitor (ActivityMonitor * pMonitor)
	{
		monitors.push_back(pMonitor);
	}

	/**
	 * Starts the activity monitor.
	 */
	virtual void start();

	/**
	 * Stops the activity monitor.
	 */
	virtual void stop();

	/**
	 * Get the value of lastActiveTime
	 * @return the value of lastActiveTime
	 */
	virtual time_t getLastActivity() const;

	/**
	 * Deletes all the member monitors.
	 */
	virtual ~GroupActivityMonitor();

};

#endif // GROUPACTIVITYMONITOR_H
