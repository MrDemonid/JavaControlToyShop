package model.fileio;

import ex.BadWriteLineException;
import ex.BarReadLineException;
import ex.NeverFileException;

public abstract class FileAbstract <T>{

    boolean append;
    String fileName;

    public FileAbstract(String fileName, boolean append) {
        this.fileName = fileName;
        this.append = append;
    }

    public boolean isAppend() {
        return append;
    }

    abstract public T load() throws NeverFileException, BarReadLineException;
    abstract public void save(T buffer) throws NeverFileException, BadWriteLineException;
}
