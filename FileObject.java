import java.util.Iterator;
public class FileObject extends FileSystemObject {
    //file specific stuff goes here
    StringBuilder contents = new StringBuilder();
    @Override
    public int getSize()
    {
      return contents.length();
    }

    public String read() 
    {
      return contents.toString();
    }

    public String read(int start) throws StringIndexOutOfBoundsException 
    {
      return contents.substring(start);
    }

    public String read(int start, int end) throws StringIndexOutOfBoundsException 
    {
      return contents.substring(start, end);
    }

    public String write(String s)
    {
      refreshModified();
      contents = new StringBuilder(s);
      return "File: " + getFullName() + " contains: " + contents.toString();
    }
    public String write(String s, int start) throws StringIndexOutOfBoundsException 
    {
      refreshModified();
      contents = contents.replace(start, start + s.length() -1, s);
      return "File: " + getFullName() + " contains: " + contents.toString();
    }
    public String write(String s, int start, int end) throws StringIndexOutOfBoundsException 
    {
      refreshModified();
      contents = contents.replace(start, end, s);
      return "File: " + getFullName() + " contains: " + contents.toString();
    }

    //Do not call from anywhere but inside calls to add method in FolderObject
    public FileObject(String name, String type)
    {
      super(name, type);
    }

    @Override
    public String getFullName()
    {
        return name + "." + type;
    }
    @Override
    public Iterator<FileSystemObject> createDeepIterator()
    {
      return new NullIterator();
    }
    @Override
     public Iterator<FileSystemObject> createShallowIterator()
    {
      return new NullIterator();
    }
}