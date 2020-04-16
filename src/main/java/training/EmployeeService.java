package training;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeDao employeeDao;

    private final ApplicationEventPublisher pub;

    private final Environment env;

    private final EmployeeRepository employeeRepository;

    private final boolean uppercaseEnabled;

    public EmployeeService(EmployeeDao employeeDao, ApplicationEventPublisher pub, Environment env, EmployeeRepository employeeRepository, @Value("${uppercase.enabled}") boolean uppercaseEnabled) {
        this.employeeDao = employeeDao;
        this.pub = pub;
        this.env = env;
        this.employeeRepository = employeeRepository;
        this.uppercaseEnabled = uppercaseEnabled;
    }

    @Transactional
    public Employee saveEmployee(String name) {
        log.info("Save employee");
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name can not be empty");
        }


        var employee = employeeRepository.save(new Employee(convertName(name)));
        pub.publishEvent(new EmployeeWasCreatedEvent(this, name));
        return employee;
    }

    @PostConstruct
    public void initMsg() {
        log.info("Service created");
    }

    public List<Employee> listEmployees() {
        return employeeRepository.findAll();
    }


    public Employee findEmployeeById(long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
    }

    @Transactional
    public Employee updateEmployee(long id, String name) {
        var employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        employee.setName(convertName(name));
        return employee;
    }

    private String convertName(String name) {
        if (uppercaseEnabled) {
            return name.toUpperCase();
        }
        else {
            return name;
        }
    }

    @Transactional
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    @Transactional
    public void emptyEmployees() {
        employeeRepository.deleteAll();
    }
}
