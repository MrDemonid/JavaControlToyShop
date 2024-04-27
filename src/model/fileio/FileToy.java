package model.fileio;

import ex.BadWriteLineException;
import ex.BarReadLineException;
import ex.NeverFileException;

import java.util.List;

public class FileToy extends FileText {

    public FileToy(String fileName) {
        super(fileName);
    }

    @Override
    public List<String> load() throws NeverFileException, BarReadLineException
    {
        List<String> src = super.load();
        return src;
    }

    @Override
    public void save(List<String> buffer) throws NeverFileException, BadWriteLineException
    {

        return;
    }

}
