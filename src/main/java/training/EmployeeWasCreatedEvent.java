package training;

import org.springframework.context.ApplicationEvent;

public class EmployeeWasCreatedEvent extends ApplicationEvent {

    private String name;

    public EmployeeWasCreatedEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
