import java.util.Iterator;
public class FileObject extends FileSystemObject {
    //file specific stuff goes here


    
    public String getFullName()
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