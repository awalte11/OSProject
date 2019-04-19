public class FileTree
{
    private FileSystemObject root;


    public FileSystemObject getRoot()
    {
        return root;
    }


    public FileTree()
    {
        root = new FolderObject("root");

    }

    //TODO ADD LINKEDLIST that's the current path

}