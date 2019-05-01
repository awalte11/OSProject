import java.util.LinkedList;
import java.util.Iterator;
public class FileTree
{
    private FileSystemObject root;
    private LinkedList<FileSystemObject> path = new LinkedList<FileSystemObject>();
    private FileSystemObject here;
    
    
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
    public FileSystemObject getFolder(String s, FileSystemObject o)
    {
      Iterator<FileSystemObject> search = o.createShallowIterator();
      while(search.hasNext())
      {
        FileSystemObject candidate = search.next();
        if (candidate instanceof FolderObject && candidate.getName().equals(s))
        {
          return candidate;
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
        if (candidate instanceof FolderObject && candidate.getName().equals(s))
        {
          return true;
        }
        
      }
      return false;
    }

    //If calling directly, always validate first with hasFile
    public FileSystemObject getFile(String s, FileSystemObject o)
    {
      Iterator<FileSystemObject> search = o.createShallowIterator();
      while(search.hasNext())
      {
        FileSystemObject candidate = search.next();
        if (candidate instanceof FileObject && candidate.getFullName().equals(s))
        {
          return candidate;
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
        if (candidate instanceof FileObject && candidate.getFullName().equals(s))
        {
          return true;
        }
        
      }
      return false;
    }

    //Code for implemented commands goes here
   
   
   //Code stubs for NYI commands file system functions alex is handling goes here.
   public String openFile(String[] args)
   {
	   return "Not yet implemented";
	   
   }
   
   public String readFile(String[] args)
   {
	   return "Not yet implemented";
	   
   }
   
   public String writeFile(String[] args)
   {
	   return "Not yet implemented";
	   
   }
   
   public String closeFile(String[] args)
   {
	   return "Not yet implemented";
	   
   }
  /**
   * Routing notes
  * Windows comamnd prompt is requiring a space before .. now, so we will too.
  * It's practically easier to allow \ in the path than not allow it, so we will.
  * Make sure to pass the path in the form of a single string.
  *
  * Do cover multiple .. with a / between, windows lets you do that as long as you use chdir not cd
  * cases to cover: cd\, cd \, cd <path> where path sections are seperated by / and include .. for back
  */

   //All input will be formatted as so: [{command}, {primary input}, {args}]. not sure we have args. Remember to pass the command when dealing
    //Code stubs for NYI commands goes here
    public String makeFile(String[] args)
    {

        return "Not yet implemented";
      
    }


	public String delete(String[] theseArgs) {

		return "Not yet implemented";
	}


	public String makeFolder(String[] theseArgs) {
    return "Not yet implemented";
	}


	public String rename(String[] theseArgs) {
    return "Not yet implemented";
	}


	public String move(String[] theseArgs) {
    return "Not yet implemented";
	}


	public String copy(String[] theseArgs) {
    return "Not yet implemented";
	}


	public String changeDir(String[] theseArgs) {
    return "Not yet implemented";
	}


	public String toRoot() {
    return "Not yet implemented";
	}


	public String viewFolder(String[] theseArgs) {
    return "Not yet implemented";
	}
    

}