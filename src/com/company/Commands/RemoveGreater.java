package com.company.Commands;

import com.company.Data.Route;
import com.company.Parser.InputHelper;
import com.company.Parser.Parser;
import com.company.Parser.Token;
import com.company.Programm.CollectionController;

import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Удалить из коллекции все элементы, ключ которых меньше, чем заданный
 */
public class RemoveGreater implements Command{
    private CollectionController controller;

    public  RemoveGreater(CollectionController controller){
        this.controller = controller;
    }

    @Override
    public String getName() {
        return "remove_lower_key";
    }

    @Override
    public String getDescription() {
        return "удалить из коллекции все элементы, ключ которых меньше, чем заданный / remove_lower_key id";
    }

    @Override
    public void execute(AbstractList<Token> tokens) throws Exception {
        InputHelper.displayInput(tokens);
        long id;
        try{
            id = Parser.parseLong(tokens.get(1));
        }
        catch (Exception e){
            throw new Exception("Парсинг агрумента distance не удался. " + e.getMessage());
        }

        ArrayList<Long> keys = new ArrayList<Long>();
        for(long key : controller.map.keySet()){
            keys.add(key);
        }

        int count = 0;
        for(long key : keys){
            Route r = controller.map.get(key);
            if(r.getId() > id){
                controller.map.remove(key);
                count++;
            }
        }

        System.out.println(String.format("Из коллекции успешно удалено %d элементов.", count));
    }
}