
#ifndef GROUPACTIVITYMONITOR_H
#define GROUPACTIVITYMONITOR_H
#include "ActivityMonitor.h"
#include "ActivityMonitor.h"

#include <string>
#include vector


class GroupActivityMonitor : public ActivityMonitor, public ActivityMonitor
{
public:

	// Constructors/Destructors
	//  


	/**
	 * Empty Constructor
	 */
	GroupActivityMonitor ();

	/**
	 * Empty Destructor
	 */
	virtual ~GroupActivityMonitor ();



	/**
	 * @param  pMonitor
	 */
	void addMonitor (ActivityMonitor * pMonitor)
	{
	}

protected:

public:

protected:

public:

protected:


private:

	// Private attributes
	//  

	// The vector of activity monitorsin the composion.
	vector<ActivityMonitor *> monitors;
public:

private:

public:


	// Private attribute accessor methods
	//  


	/**
	 * Set the value of monitors
	 * The vector of activity monitorsin the composion.
	 * @param new_var the new value of monitors
	 */
	void setMonitors (vector<ActivityMonitor *> new_var)	 {
			monitors = new_var;
	}

	/**
	 * Get the value of monitors
	 * The vector of activity monitorsin the composion.
	 * @return the value of monitors
	 */
	vector<ActivityMonitor *> getMonitors ()	 {
		return monitors;
	}
private:


	void initAttributes () ;

};

#endif // GROUPACTIVITYMONITOR_H
