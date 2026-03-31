package employeewagecomputationproblemwith4layerarchitecture.dao;

import java.util.ArrayList;
import java.util.List;

import employeewagecomputationproblemwith4layerarchitecture.model.Employee;
public class EmployeeDAO {
List<Employee> employeeDetails = new ArrayList<>();

    public void save(Employee employee) {
     employeeDetails.add(employee);
        System.out.println("Employee data saved successfully.");
    }
}