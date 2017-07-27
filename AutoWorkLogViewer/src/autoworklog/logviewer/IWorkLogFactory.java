package autoworklog.logviewer;

/**
 * Base interface for all work log factories.
 */
public interface IWorkLogFactory {

	/**
	 * Creates a new work log.
	 * @return WorkLog the non null worklog created by the factory.
	 */
	public WorkLog createWorkLog();
}
