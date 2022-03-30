package com.company.Commands;

import com.company.Parser.InputHelper;
import com.company.Parser.Token;
import com.company.Programm.CollectionController;

import java.util.AbstractList;

/**
 * Вывести информацию о коллекции
 */
public class InfoCommand implements Command{
    private CollectionController controller;

    public InfoCommand(CollectionController controller) {
        this.controller = controller;
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }

    @Override
    public void execute(AbstractList<Token> tokens) {
        InputHelper.displayInput(tokens);
        String type = "HashMap<Long, Route>";

        System.out.println(
                "Информация о коллекции: " + "\n" +
                        "Тип коллекции: " + type + "\n" +
                        "Дата инициализации: " + controller.initializationTime + "\n" +
                        "Количество элементов в коллекции: " + controller.map.size()
        );
    }
}