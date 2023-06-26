package swingy.models;

public class User {
        public String name;
        public String password;

        // Empty contructor (optional)
        public User() {};

        public User(String name, String password) {
                this.name = name;
                this.password = password;
        }
}
