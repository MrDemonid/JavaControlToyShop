package model;


import ex.BadWriteLineException;
import ex.NeverFileException;
import model.fileio.TextFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Shop {
    Map<Integer, Toy> toys;

    public Shop() {
        toys = new HashMap<>();
    }

    /**
     * Добавляет в словарь новую игрушку, или прибавляет у существующей количество
     * @param toy Игрушка (существующая или нет)
     */
    public void add(Toy toy)
    {
        int id = toy.getId();
        if (toys.containsKey(id))
            toys.get(id).add(toy.getCount());  // добавляем количество
        else
            toys.put(id,toy);                  // добавляем новую игрушку
    }

    public void load(String fileName) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName)))
        {
            toys = new HashMap<>();
            toys = ((HashMap<Integer, Toy>) ois.readObject());
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }

    public void save(String fileName) throws NeverFileException, BadWriteLineException
    {
        List<String> list = new ArrayList<>();
        list.add("[");

        Iterator<Map.Entry<Integer, Toy>> elem = toys.entrySet().iterator();
        while (elem.hasNext())
        {
            Map.Entry<Integer, Toy> entry = elem.next();
            Toy toy = entry.getValue();
            if (elem.hasNext())
                list.add(toy.serialize() + ",");
            else
                list.add(toy.serialize());
        }
        list.add("]");

        // скидываем в файл
        TextFile file = new TextFile(fileName);
        file.save(list);
    }

    @Override
    public String toString()
    {
//        StringBuilder sb = new StringBuilder();
//        for (Toy toy : toys.values())
//            sb.append(toy).append("\n");
//        return sb.toString();
//
        return toys.values().stream().map(toy -> toy + "\n").collect(Collectors.joining("", "В наличии:\n", ""));
    }

}
