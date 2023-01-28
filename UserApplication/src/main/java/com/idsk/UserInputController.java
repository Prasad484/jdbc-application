package com.idsk;

import org.apache.log4j.Logger;

import java.util.Scanner;

public class UserInputController {
    protected static String userName, password, firstName, lastName, qualification;
    protected static Integer userIdentification;

    private static Logger logger = Logger.getLogger(UserInputController.class.getName());

    public void getInput(){
        Scanner scanner = new Scanner(System.in);

        logger.info("Enter ID:");
        userIdentification = Integer.parseInt(scanner.nextLine());

        logger.info("Enter Username:");
        userName = scanner.nextLine();

        logger.info("Enter Password:");
        password = scanner.nextLine();

        logger.info("Enter Firstname:");
        firstName = scanner.nextLine();

        logger.info("Enter Lastname:");
        lastName = scanner.nextLine();

        logger.info("Enter Qualification:");
        qualification = scanner.nextLine();

    }

}
