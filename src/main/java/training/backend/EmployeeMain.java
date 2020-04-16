package training.backend;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EmployeeMain {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(EmployeeConfig.class);

        try (context) {
            var employeeService = context.getBean(EmployeeService.class);

            employeeService.saveEmployee("John Doe");
        }
    }
}
