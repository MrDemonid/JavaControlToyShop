package model.fileio;

import ex.BadWriteLineException;
import ex.BarReadLineException;
import ex.NeverFileException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileText extends FileAbstract<List<String>> {

    public FileText(String fileName, boolean append) {
        super(fileName, append);
    }

    /*
    ------------------------------------------------------
    Метод для Java 7+
    ------------------------------------------------------
    import ex.ReadAllLinesException;
    import java.nio.file.Files;
    import java.nio.file.Paths;

    @Override
    public List<String> load() throws ReadAllLinesException
    {
        List<String> out;
        try {
            out = Files.readAllLines(Paths.get(fileName));
        } catch (IOException e)
        {
            throw new ReadAllLinesException(fileName);
        }
        return out;
    }
    */


    /**
     * Чтение текстового файла в список строк, с пробросом возможных исключений на уровень вверх.
     * @return Список строк
     */
    @Override
    public List<String> load() throws NeverFileException, BarReadLineException
    {
        List<String> out = new ArrayList<>();
        try (BufferedReader f = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8)))
        {
            while (f.ready())
                out.add(f.readLine());

        } catch (FileNotFoundException e) {
            throw new NeverFileException(fileName);
        } catch (IOException e) {
            throw new BarReadLineException(fileName);
        }

        return out;
    }

    /**
     * Запись в текстовый файл, с пробросом возможных исключений на уровень вверх.
     * @param buffer Список строк
     */
    @Override
    public void save(List<String> buffer) throws NeverFileException, BadWriteLineException
    {
        try (FileWriter f = new FileWriter(fileName, StandardCharsets.UTF_8, isAppend()))
        {
            for (String s : buffer)
                f.append(s).append("\r\n");

        } catch (FileNotFoundException e) {
            throw new NeverFileException(fileName);
        } catch (IOException e) {
            throw new BadWriteLineException(fileName);
        }
    }
}
