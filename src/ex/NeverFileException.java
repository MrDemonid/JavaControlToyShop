package ex;

public class NeverFileException extends Exception {

    public NeverFileException(String filename) {
        super(String.format("Файл '%s' не найден!%n", filename));
    }
}
