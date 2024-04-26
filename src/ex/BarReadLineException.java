package ex;

import java.io.IOException;

public class BarReadLineException extends IOException {

    public BarReadLineException(String filename) {
        super(String.format("Ошибка чтения из файла '%s'%n", filename));
    }
}
