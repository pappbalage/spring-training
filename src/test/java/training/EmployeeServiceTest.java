package training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @Mock
    private EmployeeDao dao;

    @Mock
    private Environment env;

    private EmployeeService service;

    @BeforeEach
    public void init() {
        service = new EmployeeService(dao, applicationEventPublisher, env, true);
    }

    @Test
    public void testSaveEmployeeWithEmpty() {
        assertThrows(IllegalArgumentException.class, () -> service.saveEmployee(" "));
    }

    @Test
    public void testSaveEmployee() {

        service.saveEmployee("Jb");

        verify(dao).saveEmployee(eq("JB"));
        verify(dao, never()).getEmployees();
        assertDoesNotThrow(() -> service.saveEmployee("Jb"));
    }
}
