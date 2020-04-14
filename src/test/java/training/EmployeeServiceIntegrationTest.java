package training;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = EmployeeConfig.class)
public class EmployeeServiceIntegrationTest {

    @Autowired
    private EmployeeDao dao;

    @Autowired
    private EmployeeService service;

    @Test
    public void testSave() {
        service.saveEmployee("jb");
        List<String> names = dao.getEmployees();
        assertEquals(List.of("JB"), names);

    }

    @BeforeEach
    public void clearEmployees() {
        dao.emptyEmployees();
    }


}
