package com.girnar.humanResource.Jdbc;

import java.sql.Connection;

public class Director extends Manager {

	/*
	 * (non-Javadoc)
	 * @see com.girnar.humanResource.fileSystem.HR#addTeamMember(com.girnar.humanResource.fileSystem.DatabaseOfEmployees, int, int)
	 * Add team member in team of Director
	 */
	public void addTeamMember(Connection conn, int empId, int userInput) {
		
		if(serviceClassOfEmployee.isEmpIdExist(conn, userInput)) {
			System.out.println("Employee Id Not Exist");
			return;
		}
		if ( serviceClassOfEmployee.getDesignation(conn, userInput).matches("CEO|HR")) {
			System.out.println("Able To Add Only Manager,Fresher In Your Team");
			return;
		}
			serviceClassOfEmployee.addTeamMember(conn, empId, userInput);
	}

	public void promoteEmployee(Connection conn, int empId, int candidateId) {
		if(!serviceClassOfEmployee.isEmpIdExist(conn, candidateId)) {
			if ( serviceClassOfEmployee.getDesignation(conn, candidateId).equals("Manager")) {
				serviceClassOfEmployee.promoteEmployee(conn, candidateId, 2);
				System.out.println("You Promote with new Designation of Director");
				return;
			}
			if ( serviceClassOfEmployee.getDesignation(conn, candidateId).equals("Fresher")) {
				serviceClassOfEmployee.promoteEmployee(conn, candidateId, 4);
				System.out.println("You Promote with new Designation of Manager");
				return;
			}
			System.out.println("Not Authorized");
			return;
		}else {
			System.out.println("Employee Id Not Exist");
		}

	}
	/*
	 * (non-Javadoc)
	 * @see com.girnar.humanResource.fileSystem.HR#addEmployees(com.girnar.humanResource.fileSystem.DatabaseOfEmployees, java.lang.String, java.lang.String)
	 * Recruiting new member in company
	 */
	public void addEmployees(Connection conn, String name, int designation) {
	
		if (designation != 4 && designation != 5) {
			System.out.println("Authorized to add only Fresher and Manager"); 
			return;
		}
		serviceClassOfEmployee.addEmployees(conn, name, designation);
	}
}
