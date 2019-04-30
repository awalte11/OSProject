import java.time.LocalDate;
import java.util.Iterator;
public abstract class FileSystemObject {
    protected String name;
    protected String type;
    protected LocalDate updated;
    protected int size;
	private int identifier;
    protected FileSystemObject parent;
	private static int nextIdentifier = 0;

	public int getIdentifier()
	{
		return identifier;
	}

    public void add(FileSystemObject child) throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }
    public void remove(FileSystemObject child) throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }
    public FileSystemObject getChild(int i) throws UnsupportedOperationException//gonna need more of these. possibly with lambda expression support or something
    {
        throw new UnsupportedOperationException();
    }
    
    public FileSystemObject getParent()
    {
      return parent;
    }
    
    public void setParent(FileSystemObject newParent) 
    {
      if (parent != null)
      {
        parent.remove(this);
      }
      parent = newParent;
      newParent.add(this);
    }
	

	public abstract String getFullName();

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
        refreshModified();
    }

    public void setType(String type)
    {
        this.type = type;
        refreshModified();
    }

    public String getType()
    {
        return type;
    }
    
    public void refreshModified()
    {
        updated = LocalDate.now();
    }

    public int getSize()
    {
        return  size;
    }

    

    
    public FileSystemObject(String name, String type)
    {
        this.name = name;
		this.identifier = nextIdentifier++;
        this.type = type;
        this.updated = LocalDate.now();
        size = 1;
        //NO parent-related functions can go here, as this constructor must be able to deal with the creation of the root folder
    }
    
    //This Iterator is for going over the whole tree, starting from the object in question.
    public abstract Iterator<FileSystemObject> createDeepIterator();

    //for iterating over the children of the object in question
    public abstract Iterator<FileSystemObject> createShallowIterator();




    ///All functions in file or folder class must be declared here.
    ///Composite pattern recommends you also have a default implementation here.
    ///If it doesn't make sense for a file - have the default throw the exception
    ///If it doesnt' make sense for a folder - have the folder implementation throw the default.

}