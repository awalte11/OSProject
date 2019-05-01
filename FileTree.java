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
    
    //function that finds and goes to a folder one step down
    public String toChild(String name)
    {
      Iterator<FileSystemObject> search = here.createShallowIterator();
      while(search.hasNext())
      {
        FileSystemObject candidate = search.next();
        if (candidate instanceof FolderObject && candidate.getName().equals(name))
        {
          path.addLast(candidate);
          here = candidate;
          return getPath();
        }
        
      }
      return "Folder Not Found. \n" + getPath();
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


	//TODO, overhaul this section to enable remote actions on files. Or just decide you can only operate on local folders

    public boolean validatePathStep(String s, FileSystemObject o)
    {
      switch(s)
      {
        case "\\":
          return true; //return to root is always valid
        case "..":
          return ( !(o == root)); //going up a folder is valid unless root
        default:
          return isFolderHere(s, o);
        
      }
    }
    
    //function that goes one step up the tree;
    public String folderUp()
    {
      if (root != path.getLast())
      {
        path.removeLast();
        here = path.getLast();
        return getPath();
      }
      else
      {
        return "Already at root. \n" + getPath();
      }
    
    }

    public boolean isFolderHere(String s, FileSystemObject o)
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