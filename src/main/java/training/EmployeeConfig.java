package training;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfig {

    @Bean
    public EmployeeDao employeeDao() {
        return new EmployeeDao();
    }

    @Bean
    public EmployeeService employeeService() {
        return new EmployeeService(employeeDao());
    }
}
