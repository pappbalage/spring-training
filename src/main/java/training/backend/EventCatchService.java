package training.backend;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class EventCatchService implements ApplicationListener<EmployeeWasCreatedEvent> {
    private AtomicInteger counter = new AtomicInteger();

    @Override
    public void onApplicationEvent(EmployeeWasCreatedEvent employeeWasCreatedEvent) {

    }
}
