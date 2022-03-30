package com.company.Commands;

import com.company.Parser.InputHelper;
import com.company.Parser.Token;
import com.company.Programm.CollectionController;

import java.util.AbstractList;

public class ClearCommand implements Command {
    private CollectionController controller;

    public ClearCommand(CollectionController controller){
        this.controller = controller;
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "очистить коллекцию";
    }

    public void execute(AbstractList<Token> tokens) {
        InputHelper.displayInput(tokens);
        controller.map.clear();
        System.out.println("Все элементы успешно удалены из коллекции.");
    }
}