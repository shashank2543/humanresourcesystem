package com.girnar.humanResource.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceClassOfEmployee {
	

	public void teamUnderYou(Connection conn, int empId) {
		try {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			pstmt = conn.prepareStatement("select Teammember_id from Team where id=" + empId + ";");
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				System.out.println("No Team Under You");
				return;
			}
			System.out.println("Team Under You:");
			rs.beforeFirst();
			while (rs.next()) {
				printDesignation(conn, rs.getInt(1) );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addTeamMember(Connection conn, int empId, int newTeamMemberId) {
		try {
			PreparedStatement pstmt = null;
			if (isEmpIdExist(conn, newTeamMemberId)) {
				System.out.println("Enter Valid Id");
				return;
			} else if (empId == newTeamMemberId) {
				System.out.println("Error");
				return;
			} else {
				pstmt = conn.prepareStatement("UPDATE Employee SET head_id =" + empId + ",updated_at = NOW()  where id =" + newTeamMemberId + ";");
				pstmt.executeUpdate();
				pstmt = conn.prepareStatement("INSERT  INTO Team VALUES(" + empId + "," + newTeamMemberId + ",NOW(),NOW());");
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println("Already Present in your team\n");
		}

	}
	public void getReportingHeads(Connection conn, int empId) {
		try {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			pstmt = conn.prepareStatement("select head_id from Employee where Employee.id=" + empId);
			rs = pstmt.executeQuery();
			rs.next();
			if(rs.getInt(1) == -1) {
				System.out.println("No one to Report \n");
				return;
			} else {
				System.out.println("Report To : ");
				printDesignation(conn, rs.getInt(1));
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void promoteEmployee(Connection conn, int empId, int newDesignation) {
		try {
			PreparedStatement pstmt = null;
			pstmt = conn.prepareStatement(
					"update Employee set designation_id = " + newDesignation  + ",updated_at = NOW()   where id=" + empId + ";");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean isEmpIdExist(Connection conn, int empId) {

		try {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			pstmt = conn.prepareStatement("SELECT id FROM Employee WHERE id =" + empId + ";");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	public String printDesignation(Connection conn, int empId) {

		try {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			pstmt = conn.prepareStatement(
					"select Employee.id,Employee.name,Designation.designation_name from Employee inner join Designation on Employee.designation_id = Designation.designation_id where Employee.id ="
							+ empId + ";");
			rs = pstmt.executeQuery();
			rs.next();
			System.out.println("ID-" + rs.getInt(1));
			System.out.println("Name-" + rs.getString(2));
			System.out.println("Designation-" + rs.getString(3) + "\n");
			return rs.getString(3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void addEmployees(Connection conn, String name, int Designation) {
		
		try {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			pstmt = conn.prepareStatement("insert into Employee values(NULL," + "?" + "," + Designation + ",NOW(),NOW(),-1);");
			pstmt.setString(1, name);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("select MAX(id) from Employee;"); 
			rs = pstmt.executeQuery();
			rs.next();
			System.out.println("New Generated Id:" + rs.getInt(1) + "\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void showProfile(Connection conn, int empId) {
		printDesignation(conn, empId);
	}

	public String getDesignation(Connection conn, int empId) {
		try {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			pstmt = conn.prepareStatement(
					"select Designation.designation_name from Employee inner join Designation on Employee.designation_id = Designation.designation_id where Employee.id ="
							+ empId + ";");
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
