
#ifndef ACTIVITYMONITORFACTORY_H
#define ACTIVITYMONITORFACTORY_H

#include <string>
/******************************* Abstract Class ****************************
ActivityMonitorFactory does not have any pure virtual methods, but its author
  defined it as an abstract class, so you should not use it directly.
  Inherit from it instead and create only objects from the derived classes
*****************************************************************************/

class ActivityMonitorFactory
{
public:

	// Constructors/Destructors
	//  


	/**
	 * Empty Constructor
	 */
	ActivityMonitorFactory ();

	/**
	 * Empty Destructor
	 */
	virtual ~ActivityMonitorFactory ();



	/**
	 * Creates a new activity monitor.
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

#endif // ACTIVITYMONITORFACTORY_H
