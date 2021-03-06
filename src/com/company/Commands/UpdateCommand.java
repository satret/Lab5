package com.company.Commands;

import com.company.Data.Route;
import com.company.Parser.InputHelper;
import com.company.Parser.Token;
import com.company.Programm.CollectionController;

import java.util.AbstractList;
import java.util.Scanner;

/**
 * Обновить значение элемента коллекции
 */
public class UpdateCommand implements Command{
    CollectionController controller;
    Scanner scanner;

    public UpdateCommand(CollectionController controller, Scanner scanner){
        this.controller = controller;
        this.scanner = scanner;
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescription() {
        return "обновить значение элемента коллекции, id которого равен заданному / update id \"name\" distance";
    }

    @Override
    public void execute(AbstractList<Token> tokens) throws Exception {
        InputHelper.displayInput(tokens);

        if(tokens == null){
            throw new IllegalArgumentException("Сисок токенов не может быть равен null!");
        }

        // Токенов должно быть 4: название команды и 3 аргумента
        if(tokens.size() != 4){
            throw new IllegalArgumentException("Аргументов этой команды должно быть 3.");
        }

        Route newRoute = new Route();

        InputHelper.receiveId(newRoute, tokens.get(1));

        if(!controller.map.containsKey(newRoute.getId())){
            throw new Exception("Элемента с таким индексом не существует!");
        }

        InputHelper.receiveName(newRoute, tokens.get(2));
        InputHelper.receiveDistance(newRoute, tokens.get(3));

        InputHelper.receiveCoordinates(newRoute, scanner);
        InputHelper.receiveFrom(newRoute, scanner);
        InputHelper.receiveTo(newRoute, scanner);

        Route storedRoute = controller.map.get(newRoute.getId());
        controller.map.remove(storedRoute);
        controller.map.put(newRoute.getId(), newRoute);

        System.out.println(String.format("Значение элемента с id %d успешно обновлено.", newRoute.getId()));
    }
}
