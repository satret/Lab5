package com.company.Commands;

import com.company.Data.Route;
import com.company.Parser.InputHelper;
import com.company.Parser.Token;
import com.company.Programm.CommandManager;
import com.company.Programm.CollectionController;
import java.util.AbstractList;

import java.util.AbstractList;

/**
 * Вывести список элементов коллекции
 */
public class ShowCommand implements Command {
    private CollectionController controller;

    public ShowCommand(CollectionController controller){
        this.controller = controller;
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public void execute(AbstractList<Token> tokens) {
        InputHelper.displayInput(tokens);
        if(controller.map.size() == 0){
            System.out.println("Коллекция пуста.");
        }

        for(long key : controller.map.keySet()){
            Route r = controller.map.get(key);
            System.out.println(r.toString() + '\n');
        }
    }
}