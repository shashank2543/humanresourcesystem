package com.girnar.humanResource.Jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
public class Test {
	static boolean innerLoop = true;

	public static void main(String[] args) throws IOException {
		DataBaseOfEmployees dataBaseOfEmployees = new DataBaseOfEmployees();
		Connection conn = dataBaseOfEmployees.getConnection();
		Integer empId = 0, userChoise = 0;
		Scanner scanner = new Scanner(System.in); // Intialize Scanner
		//Employee employee = null;
		boolean outerLoop = true;

		while (outerLoop) {
			// Starting Menu For LogIn
			System.out.println("Welcome To Employee Management System");
			System.out.println("Enter Your Employee Id");
			System.out.println("Close Application press -1");
			innerLoop = true;
			ServiceClassOfEmployee services = new ServiceClassOfEmployee(); 
			try {
				empId = Integer.parseInt(validateUserChoice(scanner));
			} catch (Exception e) {
				System.out.println("Kindly Enter Only Interger");
				continue;
			}
			if (empId == -1) {
				try {
					conn.close();
					break;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			}

			if (services.isEmpIdExist(conn, empId)) {
				System.out.println("Employee Id Not Exist");
				continue;
			}
			String designationOfEmployee = services.printDesignation(conn,empId );
		
			
			Employee employee = null;
			 
			switch (designationOfEmployee) {
			case "CEO":
				employee = new CEO();
				while (innerLoop) {
					printMenu(conn, scanner, employee, empId);
				}
				break;
			case "HR":
				employee = new HR();
				while (innerLoop) {
					printMenu(conn, scanner, employee, empId);
				}
				break;
			case "Director":
				employee = new Director();
				while (innerLoop) {
					printMenu(conn, scanner, employee, empId);
				}
				break;
			case "Manager":
				employee = new Manager();
				while (innerLoop) {
					printMenu(conn, scanner, employee, empId);
				}
				break;
			case "Fresher":
				employee = new Fresher();
				while (innerLoop) {
					System.out.println("1. Show Report Person");
					System.out.println("2. GetProfile");
					System.out.println("3. Logout");
					userChoise = Integer.parseInt(validateUserChoice(scanner));
					switch (userChoise) {
					case 1:
						employee.showReportPerson(conn, empId);
						break;
					case 2:
						employee.showProfile(conn, empId);
						break;
					case 3:
						innerLoop = false;
						break;
					default:
						System.out.println("Invalid Input");
						break;
					}
				}

			}
		}
		
		System.out.println("Thank You");
	}

	private static void printMenu(Connection conn, Scanner scanner, Employee employee,
			int empId) throws IOException {

		System.out.println("1. Show Report Person");
		System.out.println("2. GetProfile");
		System.out.println("3. Show Team Under Your Supervision");
		System.out.println("4. Add New Member in Your SuperVision");
		System.out.println("5. Promote Employee");
		System.out.println("6. Recruit New Employee");
		System.out.println("7. Logout");
		Integer userChoise = 0;
		try {
			userChoise = Integer.parseInt(validateUserChoice(scanner));
		} catch (Exception e) {
			System.out.println("Kindly Enter Only Interger");
		}
		switch (userChoise) {
		case 1:
			employee.showReportPerson(conn, empId);
			break;
		case 2:
			employee.showProfile(conn, empId);
			break;
		case 3:
			employee.showTeamMember(conn, empId);
			break;
		case 4:
			System.out.println("Please Enter Employee Id to Whom You Want in your Team");
			Integer userInput = 0;
			try {
				userInput = Integer.parseInt(validateUserChoice(scanner));
			} catch (Exception e) {
				System.out.println("Kindly Enter Only Interger");
				System.out.println("Returning To Main Menu And Try it Again\n");
				break;
			}
			employee.addTeamMember(conn, empId, userInput);
			break;
		case 5:
			System.out.println("Enter Employee Id You Want To Promote");
			Integer candidateId = 0;
			try {
				candidateId = Integer.parseInt(validateUserChoice(scanner));
			} catch (Exception e) {
				System.out.println("Kindly Enter Only Interger");
				System.out.println("Returning To Main Menu And Try it Again\n");
				break;
			}
			employee.promoteEmployee(conn, empId, candidateId);
			break;
		case 6:
			System.out.println("Enter First Name Of New Employee Without Space and Special Character");
			String nameOfNewEmployee = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			// nameOfNewEmployee += scanner.nextLine();
			nameOfNewEmployee = br.readLine();
			if (!valiateString(nameOfNewEmployee)) {    // validating string contains only character 
				System.out.println("Name Contains only alphabet");
				System.out.println("Returning To Main Menu And Try it Again\n");
				break;
			}
			while (true) {
				System.out.println("1. Director");
				System.out.println("2. HR");
				System.out.println("3. Manager");
				System.out.println("4. Fresher");
				try {
					userInput = Integer.parseInt(validateUserChoice(scanner));
				} catch (Exception e) {
					System.out.println("Kindly Enter Only Integer");
					continue;
				}
				if (userInput < 1 || userInput > 4) {
					System.out.println("Wrong Input");
					continue;
				}
				employee.addEmployees(conn, nameOfNewEmployee, userInput+1);
				break;
			}
			break;
		case 7:
			innerLoop = false;
			break;
		default:
			System.out.println("Invalid Input");
			break;
		}

	}
	private static String validateUserChoice(Scanner scanner) { // Take Only Integer
		while (!scanner.hasNextLine()) {
			System.out.println("Wrong Input !! Kindly Enter Only Integer");
			scanner.next(); // this is important!
		}
		return scanner.nextLine();
	}

	private static boolean valiateString(String name) { // String Contains Only alphabet
		// Handle Enter Condition
		if (name.isEmpty())
			return false;

		char[] chars = name.toCharArray();
		for (char c : chars) {
			if (!Character.isLetter(c)) {
				return false;
			}
		}

		return true;

	}
}
