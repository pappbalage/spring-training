package training;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeDao employeeDao;

    private final ApplicationEventPublisher pub;

    private final Environment env;

    private final boolean uppercaseEnabled;

    public EmployeeService(EmployeeDao employeeDao, ApplicationEventPublisher pub, Environment env, @Value("${uppercase.enabled}") boolean uppercaseEnabled) {
        this.employeeDao = employeeDao;
        this.pub = pub;
        this.env = env;
        this.uppercaseEnabled = uppercaseEnabled;
    }

    public Employee saveEmployee(String name) {
        log.info("Save employee");
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name can not be empty");
        }


        var employee = employeeDao.saveEmployee(convertName(name));
        pub.publishEvent(new EmployeeWasCreatedEvent(this, name));
        return employee;
    }

    @PostConstruct
    public void initMsg() {
        log.info("Service created");
    }

    public List<Employee> listEmployees() {
        return employeeDao.getEmployees();
    }

    public Employee findEmployeeById(long id) {
        return employeeDao.findEmployeeById(id);
    }

    public Employee updateEmployee(long id, String name) {
        return employeeDao.updateEmployee(id, convertName(name));
    }

    private String convertName(String name) {
        if (uppercaseEnabled) {
            return name.toUpperCase();
        }
        else {
            return name;
        }
    }

    public void deleteEmployee(long id) {
        employeeDao.deleteEmployee(id);
    }

    public void emptyEmployees() {
        employeeDao.emptyEmployees();
    }
}
