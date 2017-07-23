
#ifndef ACTIVITYMONITOR_H
#define ACTIVITYMONITOR_H

#include <string>

/**
 * Bas class for all user activity monitors.
 */
class ActivityMonitor
{
public:

	/**
	 * Gets the last activity of the user on a given device.
	 * @return time_t is the time of the last known activity.
	 */
	virtual time_t getLastActivity() const=0;


	/**
	 * Starts the monitor on a bacground thread.
	 */
	virtual void start()=0;


	/**
	 * Stops the monitor
	 */
	virtual void stop()=0;

	/**
	 * Empty Destructor
	 */
	virtual ~ActivityMonitor() {}
};

#endif // ACTIVITYMONITOR_H
