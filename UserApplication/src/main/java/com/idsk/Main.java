package com.idsk;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        UserCredential userCredential = new UserCredential();
        UserInputController userInput = new UserInputController();
        UserInputValidator userInputValidator = new UserInputValidator();
        PostgresService postgresService = new PostgresService();

        Connection connection = postgresService.makeConnection();

        userInput.getInput();
        ResultSetMetaData resultSetMetaData;
        try {
            resultSetMetaData = postgresService.metaData(connection, "usercreds");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        userCredential.setUserID(userInput.userIdentification);
        userCredential.setUserName(userInputValidator.inputValidator(userInput.userName, resultSetMetaData, 2));
        userCredential.setPassword(userInputValidator.inputValidator(userInput.password, resultSetMetaData, 3));
        userCredential.setFirstName(userInputValidator.inputValidator(userInput.firstName, resultSetMetaData, 4));
        userCredential.setLastName(userInputValidator.inputValidator(userInput.lastName, resultSetMetaData, 5));
        userCredential.setQualification(userInputValidator.inputValidator(userInput.qualification, resultSetMetaData, 6));


        postgresService.createTable(connection, "usercreds");

        postgresService.insertPayload(connection,"usercreds", userCredential.getUserID(), userCredential.getUserName(), userCredential.getPassword(),
                userCredential.getFirstName(), userCredential.getLastName(), userCredential.getQualification());

        postgresService.fetchData(connection, "usercreds");

    }
}