package ua.GoIT_Dev2.ProjectManagementSystem.util;

import lombok.SneakyThrows;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Scanner;

public class DataValidator {

    public boolean validateInputData (String inputData, Field field){
        String textMatch = "[A-Za-z0-9\\s]+";
        if (field.getType().equals(Integer.TYPE) || field.getName().contains("id") || field.getName().contains("Id")){
            textMatch = "\\d+";
        }
        if (field.getType().equals(Date.class)){
            textMatch = "\\d{4}-\\d{2}-\\d{2}";
        }
        if ("sex".equals(field.getName())){
            textMatch = "[mf]";
        }
        return inputData.matches(textMatch);
    }

}
