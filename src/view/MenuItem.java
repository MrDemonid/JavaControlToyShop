package view;

public class MenuItem {

    private int id;
    private String text;
    private Menu submenu;

    public MenuItem(int id, String text, Menu submenu)
    {
        this.id = id;
        this.text = text;
        this.submenu = submenu;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Menu getSubmenu() {
        return submenu;
    }

    @Override
    public String toString()
    {
        return String.format("%-30s", text);
    }

}
