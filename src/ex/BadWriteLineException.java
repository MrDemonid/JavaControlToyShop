package ex;

import java.io.IOException;

public class BadWriteLineException extends IOException {

    public BadWriteLineException(String filename) {
        super(String.format("Ошибка записи в файл '%s'%n", filename));
    }
}
