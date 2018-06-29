package com.girnar.humanResource.Jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FileInputAndOutput {

	public void writeInFile(DatabaseOfEmployees databaseOfEmployees) {
		try {
			FileWriter fileEmployeeDetails = new FileWriter("/home/gspl/EmployeeDetails.txt");
			Set<Integer> keys = databaseOfEmployees.employeeDetails.keySet();
			for (Integer iterate : keys) {
				String key = Integer.toString(iterate) + " " + databaseOfEmployees.employeeDetails.get(iterate)[0] + " "
						+ databaseOfEmployees.employeeDetails.get(iterate)[1] + "\n";
				fileEmployeeDetails.write(key);

			}
			FileWriter fileTeamMates = new FileWriter("/home/gspl/TeamMates.txt");
			keys = databaseOfEmployees.teamMembers.keySet();
			for (Integer iterate : keys) {
				String key = Integer.toString(iterate) + " " + databaseOfEmployees.teamMembers.get(iterate) + "\n";
				fileTeamMates.write(key);

			}
			FileWriter fileReportPerson = new FileWriter("/home/gspl/ReportPerson.txt");
			keys = databaseOfEmployees.reportPerson.keySet();
			for (Integer iterate : keys) {
				String key = Integer.toString(iterate) + " " + databaseOfEmployees.reportPerson.get(iterate) + "\n";
				fileReportPerson.write(key);

			}

			// Close
			fileEmployeeDetails.close();
			fileTeamMates.close();
			fileReportPerson.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readFromFile(DatabaseOfEmployees databaseOfEmployees) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("/home/gspl/EmployeeDetails.txt"));
			String line = reader.readLine();

			while (line != null) {
				String[] splitted = line.split(" ");
				String[] temp1 = { splitted[1], splitted[2] };
				
				databaseOfEmployees.employeeDetails.put(Integer.parseInt(splitted[0]), temp1);
				// read next line
				line = reader.readLine();
			}
			reader.close();
			reader = new BufferedReader(new FileReader("/home/gspl/TeamMates.txt"));
			line = reader.readLine();

			while (line != null) {
				line = line.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\, ", " ");
				
				String[] splitted = line.split(" ");
				Set<Integer> teamMateId = new HashSet<>();
				for (int count = 1; count < splitted.length; count++)
					teamMateId.add(Integer.parseInt(splitted[count]));
				databaseOfEmployees.teamMembers.put(Integer.parseInt(splitted[0]), teamMateId);
				// read next line
				line = reader.readLine();
			}
			reader.close();
			reader = new BufferedReader(new FileReader("/home/gspl/ReportPerson.txt"));
			line = reader.readLine();

			while (line != null) {
				line = line.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\, ", " ");
				String[] splitted = line.split(" ");
				Set<Integer> teamMateId = new HashSet<>();
				for (int count = 1; count < splitted.length; count++)
					teamMateId.add(Integer.parseInt(splitted[count]));
				databaseOfEmployees.reportPerson.put(Integer.parseInt(splitted[0]), teamMateId);
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
