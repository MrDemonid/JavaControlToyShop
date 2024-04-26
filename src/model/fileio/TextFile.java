package model.fileio;

import ex.BadWriteLineException;
import ex.BarReadLineException;
import ex.NeverFileException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class TextFile extends FileBase {

    public TextFile(String fileName) {
        super(fileName);
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
     * @throws NeverFileException
     * @throws BarReadLineException
     */
    @Override
    public List<String> load() throws NeverFileException, BarReadLineException
    {
        List<String> out = new ArrayList<>();
        try (BufferedReader f = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8)))
        {
            while (f.ready())
            {
                out.add(f.readLine());
            }

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
     * @throws NeverFileException
     * @throws BadWriteLineException
     */
    @Override
    public void save(List<String> buffer) throws NeverFileException, BadWriteLineException
    {
        try (FileWriter f = new FileWriter(fileName, StandardCharsets.UTF_8))
        {
            for (String s : buffer) {
                f.append(s).append("\n");
            }
        } catch (FileNotFoundException e) {
            throw new NeverFileException(fileName);
        } catch (IOException e) {
            throw new BadWriteLineException(fileName);
        }
    }


}
