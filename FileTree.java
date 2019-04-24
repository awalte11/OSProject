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
    
    //function that finds a folder one step down
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
    
    public String getPath()
    {
      String pathname = "";
      for(int i = 0; i < path.size(); i++)
      {
        pathname += path.get(i).getName() + "/";
      }
      
      return pathname + "\n";
    }
    
    public boolean isRoot()
    {
      return root == path.getLast();
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

    

}