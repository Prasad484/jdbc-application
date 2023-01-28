package com.idsk;

import org.apache.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class UserInputValidator {
        private static Logger logger = Logger.getLogger(UserInputValidator.class.getName());

        public String inputValidator(String userInput,  ResultSetMetaData resultSetMetaData, Integer column) {
                try {
                        int size = resultSetMetaData.getPrecision(column);
                        if (userInput.getBytes(StandardCharsets.UTF_8).length > size) {
                                logger.error(String.format("The provided value for the column is too long for the column's type. Column: %s", resultSetMetaData.getColumnName(column)));
                                System.exit(0);
                        }
                        return userInput;
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }


        }
}
