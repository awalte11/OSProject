import java.util.Iterator;
public class NullIterator implements Iterator<FileSystemObject> {
  public FileSystemObject next() {return null;}
  public boolean hasNext() {return false;}
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
}