package training;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;

    private final ApplicationEventPublisher pub;

    public EmployeeService(EmployeeDao employeeDao, ApplicationEventPublisher pub) {
        this.employeeDao = employeeDao;
        this.pub = pub;
    }

    public void saveEmployee(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name can not be empty");
        }

        var upperCase = name.toUpperCase();

        employeeDao.saveEmployee(upperCase);

        pub.publishEvent(new EmployeeWasCreatedEvent(this, name));
    }

    @PostConstruct
    public void initMsg() {
        System.out.println("Service created");
    }
}
