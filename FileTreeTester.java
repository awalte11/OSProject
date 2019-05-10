
public class FileTreeTester{

  public static int commandCount = 0;
  static FileTree tree;
  public static void main(String[] args)
  {
    tree = new FileTree();
    lineRep();
    System.out.println(tree.makeFolder(new String[] {"makefolder", "tFer"}));
    lineRep();
    System.out.println(tree.makeFolder(new String[] {"makefolder", "testFolder2"}));
    lineRep();
    System.out.println(tree.makeFile(new String[] {"makeFile", "testfile.dum"}));
    lineRep();
    System.out.println(tree.changeDir(new String[] {"cd", "testFolder" }));
    lineRep();
    System.out.println(tree.makeFile(new String[] {"makeFile", "testfile2.dum"}));
    lineRep();
    System.out.println(tree.makeFile(new String[] {"makeFile", "testfile4.dum"}));
    lineRep();
    System.out.println(tree.viewFolder(new String[] {"makeFile", "testfile4.dum"}));
  


    
    System.out.println(tree.makeFile(new String[] {"make", "test4.dum" }));


   
    
  }

  private static void lineRep()
  {
    commandCount++;
    System.out.println(commandCount + " path: " + tree.getPath());
  }
}