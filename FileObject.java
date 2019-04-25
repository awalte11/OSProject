import java.util.Iterator;
public class FileObject extends FileSystemObject {
    //file specific stuff goes here

    public FileObject(String name, String type)
    {
        super(name, type);
       
        
    }

    
    public String getName()
    {
        return name +"." +type;
    }
    
    public Iterator<FileSystemObject> createDeepIterator()
    {
      return new NullIterator();
    }
    
     public Iterator<FileSystemObject> createShallowIterator()
    {
      return new NullIterator();
    }
}