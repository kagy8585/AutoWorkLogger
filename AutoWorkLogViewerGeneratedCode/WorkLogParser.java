
public class WorkLogParser implements IWorkLogFactory {

	private String inputFilePath;	public WorkLogParser () { };
	
	//
	// Methods
	//


	/**
	 * Set the value of inputFilePath
	 * @param newVar the new value of inputFilePath
	 */
	private void setInputFilePath (String newVar) {
		inputFilePath = newVar;
	}

	/**
	 * Get the value of inputFilePath
	 * @return the value of inputFilePath
	 */
	private String getInputFilePath () {
		return inputFilePath;
	}

	//
	// Other methods
	//

	/**
	 * Creates a new work log parser based onj an input file.
	 * @param        fileName The name of the input file.
	 */
	public void WorkLogParser(String fileName)
	{
	}


	/**
	 * Creates a new work log based oon the input file.
	 * @return       WorkLog
	 */
	public WorkLog createWorkLog()
	{
	}


}
