package view;

public class MenuItem {

    protected String text;
    private MenuCmd id;
    protected Menu submenu;

    public MenuItem(MenuCmd id, String text, Menu submenu)
    {
        this.text = text;
        this.id = id;
        this.submenu = submenu;
    }

    public MenuCmd getId()
    {
        return id;
    }

    public Menu getSubmenu() {
        return submenu;
    }

    @Override
    public String toString()
    {
        String res = String.format("%-30s", text);
        if (submenu != null)
            res += "->";
        return res;
    }

}
