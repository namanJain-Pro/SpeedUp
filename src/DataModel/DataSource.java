package DataModel;

import java.sql.*;

public class DataSource {

    private static DataSource instance = new DataSource();
    private Connection connection;

    private PreparedStatement insert_user;
    private PreparedStatement query_user_username;
    private PreparedStatement query_user_auth;
    private PreparedStatement track_user;
    private PreparedStatement query_user_info;
    private PreparedStatement get_user;
    private PreparedStatement insert_practice_record;

    private DataSource(){
    }

    public static DataSource getInstance(){
        return instance;
    }

    public boolean open(){
        try{
            connection = DriverManager.getConnection(Constant.CONNECTION_STRING);
            insert_user = connection.prepareStatement(Constant.INSERT_USER);
            query_user_username = connection.prepareStatement(Constant.QUERY_USER_USERNAME);
            query_user_auth = connection.prepareStatement(Constant.QUERY_AUTHENTICATION);
            track_user = connection.prepareStatement(Constant.TRACK_LOGIN_STATUS);
            query_user_info = connection.prepareStatement(Constant.QUERY_SIGNED_USER_INFO);
            get_user = connection.prepareStatement(Constant.GET_USER_INFO);
            insert_practice_record = connection.prepareStatement(Constant.INSERT_PRACTICE_RECORD);
            return true;
        }catch (SQLException e){
            System.out.println("Couldn't connect to database "+e.getMessage());
            return false;
        }
    }
    
    public void close(){
        try{
            if(track_user != null){
                track_user.close();
            }
            if(query_user_username != null){
                query_user_username.close();
            }
            if(query_user_auth != null){
                query_user_auth.close();
            }
            if(insert_user != null){
                insert_user.close();
            }
            if(query_user_info != null){
                query_user_info.close();
            }
            if(get_user != null){
                get_user.close();
            }
            if(insert_practice_record != null){
                insert_practice_record.close();
            }
            if(connection != null){
                connection.close();
            }
        }catch (SQLException e){
            System.out.println("Couldn't close database "+e.getMessage());
        }
    }

    public void create(){
        try(Statement statement = connection.createStatement()){
            statement.execute(Constant.CREATE_TABLE_USER);
            statement.execute(Constant.CREATE_TABLE_LOGIN_STATUS);
            statement.execute(Constant.CREATE_TABLE_PRACTICE_RECORD);
            statement.execute(Constant.CREATE_TABLE_TEST_RECORD);
        }catch (SQLException e){
            System.out.println("Couldn't create table " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean insertUser(String name, String email, String password, String dateOfBirth){
        try{
            query_user_username.setString(1, name);
            ResultSet result = query_user_username.executeQuery();

            if(result.next()) {
                return false;
            }else{
                insert_user.setString(1, name);
                insert_user.setString(2, email);
                insert_user.setString(3, password);
                insert_user.setString(4, dateOfBirth);

                int affectedRow = insert_user.executeUpdate();

                if (affectedRow != 1){
                    System.out.println("Couldn't insert user");
                    return false;
                }

                ResultSet resultSet = insert_user.getGeneratedKeys();
                if(resultSet.next()){
                    trackUserLogin(name, password);
                }

                return true;
            }
        } catch (SQLException e){
            System.out.println("couldn't insert user " + e.getMessage());
            return false;
        }
    }

    public boolean checkUserLoginStatus(){
        try(Statement statement = connection.createStatement()){
            ResultSet result = statement.executeQuery(Constant.QUERY_STATS_TO_FIND_CURRENT_USER);

            if(!result.next()){
                return false;
            }

            return true;
        } catch (SQLException e){
            System.out.println("Problem in checking user status "+ e.getMessage());
            return false;
        }
    }

    private void trackUserLogin(String username, String password){
        try {
            track_user.setString(1, username);
            track_user.setString(2, password);
            track_user.setInt(3, 1);

            track_user.execute();
        } catch (SQLException e){
            System.out.println("Couldn't update login status " + e.getMessage());
        }
    }

    public void changeLoginStatusTo0() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(Constant.UPDATE_LOGIN_STATUS_TO_0);
        } catch (SQLException e) {
            System.out.println("Couldn't update to 0 " + e.getMessage());
        }
    }

    public boolean checkUser(String input, String password) {
        try {

            query_user_auth.setString(1, input);
            query_user_auth.setString(2, input);
            query_user_auth.setString(3, password);

            ResultSet auth = query_user_auth.executeQuery();

            if(auth.next()){
                trackUserLogin(input, password);
                return true;
            }

            return false;
        } catch (SQLException e){
            System.out.println("Problem in checking user data " + e.getMessage());
            return false;
        }
    }

    public User getCurrentUser(){
        try(ResultSet result1 = query_user_info.executeQuery()) {
            String username = result1.getString(1);
            get_user.setString(1, username);
            ResultSet resultSet = get_user.executeQuery();

            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setEmail(resultSet.getString(3));
            user.setPassword(resultSet.getString(4));
            user.setDateOfBirth(resultSet.getString(5));

            return user;
        } catch (SQLException e){
            System.out.println("Problem in gaining user info " + e.getMessage());
            return null;
        }
    }

    public boolean updatePracticeRecord(String diff, int length, int speed, int error){
        try {
            User user = getCurrentUser();
            insert_practice_record.setInt(1, user.getId());
            insert_practice_record.setString(2, diff);
            insert_practice_record.setInt(3, length);
            insert_practice_record.setInt(4, speed);
            insert_practice_record.setInt(5, error);

            insert_practice_record.execute();

            return true;
        } catch (SQLException e){
            System.out.println("Couldn't entry practice record "+e.getMessage());
            return false;
        }
    }
}



























