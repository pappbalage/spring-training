package training;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
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

    @Bean
    public DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/employees?useUnicode=true");
        dataSource.setUser("root");
        dataSource.setPassword("D@rkPrint12Y");
        return dataSource;
    }

    @Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource()).load();
        flyway.migrate();
        return flyway;
    }
}
