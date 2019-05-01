import java.io.*;
import java.util.Arrays;
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
            System.out.println(tree.getPath() +":");
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

                default:
                    System.out.println("Invalid command");
            }

        }


        input.close();

    }



    
    public static void help() {

    }


}