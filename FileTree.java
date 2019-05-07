import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;
public class FileTree
{
    private FileSystemObject root;
    private LinkedList<FileSystemObject> path = new LinkedList<FileSystemObject>();
    private FileSystemObject here;

    private ArrayList<FileObject> openFiles = new ArrayList<FileObject>();
    
    
    public FileSystemObject currentFolder()
    {
      return path.getLast();
    }


    public FileSystemObject getRoot()
    {
        return root;
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
        case "\\": //note: this is actually TWO \ input wise as the first \ of each pair makes the next one be read literally.
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
    public FileSystemObject followPath(String[] path, FileSystemObject o)
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
    public FolderObject getFolder(String s, FileSystemObject o)
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

    public boolean hasFolder(String s, FileSystemObject o)
    {
      Iterator<FileSystemObject> search = o.createShallowIterator();
      while(search.hasNext())
      {
        FileSystemObject candidate = search.next();
        System.out.println (s + " " + candidate.getFullName());
        if (candidate instanceof FolderObject && candidate.getName().equals(s))
        {
          return true;
        }
        
      }
      return false;
    }

    //If calling directly, always validate first with hasFile
    public FileObject getFile(String s, FileSystemObject o)
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

    public boolean hasFile(String s, FileSystemObject o)
    {
      Iterator<FileSystemObject> search = o.createShallowIterator();
      while(search.hasNext())
      {
        
        FileSystemObject candidate = search.next();
        System.out.println ("Check: " + s + " " + candidate.getFullName());
        if (candidate instanceof FileObject && candidate.getFullName().equals(s))
        {
          return true;
        }
        
      }
      return false;
    }

  //Code for implemented commands goes here
   
  public String openFile(String[] args)//Alex Walters
  {
    if (args.length < 2)
      return "Please supply a file name";

    String fileName = args[1];
    if (!hasFile(fileName, here))
    {
      return "File not found";
    }
    else
    {
      FileObject o = getFile(fileName, here);
      if (openFiles.contains(o))
        return o.getFullName() + " is already open. ID is " + o.getIdentifier();
      else
      {
        openFiles.add(o);
        return o.getFullName() + " is now open. ID is " + o.getIdentifier();
      }

    } 
  }
  public String closeFile(String[] args)//Alex walters
  {
    int id;
    if (args.length < 2)
      return "Please supply an integer file ID";
    try {
       id = Integer.parseInt(args[1]);
    }
    catch (NumberFormatException e){
      return "Please supply an integer file ID";
    }
    boolean removed = openFiles.removeIf(o -> (o.getIdentifier() == id)  );
    if (removed)
      return "File closed";
    else
      return "File # " + id + " is not open";    
    }
    
    //For you guys to use when deleting
    private void closeFileInternal (int id)
    {
      openFiles.removeIf(o -> (o.getIdentifier() == id)  );
    }
   //Opens file and returns result. Moved here so I can play with implementation options without busting the public facing option

   private FileObject findOpenFile(int id){    
    for (FileObject file : openFiles) {
        if (file.getIdentifier() == id) {
            return file;
        }
    }
    return null; 
}
  
   
   public String readFile(String[] args)//Alex Walters
   {
     
     int id;
     if (args.length < 2)
       return "Please supply an integer file ID";
     try {
       id = Integer.parseInt(args[1]);
     }
     catch (NumberFormatException e){
       return "Please supply an integer file ID";
     }
     FileObject readThis = findOpenFile(id);
     if (readThis == null)
     return "Bad file Id";
     if (args.length == 2)
     {
        return(readThis.read());
     }
     else if (args.length == 3)
     {
        int readStart = 0;
        try {
          readStart = Integer.parseInt(args[2]);
        }
        catch (NumberFormatException e){
          return "Read start point must be an integer";
        }
        try {
          return readThis.read(readStart);
        }
        catch (StringIndexOutOfBoundsException e) {
          return "Read start point must be an integer less than the length of the data";
        }
     }
     else if (args.length >= 4)
     {
        int readStart = 0;
        int readend = 0;
        try {
          readStart = Integer.parseInt(args[2]);
        }
        catch (NumberFormatException e){
          return "Read start point must be a positive integer";
        }
        if ( readStart < 0)
          return "Read start point must be a positive integer";
        try {
          readend = Integer.parseInt(args[3]);
        }
        catch (NumberFormatException e){
          return "Read stop point must be a positive integer";
        }
        try {
          return readThis.read(readStart, readend);
        }
        catch (StringIndexOutOfBoundsException e) {
          return "Invalid start or end: Must describe a subseqence of data";
        }
     }


    return "It's not actually possible to get this message, but the compiler insists it it must be a thing";
   }
   
   public String writeFile(String[] args)//Alex Walters
   {
    int id;
    if (args.length < 3)
      return "Please supply data (no spaces, sim limit) and a file id";
    try {
      id = Integer.parseInt(args[2]);
    }
    catch (NumberFormatException e){
      return "Please supply an integer file ID";
    }
    FileObject readThis = findOpenFile(id);
    if (readThis == null)
      return "Bad file Id";
    if (args.length == 3)
    {
       return(readThis.write(args[1]));
    }
    else if (args.length == 4)
    {
       int readStart = 0;
       try {
         readStart = Integer.parseInt(args[3]);
       }
       catch (NumberFormatException e){
         return "Write start point must be an integer";
       }
       try {
        return(readThis.write(args[1], readStart));
       }
       catch (StringIndexOutOfBoundsException e) {
         return "Write start point must be a integer less than the length of the data";
       }
    }
    else if (args.length >= 5)
    {
       int readStart = 0;
       int readend = 0;
       try {
         readStart = Integer.parseInt(args[3]);
       }
       catch (NumberFormatException e){
         return "Write start point must be a positive integer";
       }
       try {
         readend = Integer.parseInt(args[4]);
       }
       catch (NumberFormatException e){
         return "Write stop point must be a positive integer";
       }
       try {
        return(readThis.write(args[1], readStart, readend));
       }
       catch (StringIndexOutOfBoundsException e) {
         return "Invalid start or end: Must describe a subseqence of data";
       }
    }


    return "It's not actually possible to get this message, but the compiler insists it it must be a thing";
    
   }
   
   //Code stubs for NYI commands file system functions alex is handling goes here.

   
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
      System.out.print(fullName);
      String[] split = fullName.split("\\."); 
      System.out.print(split.length);
      for (String s : split) {
        System.out.println(s);
      }
      if (split.length < 2)
      {
        return "Please supply a file name and extension.";
      }
;
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
     closeFileInternal(o.getIdentifier());
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
    if (hasFile(theseArgs[2], here))
      return "File already exists here";
     o.setName(theseArgs[2]);
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
  String[] path = theseArgs[2].split("/");
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
  if (validatePath(path, here))
  {
    FileSystemObject targetLoc = followPath(path, here);
    if (hasFolder(theseArgs[2], targetLoc) || hasFile(theseArgs[2], targetLoc))
      return "Target location already has an object with this name";
    object.parent.remove(object);
    targetLoc.add(object);
  }
  return "Invalid path";
    
 }

  
 public String copy(String[] theseArgs)
  {
    if (theseArgs.length < 3)
      return "Please supply name and new name path";
    
    String[] split = theseArgs[2].split("\\."); 
    if (split.length < 2)
     return "Please supply a file name and extension.";
    FileObject o = getFile(theseArgs[1], currentFolder());
    if(o!=null)
    {
      FileObject p = new FileObject(split[0], split[1]);
      currentFolder().add(p);
      return "File Copied";
    }
    return "File not found to copy";
 }


 public String changeDir(String[] theseArgs) {
    if (theseArgs[0].equals("cd\\"))
      return toRoot();
    else
    {
      if (theseArgs.length < 2)
        return "Please supply a new path";
      String[] pathS = theseArgs[1].split("/");
      if (validatePath(pathS, here))
      {
        System.out.println("Start: " + here.name);
        here = followPath(pathS, here);
        System.out.println("End:" + here.name);
        resetPath();
        System.out.println("End2:" + here.name);
        System.out.println("End:" + path.getLast().getFullName());
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
      newPath.addLast(tempHere);
      tempHere = tempHere.parent;
    }
    newPath.addLast(tempHere);
  }

  public String toRoot() {
   if(isRoot())
     return "Already in root";
   path.clear();
   path.addFirst(root);
   return "Returned to root";
 }


 public String viewFolder(String[] theseArgs) {
   Iterator<FileSystemObject> search = currentFolder().createShallowIterator();
   while(search.hasNext())
   {
     FileSystemObject candidate = search.next();
     System.out.println(candidate.getFullName());
   }
   return "";
 }
    

}