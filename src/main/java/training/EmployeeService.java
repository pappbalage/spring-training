package training;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void saveEmployee(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name can not be empty");
        }

        var upperCase = name.toUpperCase();

        employeeDao.saveEmployee(upperCase);
    }

    @PostConstruct
    public void initMsg() {
        System.out.println("Service created");
    }
}
