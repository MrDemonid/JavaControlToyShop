package model;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
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

    public void save(String fileName) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName)))
        {
            oos.writeObject(toys);
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
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
