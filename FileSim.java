
import java.util.Scanner;
public class FileSim
{
    static FileTree tree;
    public static void main(String[] args)
    {
        boolean done = false;
        Scanner input = new Scanner(System.in);
        tree = new FileTree();
        System.out.println("Welcome to the file sim");
        while(!done)
        {
            System.out.printf(tree.getPath() +":");
            String[] in = input.nextLine().split(" ");


         
            switch(in[0].toLowerCase())
            {
                case "mkfile":
                case "newfile":
                    System.out.println(tree.makeFile(in));
                    break;

                case "del":
                    System.out.println(tree.delete(in));
                    break;
                case "mkfolder":
                case "makedir":
                case "makefolder":
                case "mkdir":
                    System.out.println(tree.makeFolder(in));
                    break;
                case "rn":
                case "rename":
                    System.out.println(tree.rename(in));
                    break;
                case "move":
                    System.out.println(tree.move(in));
                    break;
                case "copy":
                    System.out.println(tree.copy(in));
                    break;

                  
                      
                
                case "cd":
                case "cd..":
                case "cd\\"://Note: This is cd\ when typing  
                    System.out.println(tree.changeDir(in));
                    break;

                case "ls":
                case "":
                    System.out.println(tree.viewFolder(in));
                    break;


                case "help":
                case "h":
                    help();
                    break;

                case "open":
                    System.out.println(tree.openFile(in));
                    break;
                case "close":
                    System.out.println(tree.closeFile(in));
                    break;
                case "read":
                    System.out.println(tree.readFile(in));
                    break;
                case "write":
                    System.out.println(tree.writeFile(in));
                    break;
                case "q":
                case "quit":
                    done = true;
                    break;

                default:
                    System.out.println("Invalid command");
            }

        }


        input.close();

    }



    
    public static void help() {
        System.out.println("Commands for the file system sim:");
        System.out.println("");
        System.out.println("Pathing rules: each step in the path must be seperated by /. '..' goes to parent folder");
        System.out.println("");
        System.out.println("Create a file: 'makefile' or 'newfile' filename");
        System.out.println("");
        System.out.println("Delete a file or folder: 'del' name");
        System.out.println("");
        System.out.println("Make a folder: 'mkfolder' or 'mkdir' or 'makedir' or 'makefolder' name");
        System.out.println("");
        System.out.println("Rename a file or  folder: 'rn' or 'rename' oldname newname");
        System.out.println("");
        System.out.println("Move a file or  folder: 'move' name newpath");
        System.out.println("");
        System.out.println("Copy a file or  folder: 'copy' name new name");
        System.out.println("");
        System.out.println("Change active folder: 'cd' newpath");
        System.out.println("");
        System.out.println("Move to parent: 'cd..'");
        System.out.println("");
        System.out.println("Return to root active folder: 'cd\\'");
        System.out.println("");
        System.out.println("View current location: 'ls' or ''");

        System.out.println("Commands for the open/close/read/write section:");
        System.out.println("Warning, very simmy:");
        System.out.println("Commands for the file system sim:");
        System.out.println("");
        System.out.println("OpenFile: 'open' filename");
        System.out.println("Returns identifier, used to ID file in following commands");
        System.out.println("");
        System.out.println("ReadFile: 'read' identifier [start] [end] ");
        System.out.println("Bracketed commands indicate optional start and ends of read. End requires start");
        System.out.println("");
        System.out.println("WriteFile: 'write' data identifier [start] [end] ");
        System.out.println("At the moment, parsing limitations prevent data from including spaces");
        System.out.println("Bracketed commands indicate optional start and ends of read. End requires start");
        System.out.println("");
        System.out.println("CloseFile: 'close' identifier");

    }


}