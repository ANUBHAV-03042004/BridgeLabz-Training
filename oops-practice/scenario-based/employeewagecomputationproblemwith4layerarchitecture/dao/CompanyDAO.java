package employeewagecomputationproblemwith4layerarchitecture.dao;

import java.util.ArrayList;
import java.util.List;

import employeewagecomputationproblemwith4layerarchitecture.model.Company;
public class CompanyDAO {

	List<Company> CompanyDetails = new ArrayList<>();

	    public void save(Company company) {
	     CompanyDetails.add(company);
	        System.out.println("Company data saved successfully.");
	    }
	}