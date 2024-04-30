package model;

public class Toy {
    private int id;
    private String name;
    private int count;
    private int probability;

    public Toy(int id, String name, int count, int probability)
    {
        this.id = id;
        this.name = name;
        this.count = count;
        this.probability = probability;
    }

    public Toy()
    {
        this(-1, "", 0, 0);
    }

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public int getProbability() {
        return probability;
    }

    public void setCount(int count) {
        if (count >= 0)
            this.count = count;
    }

    public void setProbability(int probability) {
        if (probability >= 0 && probability <= 100)
            this.probability = probability;
    }

    /**
     * Получить одну игрушку
     * @return true - если успешно
     */
    public boolean give()
    {
        if (count <= 0)
            return false;
        count--;
        return true;
    }


    @Override
    public String toString()
    {
        String name = String.format("\"%s\"", this.name);
        return String.format("%-30s %8d шт.,  Артикул: %-8d, Вероятность: %2d%%", name, count, id, probability);
    }

    /**
     * Возвращает данные объекта в текстовом виде, пригодном для записи
     */
    public String serialize()
    {
        return String.format("{\n    \"id\" : %d,\n    \"name\" : \"%s\",\n    \"count\" : %d,\n    \"probability\" : %d\n}", id, name, count, probability);
    }

    /**
     * Принимает значения для полей из строки.
     * @param data Строка вида: "id":1,"name":"Машинка","count":5,"probability":12
     * @return true - если успешно (поля обновлены) и false при неудаче (поля не меняютсяся)
     */
    public boolean deserialize(String data)
    {
        int id = -1;
        String name = "";
        int count = 0;
        int probability = 0;

        String[] s = data.replaceAll("\\s+", "").split(",");
        for (String string : s)
        {
            String[] param = string.split(":");
            switch (param[0].toLowerCase())
            {
                case "\"id\"":
                    id = getInt(param[1], -1);
                    break;
                case "\"name\"":
                    name = getString(param[1]);
                    break;
                case "\"count\"":
                    count = getInt(param[1], 0);
                    break;
                case "\"probability\"":
                    probability = getInt(param[1], 0);
                    break;
            }
        }
        if (id != -1 && !name.isBlank() && count >= 0 && probability >= 0 && probability <= 100)
        {
            this.id = id;
            this.name = name;
            this.count = count;
            this.probability = probability;
            return true;
        }
        return false;
    }

    private String getString(String source)
    {
        int start = source.indexOf("\"");
        int end = source.lastIndexOf("\"");
        if (start == -1 || end == -1 || start == end)
            return "";
        return source.substring(start+1, end);
    }

    private int getInt(String source, int def) {
        int res;
        try {
            res = Integer.parseInt(source);
        } catch (NumberFormatException e) {
            res = def;
        }
        return res;
    }

}
