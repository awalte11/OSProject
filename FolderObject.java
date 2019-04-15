import java.util.ArrayList;

public class FolderObject extends FileSystemObject {
    ArrayList<FileSystemObject> children = new ArrayList<FileSystemObject>();//should probably replace with arrays. But I don't have the energy for shrink/expand array code right now

    public void add(FileSystemObject child)
    {
        children.add(child);
    }
    public void remove(FileSystemObject child)
    {
        children.remove(child);
    }
    public FileSystemObject getChild(int i)//gonna need more of these. possibly with lambda expression support or something
    {
        return children.get(i);
    }

}