package mylab.user.di.annot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-user-di.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testDependencyInjection() {

        // UserService 주입 확인
        assertNotNull(userService);

        // UserRepository 주입 확인
        assertNotNull(userService.getUserRepository());

        // DB 타입 확인
        assertEquals("MySQL", userService.getUserRepository().getDbType());

        // SecurityService 주입 확인
        assertNotNull(userService.getSecurityService());
    }

    @Test
    public void testRegisterUser() {

        // 비밀번호가 전달된 경우 True
        assertTrue(userService.registerUser("user01", "홍길동", "1234"));

        // 비밀번호가 전달되지 않은 경우 False
        assertFalse(userService.registerUser("user02", "김철수", ""));
    }
}