package com.company.Commands;

import com.company.Parser.InputHelper;
import com.company.Parser.Token;

import java.util.AbstractList;

/**
 * Команда, которая позволяет завершить программу или выполнение скрипта
 */
public class ExitCommand implements Command {
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "завершить программу (без сохранения в файл)";
    }

    @Override
    public void execute(AbstractList<Token> tokens) {
        InputHelper.displayInput(tokens);
        System.out.println("Выход.");
    }
}