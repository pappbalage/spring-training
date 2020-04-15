package training;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class EmployeeConfig {

//    @Bean
//    public EmployeeDao employeeDao() {
//        System.out.println("Create employee DAO");
//        return new EmployeeDao();
//    }
//
//    @Bean
//    public EmployeeService employeeService() {
//        return new EmployeeService(employeeDao());
//    }
}
