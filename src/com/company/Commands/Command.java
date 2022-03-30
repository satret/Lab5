package com.company.Commands;

import com.company.Parser.Token;

import java.util.AbstractList;

public interface Command{

    /**
     * @return Имя команды без аргументов
     */
    String getName();

    /**
     * @return Описание команды
     */
    String getDescription();

    /**
     * @param tokens Принимает список токенов - спарсенных аргументов данной команды
     * @throws Exception
     */
    void execute(AbstractList<Token> tokens) throws Exception;
}