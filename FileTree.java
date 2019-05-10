import java.util.LinkedList;

import java.util.Iterator;
public class FileTree
{
    private FileSystemObject root;
    private LinkedList<FileSystemObject> path = new LinkedList<FileSystemObject>();
    private FileSystemObject here;


    
    
    private FileSystemObject currentFolder()
    {
      return path.getLast();
    }





   

    public FileTree()
    {
        root = new FolderObject("root");
        path.addFirst(root);
        here = root;

    }
    

    
    //Gets current path
    public String getPath()
    {
      String pathname = "";
      for(int i = 0; i < path.size(); i++)
      {
        pathname += path.get(i).getName() + "/";
      }
      
      return pathname;
    }
    
    //Checks if currently in root dir
    public boolean isRoot()
    {
      return root == path.getLast();
    }


    public String[] parsePath(String s) //send paths to turn them into an array fpr validatePath and doPath to use
    {
      return s.split("/");
    }
 
    //checks if the given path is valid
    public boolean validatePathStep(String s, FileSystemObject o)
    {
      switch(s)
      {
        case "\\": //note: this is actually TWO \ input wise as the first \ of each pair makes the next one be read literally.
          return true; //return to root is always valid
        case "..":
          return ( !(o == root)); //going up a folder is valid unless root
        default:
          return hasFolder(s, o);
        
      }
    }

    /**
     * Check to see if a path is valid
     * 
     */
    public boolean validatePath(String[] path, FileSystemObject o)
    {
      boolean valid = true;
      int step = 0;
      FileSystemObject current = o;
      do {
        valid = validatePathStep(path[step], current);
        current = FollowPathStep(path[step], current);
        step++;
      } while (valid && step < path.length);

      return valid;
    }

    
  private FileSystemObject FollowPathStep(String pathStep, FileSystemObject current) {
      switch(pathStep)
      {
        case "\\": //note: this is actually one \ input wise as the first \ makes the next one be read literally.
          return root; //return to root is always valid
        case "..":
          return current.parent; //going up a folder is valid unless root
        default:
          return getFolder(pathStep, current);
        
      }
    
  }

  /**
   * Make the move indicated by the string array. Always make sure it's valid first.
   * @param path
   * @return
   */
    private FileSystemObject followPath(String[] path, FileSystemObject o)
    {
      boolean valid = true;
      int step = 0;
      FileSystemObject current = o;
      do {
        valid = validatePathStep(path[step], current);
        current = FollowPathStep(path[step], current);
        step++;
      } while (valid && step < path.length);

      return current;

    }
    


    //If calling directly, always validate first
    private FolderObject getFolder(String s, FileSystemObject o)
    {
      Iterator<FileSystemObject> search = o.createShallowIterator();
      while(search.hasNext())
      {
        FileSystemObject candidate = search.next();
        if (candidate instanceof FolderObject && candidate.getName().equals(s))
        {
          return (FolderObject) candidate;
        }
        
      }
      return null;

    }

    private boolean hasFolder(String s, FileSystemObject o)
    {
      Iterator<FileSystemObject> search = o.createShallowIterator();
      while(search.hasNext())
      {
        FileSystemObject candidate = search.next();
        if (candidate instanceof FolderObject && candidate.getName().equals(s))
        {
          return true;
        }
        
      }
      return false;
    }

    //If calling directly, always validate first with hasFile
    private FileObject getFile(String s, FileSystemObject o)
    {
      Iterator<FileSystemObject> search = o.createShallowIterator();
      while(search.hasNext())
      {
        FileSystemObject candidate = search.next();
        if (candidate instanceof FileObject && candidate.getFullName().equals(s))
        {
          return (FileObject) candidate;
        }
        
      }
      return null;

    }

    private boolean hasFile(String s, FileSystemObject o)
    {
      Iterator<FileSystemObject> search = o.createShallowIterator();
      while(search.hasNext())
      {
        
        FileSystemObject candidate = search.next();
        if (candidate instanceof FileObject && candidate.getFullName().equals(s))
        {
          return true;
        }
        
      }
      return false;
    }

  //Code for implemented commands goes here
   

  
   
   public String readFile(String[] args)//Alex Walters
   {
     
     
     if (args.length < 2)
       return "Please supply a file name";
     FileObject readThis;
     if (hasFile(args[1], here))
     {
      readThis = getFile(args[1], here);
      return(readThis.read()); 
     }
     else
     {
       return args[1] + " could not be found";
     }

   }
   
