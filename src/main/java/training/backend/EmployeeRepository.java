package training.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import training.backend.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
