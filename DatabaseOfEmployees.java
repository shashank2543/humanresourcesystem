package com.girnar.humanResource.Jdbc;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

public class DatabaseOfEmployees {
	protected HashMap<Integer, String[]> employeeDetails = new LinkedHashMap<>(); // String[name,designation]
	// Table for add Report Head
	protected HashMap<Integer, Set<Integer>> reportPerson = new HashMap<>();
	// Table for add Team Members
	protected HashMap<Integer, Set<Integer>> teamMembers = new HashMap<>();

	public HashMap<Integer, String[]> getEmployeeDetails() {
		return employeeDetails;
	}

	public void setEmployeeDetails(HashMap<Integer, String[]> employeeDetails) {
		this.employeeDetails = employeeDetails;
	}

	public HashMap<Integer, Set<Integer>> getReportPerson() {
		return reportPerson;
	}

	public void setReportPerson(HashMap<Integer, Set<Integer>> reportPerson) {
		this.reportPerson = reportPerson;
	}

	public HashMap<Integer, Set<Integer>> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(HashMap<Integer, Set<Integer>> teamMembers) {
		this.teamMembers = teamMembers;
	}

}
