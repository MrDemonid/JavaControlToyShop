package view;

/**
 * Пришлось Menu и MenuItem делать общего предка, для обхода ошибок типизации
 */
public abstract class MenuBase {

    protected String text;

    public MenuBase(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String toString()
    {
        return String.format("%-30s", text);
    }
}
