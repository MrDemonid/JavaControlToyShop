package ex;

import java.io.IOException;

public class ReadAllLinesException extends IOException {

    public ReadAllLinesException(String filename)
    {
        super(String.format("Ошибка открытия/чтения файла '%s'%n", filename));
    }
}
