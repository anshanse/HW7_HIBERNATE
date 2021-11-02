package ua.GoIT_Dev2.ProjectManagementSystem.util;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

public class MessageService {

    public void queryOperationMessage(){
        String queryPromptMessage =
                "1 - The salary of developers in project\n" +
                        "2 - Developers working on the project\n" +
                        "3 - Developers with skill\n" +
                        "4 - Developers with grade\n" +
                        "5 - Project list\n" +
                        "6 - Operation with BD\n" +
                        "0 - Exit";
        System.out.println(queryPromptMessage);
    }

    public void dbOperationMessage(){
        String dbPromptMessage =
                "\nprint a formatted string for operations with tables (company, customer, developer, project, skill):\n" +
                        "    for CREATE:  create-{table}\n" +
                        "    for READ:    get-{table}-{id}\n" +
                        "    for UPDATE:  update-{table}\n" +
                        "    for DELETE:  delete-{table}-{id}\n" +
                        "    for help:    help\n" +
                        "    for exit:    exit";
        System.out.println(dbPromptMessage);
    }

    public void printResult(List<String> resultData){
        resultData.forEach(System.out::println);
        System.out.println();
    }

    public void promptFormattingInput(Field field){
        String promptMessage = String.format("input %s : ",field.getName());
        if (field.getType().equals(Integer.TYPE) || field.getName().contains("id") || field.getName().contains("Id")){
            promptMessage =String.format("Input %s (only numbers): ", field.getName());
        }
        if (field.getType().equals(Date.class)){
            promptMessage = String.format("Input %s in format (yyyy-mm-dd): ", field.getName());
        }
        if ("sex".equals(field.getName())){
            promptMessage = String.format("Input %s (m/f): ", field.getName());
        }
        System.out.println(promptMessage);
    }

    public void inputCorrectData(){
        System.out.println("Input correct command.\n");
    }
}
