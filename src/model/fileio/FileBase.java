package model.fileio;

import java.util.List;

public abstract class FileBase {

    protected String fileName;

    public FileBase(String fileName) {
        this.fileName = fileName;
    }

    abstract public List<String> load() throws Exception;
    abstract public void save(List<String> buffer) throws Exception;

}
