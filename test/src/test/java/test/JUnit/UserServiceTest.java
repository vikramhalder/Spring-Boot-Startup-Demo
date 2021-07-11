package test.JUnit;

import com.example.demo.core.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    void testGet() {
        assertEquals("Hello JUnit 5", 1);
    }

}
