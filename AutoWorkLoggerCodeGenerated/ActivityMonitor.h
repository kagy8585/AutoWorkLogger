
#ifndef ACTIVITYMONITOR_H
#define ACTIVITYMONITOR_H

#include <string>
#include vector


class ActivityMonitor
{
public:

	// Constructors/Destructors
	//  


	/**
	 * Empty Constructor
	 */
	ActivityMonitor ();

	/**
	 * Empty Destructor
	 */
	virtual ~ActivityMonitor ();



	/**
	 * Gets the last activity of the user on a given device.
	 * @return time_t
	 */
	time_t getLastActivity ()
	{
	}


	/**
	 * Starts the monitor on a bacground thread.
	 */
	void start ()
	{
	}


	/**
	 * Stops the monitor
	 */
	void stop ()
	{
	}

protected:

public:

protected:

public:

protected:


private:

public:

private:

public:

private:



};

#endif // ACTIVITYMONITOR_H
