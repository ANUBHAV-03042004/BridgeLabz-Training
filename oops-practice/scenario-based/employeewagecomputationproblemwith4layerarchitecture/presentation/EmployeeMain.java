package employeewagecomputationproblemwith4layerarchitecture.presentation;
import employeewagecomputationproblemwith4layerarchitecture.model.Company;
import employeewagecomputationproblemwith4layerarchitecture.dao.CompanyDAO;
import employeewagecomputationproblemwith4layerarchitecture.dao.EmployeeDAO;
import employeewagecomputationproblemwith4layerarchitecture.model.Employee;
import employeewagecomputationproblemwith4layerarchitecture.service.EmpWageBuilder;
import employeewagecomputationproblemwith4layerarchitecture.service.EmployeeService;
import employeewagecomputationproblemwith4layerarchitecture.service.IEmpWageBuilder;
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
     
     

     EmpWageBuilder builder = new EmpWageBuilder(5);
//     IEmpWageBuilder builder = new EmpWageBuilder(5);
     builder.addCompany("TCS", 20, 20, 100);
     builder.addCompany("Infosys", 25, 22, 120);
     builder.addCompany("Wipro", 18, 25, 110);

     builder.computeCompanyWageForEmployeeWage();
  
     System.out.println("TCS Wage: " + builder.getTotalWage("TCS"));
     System.out.println("Infosys Wage: " + builder.getTotalWage("Infosys"));
     System.out.println("Wipro Wage: " + builder.getTotalWage("Wipro"));
    }
}