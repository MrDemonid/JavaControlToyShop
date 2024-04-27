package model;

import java.io.*;
import java.util.List;

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

    /**
     * Добавить одну игрушку
     */
    public void add(int num)
    {
        count += num;
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

    /**
     * Из строки вида: "id":1,"name":"Машинка","count":5,"probability":12
     * создаёт экземпляр класса Toy
     */
    public boolean load(String data)
    {
        int id = -1;
        String name = "";

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
                    //
                    int end = param[1].lastIndexOf("\"");
                    if (end == -1)
                        end = param[1].length();
                    name = param[1].substring(param[1].indexOf("\"")+1, end);
                    break;
                case "\"count\"":
                    this.count = getInt(param[1], 0);
                    break;
                case "\"probability\"":
                    this.probability = getInt(param[1], 0);
                    break;
            }
        }
        if (id != -1 && !name.isBlank())
        {
            this.id = id;
            this.name = name;
            return true;
        }
        return false;
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

    @Override
    public String toString()
    {

        return String.format("[%d], \"%s\", %d шт.", id, name, count);
    }

    /**
     * Возвращает данные объекта в текстовом виде, пригодном для записи
     */
    public String serialize()
    {
        return String.format("{\n    \"id\" : %d,\n    \"name\" : \"%s\",\n    \"count\" : %d,\n    \"probability\" : %d\n}", id, name, count, probability);
    }

    /**
     * Конвертирует строку ("id":1,"name":"Машинка","count":5,"probability":12) в значения
     */
    public void deserialize(String source)
    {
        String[] s = source.replaceAll("\\s+", "").split(",");
        for (String string : s) {
            System.out.println("d = " + string);

        }
    }


}
