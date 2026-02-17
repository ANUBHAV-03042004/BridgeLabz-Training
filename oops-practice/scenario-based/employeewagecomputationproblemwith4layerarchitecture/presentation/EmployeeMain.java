package employeewagecomputationproblemwith4layerarchitecture.presentation;
import employeewagecomputationproblemwith4layerarchitecture.model.Company;
import employeewagecomputationproblemwith4layerarchitecture.dao.CompanyDAO;
import employeewagecomputationproblemwith4layerarchitecture.dao.EmployeeDAO;
import employeewagecomputationproblemwith4layerarchitecture.model.Employee;
import employeewagecomputationproblemwith4layerarchitecture.service.EmpWageBuilder;
import employeewagecomputationproblemwith4layerarchitecture.service.EmployeeService;

public class EmployeeMain {

    public static void main(String[] args) {

        EmployeeService service = new EmployeeService();
        Employee employee = new Employee("Anubhav", 20);
        service.computeMonthlyWage(employee);

        EmployeeDAO dao = new EmployeeDAO();
        dao.save(employee);

        System.out.println("Employee Name: " + employee.getName());
        System.out.println("Total Wage: " + employee.getTotalWage());
        Company tcs = new Company("TCS", 20, 20, 100);
        Company infosys = new Company("Infosys", 25, 22, 120);

     CompanyDAO cdao = new CompanyDAO();
     EmpWageBuilder tcsBuilder = new EmpWageBuilder(tcs);
     tcsBuilder.computeEmployeeWage();

     EmpWageBuilder infosysBuilder = new EmpWageBuilder(infosys);
     infosysBuilder.computeEmployeeWage();

    cdao.save(tcsBuilder.getCompany());
     cdao.save(infosysBuilder.getCompany());
    }
}