public abstract class FileSystemObject {
    protected String name;
    protected String type;
    protected DateTime updated;

    public void add(FileSystemObject child)
    {
        throw new UnsupportedOperationException();
    }
    public void remove(FileSystemObject child)
    {
        throw new UnsupportedOperationException();
    }
    public FileSystemObject getChild(int i)//gonna need more of these. possibly with lambda expression support or something
    {
        throw new UnsupportedOperationException();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
        refreshModified();
    }

    public void setType(String type)
    {
        this.type = type;
        refreshModified();
    }

    public String getType()
    {
        return type;
    }

    public FileSystemObject(String name, String type)
    {
        this.name = name;
        this.type = type;
        this.updated = new DateTime();

    }

    public void refreshModified()
    {
        updated = new DateTime();
    }



    ///All functions in file or folder class must be declared here.
    ///Composite pattern recommends you also have a default implementation here.
    ///If it doesn't make sense for a file - have the default throw the exception
    ///If it doesnt' make sense for a folder - have the folder implementation throw the default.
    ///I'll set up the iterator shortly -Alex

}