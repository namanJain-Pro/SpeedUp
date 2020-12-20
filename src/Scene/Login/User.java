package Scene.Login;

import java.io.Serializable;

public class User implements Serializable {

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

