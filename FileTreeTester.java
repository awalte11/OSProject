import java.util.Iterator;
public class FileTreeTester{

  public static void main(String[] args)
  {
    FileTree tree = new FileTree();
    System.out.printf(tree.getPath());
    tree.currentFolder().add(new FolderObject("test"));
    tree.currentFolder().add(new FolderObject("test2"));
    tree.currentFolder().add(new FileObject("test", "dum"));
    tree.followPath(new String[] {"test"}, tree.currentFolder());

    tree.currentFolder().add(new FileObject("test4", "dum"));
    tree.currentFolder().add(new FileObject("tes2t", "dum"));
    tree.toRoot();
    System.out.println(tree.currentFolder().getSize());
    Iterator<FileSystemObject> testIt = tree.currentFolder().createDeepIterator();
    while(testIt.hasNext())
    {
      System.out.println(testIt.next().getFullName());
    }
    System.out.println(tree.currentFolder().getSize());
    
  }
}