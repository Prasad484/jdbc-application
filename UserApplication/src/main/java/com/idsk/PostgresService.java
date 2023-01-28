package com.idsk;

import org.apache.log4j.Logger;

import java.sql.*;

public class PostgresService {
    private static Logger logger = Logger.getLogger(PostgresService.class.getName());
    public Connection makeConnection(){
        Connection connection = null;

        try{
            Class.forName("org.postgresql.Driver");
            connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/userdatabase",
                    "postgres", "root");

            if(connection!=null){
                logger.info("Connection Established");
            }
            else{
                logger.info("Connection Failed");
            }

        }catch (Exception e){
              logger.error(e.getMessage(), e);
        }

        return connection;
    }

    public ResultSetMetaData metaData(Connection connection,String tableName) throws SQLException {
        Statement statement;
        ResultSetMetaData resultSetMetaData = null;
        try {
            String query = String.format("SELECT * FROM %s", tableName);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSetMetaData = resultSet.getMetaData();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return resultSetMetaData;

    }

    public void createTable(Connection connection, String tableName){
        Statement statement;


        try {
            String query = "CREATE TABLE IF NOT EXISTS "+ tableName+"(\n" +
                    "  user_id INT PRIMARY KEY,\n" +
                    "  username varchar(25) NOT NULL,\n" +
                    "  password varchar(30) NOT NULL,\n" +
                    "  firstname varchar(30) NOT NULL,\n" +
                    "  lastname varchar(30) NOT NULL,\n" +
                    "  qualification varchar(30) NOT NULL), \n" +
                    "  UNIQUE(username))";

            statement = connection.createStatement();
            statement.executeUpdate(query);

        }catch (Exception e){
            logger.error(e.getMessage(), e);

        }
    }

    public void insertPayload(Connection connection, String tableName, Integer id, String userName, String passWord, String firstName,
                              String lastName, String qualification){
        Statement statement;
        try {
            String insertQuery = String.format("INSERT INTO %s(user_id,username,password,firstname,lastname,qualification) values('%s', '%s', '%s', '%s', '%s', '%s');",
                    tableName,id,userName,passWord,firstName,lastName,qualification);

            statement = connection.createStatement();
            statement.executeUpdate(insertQuery);
            logger.info("Row Inserted");

        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
    }

    public void fetchData(Connection connection,String tableName){
        Statement statement;
        try {
            String selectQuery = String.format("SELECT * FROM %s", tableName);

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()){
                System.out.println(resultSet.getInt("user_id"));
                System.out.println(resultSet.getString("username"));
                System.out.println(resultSet.getString("password"));
                System.out.println(resultSet.getString("firstname"));
                System.out.println(resultSet.getString("lastname"));
                System.out.println(resultSet.getString("qualification"));
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }

    }
}