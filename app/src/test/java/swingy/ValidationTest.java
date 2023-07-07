package swingy;

import swingy.validations.ValidateUser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {
        @Test void validateRun() {
                // Basic
                assertFalse(ValidateUser.validatePassword(""));
                assertFalse(ValidateUser.validatePassword(" "));
                assertFalse(ValidateUser.validatePassword("12345678912"));
                assertFalse(ValidateUser.validatePassword("1234567891"));

                // Must pass
                assertTrue(ValidateUser.validatePassword("Avaskldj1234"));
                assertTrue(ValidateUser.validatePassword("PassWord123123"));
                assertTrue(ValidateUser.validatePassword("asdasdUpper123"));

                // Will it break?
                assertFalse(ValidateUser.validatePassword("asdjksahdsajhdjsahdsjakdhsajkdhsajkdh"));
                assertFalse(ValidateUser.validatePassword("ajshdsadASDsad123213213"));
                assertFalse(ValidateUser.validatePassword("ASDasdsad   d123123"));
                assertFalse(ValidateUser.validatePassword("                    "));
        }
}
