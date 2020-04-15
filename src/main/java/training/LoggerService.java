package training;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoggerService implements ApplicationListener<EmployeeWasCreatedEvent> {
    @Override
    public void onApplicationEvent(EmployeeWasCreatedEvent employeeWasCreatedEvent) {
        System.out.println("Event has arrived: " + employeeWasCreatedEvent.getName() + " " + LocalDateTime.now());
    }
}
