
#ifndef LINUXACTIVITYMONITOR_H
#define LINUXACTIVITYMONITOR_H
#include "ActivityMonitor.h"

#include <string>
class LinuxActivityMonitor : public ActivityMonitor
{
public:

	// Constructors/Destructors
	//  


	/**
	 * Empty Constructor
	 */
	LinuxActivityMonitor ();

	/**
	 * Empty Destructor
	 */
	virtual ~LinuxActivityMonitor ();



	/**
	 * @param  inputDevice The linux input device used to monitor the activity.
	 */
	 LinuxActivityMonitor (string & inputDevice)
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

	// File descriptor used to access the inout device.
	int fd;
	time_t lastActiveTime;
public:

private:

public:


	// Private attribute accessor methods
	//  


	/**
	 * Set the value of fd
	 * File descriptor used to access the inout device.
	 * @param new_var the new value of fd
	 */
	void setFd (int new_var)	 {
			fd = new_var;
	}

	/**
	 * Get the value of fd
	 * File descriptor used to access the inout device.
	 * @return the value of fd
	 */
	int getFd ()	 {
		return fd;
	}

	/**
	 * Set the value of lastActiveTime
	 * @param new_var the new value of lastActiveTime
	 */
	void setLastActiveTime (time_t new_var)	 {
			lastActiveTime = new_var;
	}

	/**
	 * Get the value of lastActiveTime
	 * @return the value of lastActiveTime
	 */
	time_t getLastActiveTime ()	 {
		return lastActiveTime;
	}
private:


	void initAttributes () ;

};

#endif // LINUXACTIVITYMONITOR_H
