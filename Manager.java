package com.girnar.humanResource.Jdbc;

import java.sql.Connection;

public class Manager extends Fresher {

	public void showTeamMember(Connection conn, int empId) {
		serviceClassOfEmployee.teamUnderYou(conn, empId);
	}

	public void addTeamMember(Connection conn, int empId, int userInput) {

		if (serviceClassOfEmployee.printDesignation(conn, userInput).matches("CEO|Director|HR")) {
			System.out.println("Able To Add Only Manager ,Fresher In Your Team");
			return;
		}
		serviceClassOfEmployee.addTeamMember(conn, empId, userInput);
	}

	public void promoteEmployee(Connection conn, int empId, int candidateId) {
		if (empId == candidateId) {
			System.out.println("You Enter Your Employee Id");
			return;
		}
		if(serviceClassOfEmployee.isEmpIdExist(conn, candidateId)) {
			System.out.println("Employee Id Not Exist");
			return;
		}
		if (serviceClassOfEmployee.getDesignation(conn, candidateId).equals("Fresher")) {
			serviceClassOfEmployee.promoteEmployee(conn, candidateId, 4);
			System.out.println("You Promote with new Designation of Manager");
			return;
		}
		System.out.println("Not Authorized");
		return;

	}

	public void addEmployees(Connection conn, String name, int designation) {
		if (designation != 5) {
			System.out.println("Authorized to add only Fresher");
			return;
		}
		serviceClassOfEmployee.addEmployees(conn, name, designation);

	}

}
