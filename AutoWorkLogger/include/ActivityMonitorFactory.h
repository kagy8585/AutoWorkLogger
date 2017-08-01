
#ifndef ACTIVITYMONITORFACTORY_H
#define ACTIVITYMONITORFACTORY_H

#include <string>

#include "ActivityMonitor.h"

/**
 * Basic factory for activity monitors.
 */
class ActivityMonitorFactory
{
public:
	/**
	 * Empty Constructor
	 */
	ActivityMonitorFactory ()
	{ }

	/**
	 * Creates a new activity monitor.
	 * @return ActivityMonitor *
	 */
	virtual ActivityMonitor * createActivityMonitor() const=0;

	/**
	 * Empty Destructor
	 */
	virtual ~ActivityMonitorFactory ()
	{ }
};

#endif // ACTIVITYMONITORFACTORY_H
