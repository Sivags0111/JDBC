package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbc_Practice {


	public static void main(String[] args) throws Exception {

		insertRecords();
	}
	public static void readRecords() throws Exception   {


		String url = "jdbc:mysql://localhost:3306";
		String userName ="root";
		String password = "Thara@1234";
		String query ="select * from jdbc_Practice.student";

		Connection con = DriverManager.getConnection(url, userName, password);

		Statement cs = con.createStatement();

		ResultSet rs = cs.executeQuery(query);

		while(rs.next()) {

			System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
		}

		con.close();

	}
	public static void insertRecords() throws Exception{


		String url = "jdbc:mysql://localhost:3306";
		String userName ="root";
		String password = "Thara@1234";
		String query ="insert into jdbc_Practice.student values (2,'Sita',30)";

		Connection con = DriverManager.getConnection(url, userName, password);

		Statement cs = con.createStatement();

		int rows = cs.executeUpdate(query);


		System.out.println("Number of rows affected: "+rows);

		con.close();

	}

	public static void insertVar() throws Exception{


		String url = "jdbc:mysql://localhost:3306";
		String userName ="root";
		String password = "Thara@1234";
		int id = 5;
		String name = "Zara";
		int age = 35;

		String query ="insert into jdbc_Practice.student values (?,?,?)";

		Connection con = DriverManager.getConnection(url, userName, password);

		PreparedStatement pst = con.prepareStatement(query);

		pst.setInt(1, id);
		pst.setString(2, name);
		pst.setInt(3, age);
		int rows = pst.executeUpdate();

		System.out.println("Number of rows affected: "+rows);

		con.close();

	}
	//DELETE
	public static void delRecords() throws Exception{

		String url = "jdbc:mysql://localhost:3306";
		String userName ="root";
		String password = "Thara@1234";
		int id = 7;

		String query ="delete from jdbc_Practice.student where student_ID= "+id;

		Connection con = DriverManager.getConnection(url, userName, password);

		Statement st = con.createStatement();

		int rows = st.executeUpdate(query);

		System.out.println("Number of rows affected: "+rows);

		con.close();

	}
	//UPDATE
	public static void updateRecords() throws Exception{

		String url = "jdbc:mysql://localhost:3306";
		String userName ="root";
		String password = "Thara@1234";

		String query ="UPDATE jdbc_Practice.student SET Student_Name = 'Sita' WHERE student_ID = 2";

		Connection con = DriverManager.getConnection(url, userName, password);

		Statement st = con.createStatement();

		int rows = st.executeUpdate(query);

		System.out.println("Number of rows affected: "+rows);

		con.close();

	}

	public static void insertRecords1() throws Exception{


		String url = "jdbc:mysql://localhost:3306";
		String userName ="root";
		String password = "Thara@1234";
		String query ="insert into jdbc_Practice.student values (8,'tester',30)";

		Connection con = DriverManager.getConnection(url, userName, password);

		Statement cs = con.createStatement();

		int rows = cs.executeUpdate(query);


		System.out.println("Number of rows affected: "+rows);

		con.close();

	}

}
