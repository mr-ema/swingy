package swingy.validations;

public class ValidateUser {
        public static boolean validatePassword(String password) {
                // min 12 chars - max 20 chars
                // only letters and numbers
                String regex = "^[A-Za-z0-9]{12,20}$"; 

                return password.matches(regex);
        }
}
