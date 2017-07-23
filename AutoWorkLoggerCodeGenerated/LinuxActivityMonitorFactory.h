
#ifndef LINUXACTIVITYMONITORFACTORY_H
#define LINUXACTIVITYMONITORFACTORY_H
#include "ActivityMonitorFactory.h"

#include <string>
class LinuxActivityMonitorFactory : virtual public ActivityMonitorFactory
{
public:

	// Constructors/Destructors
	//  


	/**
	 * Empty Constructor
	 */
	LinuxActivityMonitorFactory ();

	/**
	 * Empty Destructor
	 */
	virtual ~LinuxActivityMonitorFactory ();



	/**
	 * @return ActivityMonitor *
	 */
	ActivityMonitor * createActivityMonitor ()
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

#endif // LINUXACTIVITYMONITORFACTORY_H