   public String writeFile(String[] args)//Alex Walters
   {
    if (args.length < 2)
      return "Please supply a file name";
    FileObject writeThis;
    if (hasFile(args[1], here))
    {
      writeThis = getFile(args[1], here);
      System.out.println("Current contents: " + writeThis.read());

      String in = FileSim.userInput.nextLine();
      writeThis.write(in);

      return "";
    }
    else
    {
      return args[1] + " could not be found";
    }
    
   }
   
   

   
  /**
   * Routing notes
  * Windows comamnd prompt is requiring a space before .. now, so we will too.
  * It's practically easier to allow \ in the path than not allow it, so we will.
  * Make sure to pass the path in the form of a single string.
  *
  * Do cover multiple .. with a / between, windows lets you do that as long as you use chdir not cd
  * cases to cover: cd\, cd \, cd <path> where path sections are seperated by / and include .. for back
  * The file system movement methods don't support pathing directly to files ATM.
  */

   //All input will be formatted as so: [{command}, {primary input}, {args}]. not sure we have args. Remember to pass the command when dealing
    //Code stubs for NYI commands goes here
    public String makeFile(String[] args)//Brad, with tweaks by alex
    {
      if (args.length < 2)
        return "Please supply a new file name";
      if (hasFile(args[1], here))
        return "File already exists here";
      String fullName = args[1];

      String[] split = fullName.split("\\."); 
      if (split.length < 2)
      {
        return "Please supply a file name and extension.";
      }
      
      currentFolder().add(new FileObject(split[0], split[1]));
      return "File Created: " + fullName;
      
    }


  //Suggestion: Do not allow deletes of non-empty folders
  //Mandatory: Make sure either deletion closes files, or open files can't be deleted
  //Use CloseFileInternal with Identifiers when closing
 public String delete(String[] theseArgs) //Brad, with tweaks from alex
  {
    if (theseArgs.length < 2)
       return "Please supply a file name to nuke";
   FileObject o = getFile(theseArgs[1], currentFolder());
   FolderObject f = getFolder(theseArgs[1], currentFolder());
   if(o!=null){
     currentFolder().remove(o);
     return "File Deleted";
   }
   else if(f!=null){
     if(f.getSize()==0){
       currentFolder().remove(f);
       return "Folder Deleted";
     }
     else
       return "folder not empty!";
       
   }
  return "File or folder not found";
 }


 public String makeFolder(String[] theseArgs)//Brad, with errpr check by alex
  {
    if (theseArgs.length < 2)
       return "Please supply a file name to make";
    if (hasFolder(theseArgs[1], here))
      return "Folder already exists here";
   FolderObject o = new FolderObject(theseArgs[1]);
   currentFolder().add(o);
    return "Folder Created";
 }


 public String rename(String[] theseArgs)//Brad, with errpr check by alex 
  {
   if (theseArgs.length < 3)
       return "Please supply old and new names";
   FileObject o = getFile(theseArgs[1], currentFolder());
   FolderObject f = getFolder(theseArgs[1], currentFolder());
   if(o!=null){
     String[] newName = theseArgs[2].split("\\.");
    if (newName.length < 2)
      return "New name must be valid";
    if (hasFile(theseArgs[2], here))
      return "File already exists here";
     o.setName(newName[0]);
     o.setType(newName[1]);
     return "Name Changed";
   }
   else if(f!=null){
    if (hasFolder(theseArgs[2], here))
      return "Folder already exists here";
     f.setName(theseArgs[2]);
     return "Name Changed";
   }
     
    return "File or Folder not found";
 }


