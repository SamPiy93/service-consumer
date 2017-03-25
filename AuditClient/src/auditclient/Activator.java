package auditclient;

import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import payrollmanagerservice.SalaryCalculation;

public class Activator implements BundleActivator {
	private Map<Integer, String[]> financialDetails;
	private SalaryCalculation salaryCalculation;
	private Map<Integer, String[]> auditDetailsMap = new HashMap<>();
	int counter = 1;
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
		AuditOperations auditOperations = new AuditOperationsImplementation();

		financialDetails = salaryCalculation.getEmployeeDetails();
		System.out.println("service started" + calculateTotalSalaryPayments());

		// System.out.println("total : "+calculateTotalSalaryPayments());
	}

	public double calculateTotalSalaryPayments() {
		financialDetails = salaryCalculation.getEmployeeFinancialDetails();
		double totalSalary = 0;
		for (Map.Entry<Integer, String[]> entry : financialDetails.entrySet()) {
			totalSalary += Double.parseDouble(entry.getValue()[5]);
		}

		return totalSalary;
	}

	public double calculateFunds() {
		financialDetails = salaryCalculation.getEmployeeFinancialDetails();
		double totalFunds = 0;
		for (Map.Entry<Integer, String[]> entry : financialDetails.entrySet()) {
			totalFunds += (Double.parseDouble(entry.getValue()[1]) + Double.parseDouble(entry.getValue()[2])
					+ Double.parseDouble(entry.getValue()[3]));
		}

		return totalFunds;
	}

	public double getSalaryIndividuals(int id) {
		financialDetails = salaryCalculation.getEmployeeFinancialDetails();
		double salaryForIndividual = 0;
		if (!financialDetails.get(id).equals("")) {
			salaryForIndividual = Double.parseDouble(financialDetails.get(id)[5]);
		}

		return salaryForIndividual;
	}

	public Map<Integer, String[]> generateAuditReport() {
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

			auditDetailsMap.put(counter++, new String[] { String.valueOf(funds), String.valueOf(epf12),
					String.valueOf(epf8), String.valueOf(etf3), String.valueOf(loans), String.valueOf(salaries) });
		}
		return auditDetailsMap;
	}

	public SalaryCalculation getServiceInstance() {
		return salaryCalculation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		bundleContext.ungetService(serviceReference);
		System.out.println("service stopped");
	}
}
