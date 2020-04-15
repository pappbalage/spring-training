package training;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

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

    public void saveEmployee(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name can not be empty");
        }

        String changedName;
        if(Boolean.parseBoolean(env.getProperty("uppercase.enabled"))) {
            changedName = name.toUpperCase();
        } else {
            changedName = name;
        }

        if(uppercaseEnabled) {
            log.info("Hello");
        }

        //var upperCase = name.toUpperCase();

        employeeDao.saveEmployee(changedName);
        log.info("employee saved: " + changedName);

        pub.publishEvent(new EmployeeWasCreatedEvent(this, name));
    }

    @PostConstruct
    public void initMsg() {
        log.info("Service created");
    }
}
