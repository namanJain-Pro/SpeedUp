package Scene.Login;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class LoginDataHandle {

    private static final HashMap<String, User> accountsWithKeyUsername = new HashMap<>();
    private static final HashMap<String, User> accountsWithKeyEmail = new HashMap<>();

    private static class User implements Serializable{

        private final long serialVersionUID = 1L;

        private final String name;
        private final String email;
        private final String password;
        private final String dateOfBirth;

        public User(String name, String email, String password, String dateOfBirth) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.dateOfBirth = dateOfBirth;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }
    }

    public boolean verificationWithUsername(String userName, String password){
        if(accountsWithKeyUsername.containsKey(userName)){
            User user = accountsWithKeyUsername.get(userName);
            return user.getPassword().equals(password);
        }

        return false;
    }

    public boolean verificationWithEmail(String email, String password){
        if(accountsWithKeyEmail.containsKey(email)){
            User user = accountsWithKeyEmail.get(email);
            return user.getPassword().equals(password);
        }

        return false;
    }

    public int createNewAccount(String name, String email, String password, String dateOfBirth){
        if(accountsWithKeyUsername.containsKey(name)){
            return 1;
        }
        if(accountsWithKeyEmail.containsKey(email)){
            return 2;
        }

        User user = new User(name, email, password, dateOfBirth);

        Path filePath = FileSystems.getDefault().getPath("src/DataFiles/userAccount.dat");
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(filePath)))) {
            for(User oldUser : accountsWithKeyUsername.values()){
                outputStream.writeObject(oldUser);
            }
            outputStream.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

        accountsWithKeyEmail.put(user.getEmail(), user);
        accountsWithKeyUsername.put(user.getName(), user);

        return 0;
    }

    static {
        Path filePath = FileSystems.getDefault().getPath("src/DataFiles/userAccount.dat");

        try(ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(filePath)))){
            boolean eof = false;
            while(!eof){
                try{
                    User user = (User) inputStream.readObject();
                    accountsWithKeyEmail.put(user.getEmail(), user);
                    accountsWithKeyUsername.put(user.getName(), user);
                } catch (EOFException e){
                    eof = true;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
