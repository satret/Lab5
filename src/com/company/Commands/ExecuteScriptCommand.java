package com.company.Commands;

import com.company.Parser.InputHelper;
import com.company.Parser.Parser;
import com.company.Parser.Token;
import com.company.Programm.CollectionController;
import com.company.Programm.CommandManager;
import com.company.Programm.Programm;

import java.io.*;
import java.util.*;

/**
 * Выполнить указанный скрипт в файле
 */
public class ExecuteScriptCommand implements Command{
    CollectionController controller;
    CommandManager commandManager;

    static Set<String> executingScripts = new HashSet<>();

    public ExecuteScriptCommand(CommandManager commandManager,CollectionController controller){
        this.commandManager = commandManager;
        this.controller = controller;
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме / execute_script \"file_name\"";
    }

    @Override
    public void execute(AbstractList<Token> tokens) throws Exception {
        InputHelper.displayInput(tokens);

        String path;
        try{
            path = Parser.parseString(tokens.get(1));
        }
        catch (Exception e){
            throw new Exception("Парсинг агрумента file_name не удался. " + e.getMessage());
        }

        File script = new File(path);

        if(!script.exists()){
            throw new Exception("Файла со скриптом не существует!");
        }
        if(!script.canRead()){
            throw new Exception("Нет прав на чтение файла со скриптом!");
        }
        if(executingScripts.contains(path)){
            throw new Exception("Этот скрипт уже выполняется, в целях избежания рекурсии его выполнение запрещено.");
        }

        Scanner fileScanner = new Scanner(new BufferedInputStream(new FileInputStream(script)));

        System.out.println("\uD83D\uDCC1 Началось выполнение скрипта");
        executingScripts.add(path);
        Programm.run(new CommandManager(controller, fileScanner), fileScanner);
        executingScripts.remove(path);
        System.out.println("\uD83D\uDCC1 Выполение скрипта завершено");
    }
}