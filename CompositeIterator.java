import java.util.*;
public class CompositeIterator implements Iterator<FileSystemObject> {
  Stack<Iterator<FileSystemObject>> stack = new Stack<Iterator<FileSystemObject>>();
  
  public CompositeIterator(Iterator<FileSystemObject> iterator)
  {
    stack.push(iterator);
  }
  
  public FileSystemObject next()
  {
    if (hasNext())
    {
      Iterator<FileSystemObject> iterator = stack.peek();
      FileSystemObject fso = iterator.next();
      
      stack.push(fso.createDeepIterator());
      
      return fso;
    }
    else return null;
  }
  public boolean hasNext()
  {
    if (stack.empty()){
      return false;
    }
    else {
      Iterator<FileSystemObject> iterator = stack.peek();
      if (!iterator.hasNext()){
        stack.pop();
        return hasNext();
      }
      else return true;
    }
  }
}