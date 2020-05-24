package de.ansaru.happymoments.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class UserServiceTest {
 /*
    @Mock
    private UserDatabaseService dbServiceMock = Mockito.mock(UserDatabaseService.class);

    private UserService userService = new UserService(dbServiceMock);

    @Before
    public void setUp() {
        initMocks(userService);
    }

    @Test
    public void test_register_new_user_successful() {
        long id = 42;
        String name = "Name";
        String email = "Email";
        String password = "password";

        User expectedUser = new User.Builder()
                .withId(id)
                .withName(name)
                .withEmail(email)
                .build();

        when(dbServiceMock.getUserByEmail(email)).thenReturn(Optional.empty());
        when(dbServiceMock.create(any(User.class))).thenReturn(Optional.of(expectedUser));

        RegisterState result = userService.register(name, email, password);

        assertThat(result).isEqualTo(RegisterState.SUCCESS);
    }

    @Test
    public void test_register_new_user_with_used_email() {
        String name = "Name";
        String email = "Email";
        String password = "password";

        User user = new User.Builder()
                .withName(name)
                .withEmail(email)
                .build();

        when(dbServiceMock.getUserByEmail(email)).thenReturn(Optional.of(user));

        RegisterState result = userService.register(name, email, password);

        assertThat(result).isEqualTo(RegisterState.EMAIL_USED);
    }

    @Test
    public void test_register_new_user_with_unknown_error() {
        String name = "Name";
        String email = "Email";
        String password = "password";

        when(dbServiceMock.getUserByEmail(email)).thenReturn(Optional.empty());
        when(dbServiceMock.create(any(User.class))).thenReturn(Optional.empty());

        RegisterState result = userService.register(name, email, password);

        assertThat(result).isEqualTo(RegisterState.UNKNOWN_ERROR);
    }

    @Test
    public void test_login_success() {
        String email = "EMail";
        String password = "p@ssw0rd";

        when(dbServiceMock.getPasswordByEmail(email)).thenReturn(Optional.of(password));

        assertTrue(userService.login(email, password).equals(LoginState.SUCCESS));
    }

    @Test
    public void test_login_wrong_password() {
        String email = "EMail";
        String password = "p@ssw0rd";
        String wrongPassword = "wrong";

        when(dbServiceMock.getPasswordByEmail(email)).thenReturn(Optional.of(password));

        assertTrue(userService.login(email, wrongPassword).equals(LoginState.WRONG_PASSWORD));
    }

    @Test
    public void test_login_unknown_email() {
        String email = "EMail";
        String password = "p@ssw0rd";

        when(dbServiceMock.getPasswordByEmail(email)).thenReturn(Optional.empty());

        assertTrue(userService.login(email, password).equals(LoginState.UNKNOWN_EMAIL));
    }

    @Test
    public void test_update_user_successful() {
        long id = 42;
        String name = "Name";
        String email = "Email";

        User expectedUser = new User.Builder()
                .withId(id)
                .withName(name)
                .withEmail(email)
                .build();

        when(dbServiceMock.update(any(User.class))).thenReturn(Optional.of(expectedUser));

        boolean result = userService.updateInformation(id, name, email);

        assertTrue(result);
    }

    @Test
    public void test_update_user_not_successful() {
        long id = 42;
        String name = "Name";
        String email = "Email";

        when(dbServiceMock.update(any(User.class))).thenReturn(Optional.empty());

        boolean result = userService.updateInformation(id, name, email);

        assertFalse(result);
    } */
}

