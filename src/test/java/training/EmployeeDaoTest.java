package training;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeDaoTest {

    @Test
    public void testSaveEmployee() {
        var dao = new EmployeeDao();
        dao.saveEmployee("JB");

        assertEquals(List.of("JB"), dao.getEmployees());
    }
}
