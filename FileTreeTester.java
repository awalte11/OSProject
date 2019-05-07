import java.util.Iterator;
public class FileTreeTester{

  public static int commandCount = 0;
  static FileTree tree;
  public static void main(String[] args)
  {
    tree = new FileTree();
    lineRep();
    tree.currentFolder().add(new FolderObject("test"));
    lineRep();
    tree.currentFolder().add(new FolderObject("test2"));
    lineRep();
    tree.currentFolder().add(new FileObject("test", "dum"));
    lineRep();
    System.out.println(tree.changeDir(new String[] {"cd", "test" }));
    lineRep();
    tree.currentFolder().add(new FileObject("test4", "dum"));
    lineRep();
    tree.currentFolder().add(new FileObject("tes2t", "dum"));
    lineRep();
    //tree.toRoot();
    System.out.println(tree.currentFolder().getSize());
    lineRep();
    Iterator<FileSystemObject> testIt = tree.currentFolder().createDeepIterator();
    lineRep();
    while(testIt.hasNext())
    {
      System.out.println(testIt.next().getFullName());
      lineRep();
    }
    
    System.out.println(tree.makeFile(new String[] {"make", "test4.dum" }));
    lineRep();
    System.out.println(tree.openFile(new String[] {"open", "test4.dum" }));
    lineRep();
    System.out.println(tree.openFile(new String[] {"open", "test4.dum" }));
    System.out.println(tree.writeFile(new String[] {"write", "Shine", "3" }));
    while(testIt.hasNext())
    {
      System.out.println(testIt.next().getFullName());
    }
    System.out.println(tree.currentFolder().getSize());
    
  }

  private static void lineRep()
  {
    commandCount++;
    System.out.println(commandCount + "path: " + tree.getPath());
  }
}