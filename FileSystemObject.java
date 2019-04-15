public abstract class FileSystemObject {
    protected String name;

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

    }

    ///Add any additonal functions *here* Implement them in kids

}