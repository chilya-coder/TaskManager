package ua.edu.sumdu.j2se.chimyrys.tasks.controller;

import ua.edu.sumdu.j2se.chimyrys.tasks.StringUtils;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.chimyrys.tasks.model.TaskIO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataReadWriteController {

    /**
     * method that allows to write data into file using model
     * @param model AbstractTaskList
     */
    public static void writeData(AbstractTaskList model) {
        try {
            TaskIO.write(model, new FileWriter(StringUtils.FILE_PATH));
        } catch (IOException e) {
            System.out.println(StringUtils.FILE_NOT_FOUND);
        }
    }

    /**
     * method that allows to get AbstractTaskList object according to file
     * @return arrayTaskList AbstractTaskList
     */
    public static AbstractTaskList readData() {
        AbstractTaskList arrayTaskList = new ArrayTaskList();
        try {
            TaskIO.read(arrayTaskList, new FileReader(StringUtils.FILE_PATH));
        } catch (IOException e) {
            System.out.println(StringUtils.FILE_NOT_FOUND);
        }
        return arrayTaskList;
    }
}
