class SomeController {
        private static final String DB_PATH = "src/main/java/swingy/db/users.json";
        private Database<User> db;

        public SomeController() {
                this.db = new Database<User>(User.class);

                // Someview is a view component like a form view
                this.someView.someAction(e -> {
                        String[] fieldName = { "User", "New Password" };
                        String newPassword = someView.getPasswordField();

                        // Validations
                        // ...

                        // Update object on JSON file
                        List<User> users = db.readJsonFromFile(DB_PATH);

                        int index = -1;
                        for (User user : users) {
                                index++;

                                if (user.name.equals(name)) {
                                        // Create a copy to avoid problems
                                        newUser = new User(user);
                                        newUser.password = newPassword;

                                        db.updateJsonItem(newUser, index, DB_PATH);
                                        break;
                                }
                        }
        };

        private void errorDialog(String message, Component component) {
                JOptionPane.showMessageDialog(component, message, "Error",  JOptionPane.ERROR_MESSAGE);
        }
}

class User {
        public String name;
        public String password;

        public User(String name, String password) {
                this.name = name;
                this.password = password;
        }
}
