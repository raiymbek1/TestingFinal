
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    User user;




    @BeforeEach
    void setUp() {

        user = new User() {
            @Override
            public int compareTo(Object o) {
                return 0;
            }
        };
    }

    @Test
    @DisplayName("Simple multiplication should work")
    void getName() {
        assertEquals("Sanat", user.getName(),
                "Regular multiplication should work");
    }



    @RepeatedTest(5)
    @DisplayName("Ensure correct handling of zero")
    void checkName() {
        assertEquals("", user.getName(), "Multiple with zero should be zero");
    }
}
