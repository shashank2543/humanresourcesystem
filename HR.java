package com.girnar.humanResource.Jdbc;

import java.sql.Connection;

public class HR extends Director {

	
	public void addTeamMember(Connection conn, int empId, int userInput) {

		if (serviceClassOfEmployee.printDesignation(conn, userInput).matches("CEO")) {
			System.out.println("Able to add only Manager, Director and Fresher In Your Team");
			return;
		}
		serviceClassOfEmployee.addTeamMember(conn, empId, userInput);
	}

}
