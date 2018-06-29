package com.girnar.humanResource.Jdbc;

import java.sql.Connection;

public interface Employee {
	public void showTeamMember(Connection conn, int empId);

	public void addTeamMember(Connection conn, int empId, int userInput);

	public void showReportPerson(Connection conn, int empId);

	public void setReportingHead(Connection conn, int ownId, int addReportpersonId);

	public void promoteEmployee(Connection conn, int empId, int candidateId);

	public boolean isEmpIdExist(Connection conn, int empId);

	public void addEmployees(Connection conn, String name, int designation);

	public void showProfile(Connection conn, int empId);

}
