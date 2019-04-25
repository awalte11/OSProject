public interface interface_front {


	/**
	 * This is just an interface declaring the user-callable methods
	 */

	 /**
	  * Converts user input to method call
	  * first element of input is command rest is parameters
	  * @param in input
	  */
	public void parseInput (String in);
	

	/**
	 * The one that creates a file. Should definitely take a single input with nothing but a file name.
	 * Possible other options
	 * @param in
	 */
	public void createFile (String[] in);
	
	/**
	 * Delete file. Takes filename as input.
	 * @param in
	 */
	public void deleteFile (String[] in);
	

	/**
	 * Create folder at current address
	 * @param in name of folder
	 */
	public void createFolder (String[] in);
	
	/**
	 * Delete folder at current address
	 * @param in
	 */
	public void deleteFolder (String[]in);
	
	/**
	 * rename file
	 * @param in element one is old name, element two new name. match to name and extension
	 */
	public void rename (String[] in);
	

	/**
	 * Move file or folder
	 * @param in param one is file. Param two is path.
	 */
	public void move (String[] in);
	
	/**
	 * Copy file or folder
	 * @param in param one is file. Param two is path.
	 */
	public void copy (String[] in);
	
	/**
	 * Change directory being pointed at
	 * @param in can be either .. to go back, or the folder name. / to go multiple times
	 */
	public void changeDir(String[] in);
	
	/**
	 * Display current dir
	 * @param in should accept nothing, possibly accept arguments
	 */
	public void viewDir(String[]in);
	
	public void help();
	
}
