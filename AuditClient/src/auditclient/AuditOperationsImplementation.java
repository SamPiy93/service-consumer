package auditclient;

import java.util.Map;

public class AuditOperationsImplementation implements AuditOperations{
	// private Map<Integer, String[]> auditDetailsMap = new HashMap<>();
//	Activator activator = new Activator();
//	 private Map<Integer, String[]> financialDetails; 
//
//	 @Override
//	 public double calculateTotalSalaryPayments() {
//		 financialDetails = activator.getServiceInstance().getEmployeeFinancialDetails();
//	  double totalSalary = 0;
//	  for (Map.Entry<Integer, String[]> entry : financialDetails.entrySet())
//	  {
//	      totalSalary += Double.parseDouble(entry.getValue()[5]);
//	  }
//	  
//	  return totalSalary;
//	 }
//
//	 @Override
//	 public double calculateFunds() {
//		 financialDetails = activator.getServiceInstance().getEmployeeFinancialDetails();
//	  double totalFunds = 0;
//	  for (Map.Entry<Integer, String[]> entry : financialDetails.entrySet())
//	  {
//	      totalFunds += (Double.parseDouble(entry.getValue()[1])+Double.parseDouble(entry.getValue()[2])+Double.parseDouble(entry.getValue()[3]));
//	  }
//	  
//	  return totalFunds;
//	 }
//
//	 @Override
//	 public double getSalaryIndividuals(int id) {
//		 financialDetails = activator.getServiceInstance().getEmployeeFinancialDetails();
//	  double salaryForIndividual = 0;
//	  if (!financialDetails.get(id).equals("")) {
//	   salaryForIndividual = Double.parseDouble(financialDetails.get(id)[5]);
//	  }
//	  
//	  return salaryForIndividual;
//	 }
//	//
//	// @Override
//	// public Map<Integer, String[]> generateAuditReport() {
//	//  double funds = 0;
//	//  double epf12 = 0;
//	//  double epf8 = 0;
//	//  double etf3 = 0;
//	//  double loans = 0;
//	//  double salaries = 0;
//	//  
//	////  for (Map.Entry<Integer, String[]> entry : financialDetails.entrySet())
//	////  {
//////	      funds += (Double.parseDouble(entry.getValue()[1])+Double.parseDouble(entry.getValue()[2])+Double.parseDouble(entry.getValue()[3]));
//////	      epf12 += (Double.parseDouble(entry.getValue()[1]));
//////	      epf8 += (Double.parseDouble(entry.getValue()[2]));
//////	      etf3 += (Double.parseDouble(entry.getValue()[3]));
//////	      loans += (Double.parseDouble(entry.getValue()[4]));
//////	      salaries += (Double.parseDouble(entry.getValue()[5]));
//////	      
//////	      auditDetailsMap.put(counter++, new String[]{
//////	        String.valueOf(funds),
//////	        String.valueOf(epf12),
//////	        String.valueOf(epf8),
//////	        String.valueOf(etf3),
//////	        String.valueOf(loans),
//////	        String.valueOf(salaries)
//////	      });
//	////  }
//	//  return auditDetailsMap;
//	// }
}
