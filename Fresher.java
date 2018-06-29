package com.girnar.humanResource.Jdbc;

import java.sql.Connection;

public class Fresher implements Employee {

	//DatabaseOfEmployees databaseOfEmployees = null;
	ServiceClassOfEmployee serviceClassOfEmployee = new ServiceClassOfEmployee();
	 public Fresher() {
		
	}
	
	public void showReportPerson(Connection conn,int empId) {
		serviceClassOfEmployee.getReportingHeads(conn,empId);
	}
	
	public void showProfile(Connection conn,int empId)
	{
		serviceClassOfEmployee.showProfile(conn,empId);
	}
	
	public void showTeamMember(Connection conn,int empId) {
		// TODO Auto-generated method stub
		
	}
	public void addTeamMember(Connection conn,int empId, int userInput) {
		
	}
	public void setReportingHead(Connection conn,int ownId, int addReportpersonId) {
	}
	public void promoteEmployee(Connection conn,int empId, int userInput) {
		
	}
	public boolean isEmpIdExist(Connection conn ,int empId) {
		return false;
	}
	public void addEmployees(Connection conn ,String name , int designation) {
	}
	
	
}
