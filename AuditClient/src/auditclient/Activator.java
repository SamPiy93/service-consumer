package auditclient;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import payrollmanagerservice.SalaryCalculation;

public class Activator implements BundleActivator {
	private Map<Integer, String[]> financialDetails;
	private SalaryCalculation salaryCalculation;
	private Map<Integer, String[]> auditDetailsMap = new HashMap<>();
	int counter = 1;
	private Scanner scanner = new Scanner(System.in);
	ServiceReference serviceReference;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Audit client started successfully!");
		serviceReference = bundleContext.getServiceReference(SalaryCalculation.class.getName());
		salaryCalculation = (SalaryCalculation) bundleContext.getService(serviceReference);
		
//		Program starts here
		String exitCode = "";
		System.out.print("Do you want to run service?(y/n) : ");
		String response = scanner.next();
		if(response.equals("y")){
			do{
				System.out.print("Select Report type\n\n1-Audit Report\n2-Salary Report\n3-Funds Report\n4-Salary Report(Individuals)\n\nEnter Here : ");
				int selection = scanner.nextInt();
				selectionMenu(selection);
				System.out.print("Do you want to continue?(y/n) : ");
				exitCode = scanner.next();
			}while(exitCode.equals("y"));
			System.out.println("\nOperation Terminated!");
		}
	}
	
	public void selectionMenu(int selection){
		switch (selection) {
		case 1:
			System.out.println();
			generateAuditReport();
			break;
		case 2:
			System.out.println();
			calculateTotalSalaryPayments();
			break;
		case 3:
			System.out.println();
			calculateFunds();
			break;
		case 4:
			System.out.println();
			System.out.print("Please enter employee ID : ");
			int indivId = scanner.nextInt();
			getSalaryIndividuals(indivId);
			break;
		default:
			System.out.println("Invalid Entry!");
			break;
		}
	}
	
	public void calculateTotalSalaryPayments() {
		financialDetails = salaryCalculation.getEmployeeFinancialDetails();
		double totalSalary = 0;
		for (Map.Entry<Integer, String[]> entry : financialDetails.entrySet()) {
			totalSalary += Double.parseDouble(entry.getValue()[5]);
		}
		System.out.println("----------------------------------------------------");
		System.out.println("-------------Total Salary Report--------------------");
		System.out.println("----------------------------------------------------");
		System.out.println("----------Total Salary  : "+totalSalary+"----------------------");
		System.out.println("----------------------------------------------------");
		System.out.println("----------------------------------------------------");
	}

	public void calculateFunds() {
		financialDetails = salaryCalculation.getEmployeeFinancialDetails();
		double totalFunds = 0;
		double epf12 = 0;
		double epf8 = 0;
		double etf3 = 0;
		
		for (Map.Entry<Integer, String[]> entry : financialDetails.entrySet()) {
			epf12 += Double.parseDouble(entry.getValue()[1]);
			epf8 += Double.parseDouble(entry.getValue()[2]);
			etf3 += Double.parseDouble(entry.getValue()[3]);
//			totalFunds += (Double.parseDouble(entry.getValue()[1]) + Double.parseDouble(entry.getValue()[2])
//					+ Double.parseDouble(entry.getValue()[3]));
		}
		totalFunds = epf12+epf8+etf3;
		System.out.println("--------------------------------------------------------------------");
		System.out.println("---------------------Funding Report---------------------------------");
		System.out.println("--------------------------------------------------------------------");
		System.out.println("--------------Total Epf12    : "+epf12+"----------------------------");
		System.out.println("--------------Total Epf8     : "+epf8+"-----------------------------");
		System.out.println("--------------Total Etf3     : "+etf3+"-----------------------------");
		System.out.println("--------------Total Funds    : "+totalFunds+"-----------------------");
		System.out.println("--------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------");
//		return totalFunds;
	}

	public void getSalaryIndividuals(int id) {
		financialDetails = salaryCalculation.getEmployeeFinancialDetails();
		double salaryForIndividual = 0;
		if (!financialDetails.get(id).equals("")) {
			salaryForIndividual = Double.parseDouble(financialDetails.get(id)[5]);
		}
		System.out.println("----------------------------------------------------");
		System.out.println("--------Individual Salary Report--------------------");
		System.out.println("----------------------------------------------------");
		System.out.println("----------Employee ID : "+id+"----------------------");
		System.out.println("----------Net Salary  : "+salaryForIndividual+"----------------------");
		System.out.println("----------------------------------------------------");
		System.out.println("----------------------------------------------------");
	}

//	public Map<Integer, String[]> generateAuditReport() {
	public void generateAuditReport() {
		financialDetails = salaryCalculation.getEmployeeFinancialDetails();
		double funds = 0;
		double epf12 = 0;
		double epf8 = 0;
		double etf3 = 0;
		double loans = 0;
		double salaries = 0;

		for (Map.Entry<Integer, String[]> entry : financialDetails.entrySet()) {
			funds += (Double.parseDouble(entry.getValue()[1]) + Double.parseDouble(entry.getValue()[2])
					+ Double.parseDouble(entry.getValue()[3]));
			epf12 += (Double.parseDouble(entry.getValue()[1]));
			epf8 += (Double.parseDouble(entry.getValue()[2]));
			etf3 += (Double.parseDouble(entry.getValue()[3]));
			loans += (Double.parseDouble(entry.getValue()[4]));
			salaries += (Double.parseDouble(entry.getValue()[5]));
		}
		auditDetailsMap.put(counter++, new String[] { String.valueOf(funds), String.valueOf(epf12),
				String.valueOf(epf8), String.valueOf(etf3), String.valueOf(loans), String.valueOf(salaries) });
		
		System.out.println("--------------------------------------------------------------------");
		System.out.println("--------------------------Audit Report------------------------------");
		System.out.println("--------------------------------------------------------------------");
		System.out.println("------Total Funds    : "+auditDetailsMap.get(1)[0]+"----------------");
		System.out.println("------Total Epf12    : "+auditDetailsMap.get(1)[1]+"----------------");
		System.out.println("------Total Epf8     : "+auditDetailsMap.get(1)[2]+"----------------");
		System.out.println("------Total Etf3     : "+auditDetailsMap.get(1)[3]+"----------------");
		System.out.println("------Total Loans    : "+auditDetailsMap.get(1)[4]+"----------------");
		System.out.println("------Total Salaries : "+auditDetailsMap.get(1)[5]+"----------------");
		System.out.println("--------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------");
		
//		return auditDetailsMap;
	}

//	public SalaryCalculation getServiceInstance() {
//		return salaryCalculation;
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		bundleContext.ungetService(serviceReference);
		System.out.println("Audit Client Service Terminated!");
	}
}
