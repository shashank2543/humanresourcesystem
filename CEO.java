package com.girnar.humanResource.Jdbc;

import java.sql.Connection;

public class CEO extends HR {

	public void addTeamMember(Connection conn, int empId, int userInput) {

		serviceClassOfEmployee.addTeamMember(conn, empId, userInput);
	}
	
/*	public void promoteEmployee(Connection conn, int empId, int userInput) {
		
		if (serviceClassOfEmployee.getDesignation(conn, userInput).equals("Manager")) {
			serviceClassOfEmployee.promoteEmployee(conn, userInput, 2);
			System.out.println("You Promote with new Designation of Director");
			return;
		}
		if (databaseOfEmployees.getEmployeeDetails().get(userInput)[1].equals("Fresher")) {
			serviceClassOfEmployee.promoteEmployee(databaseOfEmployees, userInput, "HR");
			System.out.println("You Promote with new Designation of HR");
			return ;
		}
		System.out.println("Not Authorized");
		return;

	}*/
/*
 * (non-Javadoc)
 * @see com.girnar.humanResource.fileSystem.HR#addEmployees(com.girnar.humanResource.fileSystem.DatabaseOfEmployees, java.lang.String, java.lang.String)
 * Recruiting new member in company
 */
	public void addEmployees(Connection conn, String name, int designation) {

		serviceClassOfEmployee.addEmployees(conn, name, designation);
	}

}
