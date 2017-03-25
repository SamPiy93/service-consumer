package finaceclient;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import payrollmanagerservice.SalaryCalculation;

public class Activator implements BundleActivator {
	ServiceReference serviceReference;
	private SalaryCalculation salaryCalculation;
	Scanner scanner = new Scanner(System.in);

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Audit client started successfully!");
		serviceReference = bundleContext.getServiceReference(SalaryCalculation.class.getName());
		salaryCalculation = (SalaryCalculation) bundleContext.getService(serviceReference);
		System.out.println("----------Financial data feed wizard-----------");
		String exitCode = "";
		System.out.print("Do you want to run service?(y/n) : ");
		String response = scanner.next();
		if(response.equals("y")){
			do{
				System.out.print("Please select the process\n1-Employee Personal Details Feed\n2-Employee Financial Details Feed\n\nEnter Here : ");
				int selection = scanner.nextInt();
				selectionMenu(selection);
				System.out.print("Do you want to feed more data?(y/n) : ");
				exitCode = scanner.next();
			}while(exitCode.equals("y"));
			System.out.println("\nFinancial Operation Terminated!");	
		}	
	}
	public void selectionMenu(int selection){
		int id;
		String name;
		String nic;
		String basicSalary;
		float loanAmount;
		float interestRate;
		int year;
		int month;
		
		switch (selection) {
		case 1:
			System.out.println();
			System.out.println("Enter Employee Details");
			System.out.print("ID : ");
			id = scanner.nextInt();
			System.out.print("\nName : ");
			name = scanner.next();
			System.out.print("NIC : ");
			nic = scanner.next();
			System.out.print("Basic Salary : ");
			basicSalary = scanner.next();
			
			try {
				enterEmployeeDetails(id, name, nic, basicSalary);
			} catch (Exception e) {
				System.out.println("Error occured!");
			}
			break;
		case 2:
			System.out.println();
			System.out.println("Enter Employee Details");
			System.out.print("ID : ");
			id = scanner.nextInt();
			System.out.print("\nLoan Amount : ");
			loanAmount = scanner.nextFloat();
			System.out.print("Interest Rate : ");
			interestRate = scanner.nextFloat();
			System.out.print("Year : ");
			year = scanner.nextInt();
			System.out.print("Month : ");
			month = scanner.nextInt();
			
			try {
				enterEmployeeFinanceDetails(id, loanAmount, interestRate, year, month);
			} catch (Exception e) {
				System.out.println("Error occured!");
			}
			break;
		default:
			System.out.println("Invalid Entry!");
			break;
		}
	}
	
	public void enterEmployeeDetails(int id, String name, String nic, String basicSalary){
		try{
			salaryCalculation.employeeDetails(id, name, nic, basicSalary);
			System.out.println("Employee "+id+" personal details successfully recorded!");
		} catch (Exception e) {
			System.out.println("Error occured while entering employee details : "+e);
		}
	}
	public void enterEmployeeFinanceDetails(int id, float loanAmount, float interestRate, int year, int month){
		try{
			salaryCalculation.employeeFinancialDetails(id, loanAmount, interestRate, year, month);
			System.out.println("Employee "+id+" financial details successfully recorded!");
		}catch (Exception e) {
			System.out.println("Error occured while entering financial details of employee "+id+" : "+e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		bundleContext.ungetService(serviceReference);
		System.out.println("Financial Client Service Terminated");
	}

}
