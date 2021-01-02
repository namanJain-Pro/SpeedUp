package Scene.Login;

import DataModel.DataSource;

public class LoginDataHandle {

    public boolean createNewAccount(String name, String email, String password, String dateOfBirth){
        return DataSource.getInstance().insertUser(name, email, password, dateOfBirth);
    }

    public boolean loginHandle(String input, String password){
        return DataSource.getInstance().checkUser(input, password);
    }

}
