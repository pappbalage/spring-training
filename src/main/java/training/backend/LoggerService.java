package training.backend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class LoggerService implements ApplicationListener<EmployeeWasCreatedEvent> {
    @Override
    public void onApplicationEvent(EmployeeWasCreatedEvent employeeWasCreatedEvent) {
        log.info("Event has arrived: " + employeeWasCreatedEvent.getName() + " " + LocalDateTime.now());
    }
}
