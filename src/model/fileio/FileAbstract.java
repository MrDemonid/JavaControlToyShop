package model.fileio;

import ex.BadWriteLineException;
import ex.BarReadLineException;
import ex.NeverFileException;

public abstract class FileAbstract <T>{

    String fileName;

    public FileAbstract(String fileName) {
        this.fileName = fileName;
    }

    abstract public T load() throws NeverFileException, BarReadLineException;
    abstract public void save(T buffer) throws NeverFileException, BadWriteLineException;
}
