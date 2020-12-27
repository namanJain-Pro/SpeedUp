package DataModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSource {

    private static DataSource instance = new DataSource();
    private Connection connection;

    private DataSource(){
    }

    public static DataSource getInstance(){
        return instance;
    }

    public boolean open(){
        try{
            connection = DriverManager.getConnection(Constant.CONNECTION_STRING);
            return true;
        }catch (SQLException e){
            System.out.println("Couldn't connect to database "+e.getMessage());
            return false;
        }
    }
    
    public void close(){
        try{
            connection.close();
        }catch (SQLException e){
            System.out.println("Couldn't close database "+e.getMessage());
        }
    }

    public void create(){
        try(Statement statement = connection.createStatement()){
            statement.execute(Constant.CREATE_TABLE_USER);
            statement.execute(Constant.CREATE_TABLE_PRACTICE);
            statement.execute(Constant.CREATE_TABLE_TEST);
        }catch (SQLException e){
            System.out.println("Couldn't create table " + e.getMessage());
        }
    }


}



























