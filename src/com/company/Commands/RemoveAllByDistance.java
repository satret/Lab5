package com.company.Commands;

import com.company.Data.Route;
import com.company.Parser.InputHelper;
import com.company.Parser.Parser;
import com.company.Parser.Token;
import com.company.Programm.CollectionController;

import java.util.AbstractList;

/**
 * Удалить из коллекции все элементы, значение distance котороых равно данному
 */
public class RemoveAllByDistance implements Command{
    private CollectionController controller;

    public  RemoveAllByDistance(CollectionController controller){
        this.controller = controller;
    }

    @Override
    public String getName() {
        return "filter_greater_than_distance";
    }

    @Override
    public String getDescription() {
        return "вывести элементы, значение поля distance которых больше заданного / filter_greater_than_distance distance";
    }

    @Override
    public void execute(AbstractList<Token> tokens) throws Exception {
        InputHelper.displayInput(tokens);
        boolean wasPrint = false;

        long distance;
        try{
            distance = Parser.parseLong(tokens.get(1));
        }
        catch (Exception e){
            throw new Exception("Парсинг агрумента distance не удался. " + e.getMessage());
        }

        for(long key : controller.map.keySet()){
            Route r = controller.map.get(key);
            if(r.getDistance() == distance){
                controller.map.remove(key);
            }
        }

    }
}