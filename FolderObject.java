import java.util.Iterator;
import java.util.ArrayList;



public class FolderObject extends FileSystemObject {
    ArrayList<FileSystemObject> children = new ArrayList<FileSystemObject>();//should probably replace with arrays. But I don't have the energy for shrink/expand array code right now
    Iterator<FileSystemObject> iterator = null;
    
    public void add(FileSystemObject child)
    {
        children.add(child);
        child.parent = this;
        refreshModified();
    }
    public void remove(FileSystemObject child)
    {
        children.remove(child);
        child.parent = null;
        refreshModified();
    }
    public FileSystemObject getChild(int i)//get child by number in list. probably not needed with iterator but might be hand
    {
        return children.get(i);
    }
	
	public String getFullName()
	{
		return name +"/";
	}

    public void setType(String type)
    {
        throw new UnsupportedOperationException();
    }
    
    public Iterator<FileSystemObject> createDeepIterator()//note to self, check what this one does if the contents change between first call and use
    {
      if (iterator == null)
      {
        iterator = new CompositeIterator(children.iterator());
      }
      return iterator;
    }
    
    public Iterator<FileSystemObject> createShallowIterator()
    {
      return children.iterator();
    }
    
    public FolderObject(String name)
    {
        super(name, "folder");
        
        
    }



}