 public String move(String[] theseArgs) {
  if (theseArgs.length < 3)
  return "Please supply name and target path";
  String[] movepath = theseArgs[2].split("/");
  FileSystemObject object;

  if (hasFile(theseArgs[1], here))
  {
    object = getFile(theseArgs[1], here);
  }
  else if (hasFolder(theseArgs[1], here))
  {
    object = getFolder(theseArgs[1], here);

  }
  else 
    return "File or folder not found";
  if (validatePath(movepath, here))
  {
    FileSystemObject targetLoc = followPath(movepath, here);
    if (hasFolder(theseArgs[1], targetLoc) )
    	return "Folder with the same name already exists in destination, aborting";
    if (hasFile(theseArgs[1], targetLoc))
    {

    	
		System.out.println("Press y or r to overwrite, anything to abort");
		String whatdo = FileSim.userInput.nextLine();
		if (whatdo.toLowerCase().charAt(0) == 'y' || whatdo.toLowerCase().charAt(0) == 'r')
		{
			FileObject tokill = getFile(theseArgs[1], targetLoc);
			targetLoc.remove(tokill);
			object.parent.remove(object);
		    targetLoc.add(object);
		    return theseArgs[1] + " moved, overwriting existing file.";
			
		}
		else return "Move aborted";
    		
    	
    }
    object.parent.remove(object);
    targetLoc.add(object);
    return theseArgs[1] + " moved";

    
  }
  return "Invalid path";
    
 }

  
 public String copy(String[] theseArgs)
  {
    if (theseArgs.length < 3)
      return "Please supply name and new name path";
    

    FileSystemObject object;
    String[] newName = theseArgs[2].split("\\.");
    if (hasFile(theseArgs[1], currentFolder()))
    {
      object = getFile(theseArgs[1], currentFolder());
      if (newName.length < 2)
    	  return "Please give new name and type";
    }
    else if (hasFolder(theseArgs[1], currentFolder()))
    {
      object = getFolder(theseArgs[1], currentFolder());

    }
    else 
        return "File or folder not found";
    if(object!=null)
    {
      if (hasFolder(theseArgs[2], currentFolder()) )
        return "Folder with the same name already exists here, aborting";
      if (hasFile(theseArgs[2], currentFolder()))
      {
      	System.out.println("Object with the same name already here");
      	
  		System.out.println("Press y or r to overwrite, anything to abort");
  		String whatdo = FileSim.userInput.nextLine();
  		if (whatdo.toLowerCase().charAt(0) == 'y' || whatdo.toLowerCase().charAt(0) == 'r')
  		{
  			FileObject tokill = getFile(theseArgs[1], currentFolder());
  			currentFolder().remove(tokill);
  			FileSystemObject newObject = object.clone();
  			newObject.setName(newName[0]);
  			newObject.setType(newName[1]);
  			currentFolder().add(newObject);
  			
  			
  			

  		    return theseArgs[1] + " copied, overwriting existing file.";
  			
  		}
  		else return "Copy aborted";
      		
      	
      }
      currentFolder().add(object.clone());
      return "File Copied";
    }
    return "File not found to copy";
 }


 public String changeDir(String[] theseArgs) {
    if (theseArgs[0].equals("cd\\"))
      return toRoot();
    else
    {
      String[] pathS;
      if (theseArgs[0].equals("cd.."))
        pathS = new String[] {".."};
      else if (theseArgs.length < 2)
        return "Please supply a new path";
      else
        pathS = theseArgs[1].split("/");
      if (validatePath(pathS, here))
      {
        here = followPath(pathS, here);
        resetPath();
        return "Directory change successful";
      }
      
      return "Invalid path";
    }
    
 }






  private void resetPath() {
    LinkedList<FileSystemObject> newPath = new LinkedList<FileSystemObject>();
    FileSystemObject tempHere = here;
    while (tempHere.parent != null)
    {
      newPath.addFirst(tempHere);
      tempHere = tempHere.parent;
    }
    newPath.addFirst(tempHere);
    path = newPath;
  }

  public String toRoot() {
   if(isRoot())
     return "Already in root";
   path.clear();
   path.addFirst(root);
   here = root;
   return "Returned to root";
 }

  
  public String getInfo(String[] theseArgs)
  {
	  	FileSystemObject f;
	    if (theseArgs.length < 2)
	        return "Please supply a name";
	    else if (hasFile(theseArgs[1], currentFolder() ))
	    {
	    	f = getFile(theseArgs[1], currentFolder());
	    	
	    }
	    else if (hasFolder(theseArgs[1], currentFolder() ))
	    {
	    	f = getFolder(theseArgs[1], currentFolder());
	    	
	    }
	    else
	    {
	    	return "File or folder not found";
	    }
	    	
	    return f.getFullName() + "\t\t" + f.getSize() + f.getModified(); 
	  
  }

 public String viewFolder(String[] theseArgs) {
   String out = "";
   Iterator<FileSystemObject> search = currentFolder().createShallowIterator();

   out += "Name\t\t\t\tSize\tID\tLast Modified\n";
   while(search.hasNext())
   {
     
     FileSystemObject candidate = search.next();
     out += candidate.getFullName() + "\n";

     
   }
   return out;
 }
    

}