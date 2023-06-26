package swingy.controllers;

import java.awt.Component;
import javax.swing.JOptionPane;
import java.util.*;

import swingy.models.*;
import swingy.views.*;
import swingy.validations.ValidateUser;

public class UserController {
        private static final String DB_PATH = "src/main/java/swingy/db/users.json";
        private Database<User> db;
        private UserFormView form;
        private UserDetailsView details;
        private HomeView homeView;

        public UserController(UserFormView formView, UserDetailsView detailsView, HomeView homeView) {
                this.db = new Database<User>(User.class);
                this.form = formView;
                this.details = detailsView;
                this.homeView = homeView;

                // Create user
                this.form.submitData(e -> {
                        String[] fieldNames = { "name", "password" };
                        List<String> emptyFields = new ArrayList<String>(2);

                        for (String fieldName : fieldNames) {
                                String fieldValue = this.form.getFieldValue(fieldName);
                                if (fieldValue == null || fieldValue.trim().isEmpty()) {
                                        emptyFields.add(fieldName);
                                }
                        }
                        
                        if (!emptyFields.isEmpty()) {
                                String errorMessage = "The following fields are empty: " + String.join(", ", emptyFields);
                                errorDialog(errorMessage, this.form);
                                return; // Exit the method if there are empty fields
                        }

                        String name = this.form.getFieldValue("name");
                        String password = this.form.getFieldValue("password");

                        if (!ValidateUser.validatePassword(password)) {
                                errorDialog("Invalid Password", this.form);
                                return; // Exit the method if there are empty fields
                        };

                        User user = new User(name, password);
                        this.db.writeJsonToFile(user, DB_PATH);
                        this.form.reset();

                        JOptionPane.showMessageDialog(this.form, "User created", "Success", JOptionPane.INFORMATION_MESSAGE);
                });

                this.homeView.showUserDetails(e -> {
                        List<User> users = this.db.readJsonFromFile(DB_PATH);
                        this.details.displayUsers(users);
                });
        }

        public void errorDialog(String message, Component component) {
                JOptionPane.showMessageDialog(component, message, "Error",  JOptionPane.ERROR_MESSAGE);
        }
}
