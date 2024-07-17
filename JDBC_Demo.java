package com.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Demo {

	public static void main(String[] args) throws SQLException {

		readRecords();
	}

	public static  void readRecords() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/tutorial";
		String userName = "root";
		String password = "Thara@1234";
		String query = "SELECT * FROM tutorial.student";

		Connection con = DriverManager.getConnection(url, userName, password);

		Statement st = con.createStatement();

		ResultSet rs = st.executeQuery(query);
		rs.next();
		System.out.println("Roll no :"+rs.getInt(1));
		System.out.println("Name : "+rs.getString(2));
		System.out.println("Middle name : "+rs.getString(3));

		con.close();
	}
	public static  void insertRecords() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/tutorial";
		String userName = "root";
		String password = "Thara@1234";
		String query = "insert into tutorial.student values (103, 'Pranav', 'Thara')";	

		Connection con = DriverManager.getConnection(url, userName, password);

		Statement st = con.createStatement();

		int rows = st.executeUpdate(query);
		System.out.println("No of Rows affected : "+rows);

		con.close();
	}
	public static  void insertPST() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/tutorial";
		String userName = "root";
		String password = "Thara@1234";

		int rollno = 104;
		String name = "khef";
		String middlename = "kzH";
		String query = "insert into tutorial.student values (?,?,?);";	

		Connection con = DriverManager.getConnection(url, userName, password);

		PreparedStatement pst = con.prepareStatement(query);

		pst.setInt(1, rollno);
		pst.setString(2, name);
		pst.setString(3, middlename);
		int rows = pst.executeUpdate();
		System.out.println("No of Rows affected : "+rows);
		con.close();
	}
	public static void delRecords() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/tutorial";
		String userName = "root";
		String password = "Thara@1234";

		String name = "khef";
		String query = "DELETE FROM tutorial.student WHERE name = ?";

		Connection con = DriverManager.getConnection(url, userName, password);
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, name);
		int rows = pst.executeUpdate();
		System.out.println("No of Rows affected: " + rows);
	}
	public static void updateRec() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/tutorial";
		String userName = "root";
		String password = "Thara@1234";

		String middlename = "Cute";
		String query = "update tutorial.student set middlename =? WHERE rollno = 103";

		Connection con = DriverManager.getConnection(url, userName, password);
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, middlename);
		int rows = pst.executeUpdate();
		System.out.println("No of Rows affected: " + rows);
	}
	public static  void sp() throws SQLException {

		String url = "jdbc:mysql://localhost:3306/tutorial";
		String userName = "root";
		String password = "Thara@1234";

		Connection con = DriverManager.getConnection(url, userName, password);

		CallableStatement cst = con.prepareCall("{call GetEmp()}");
		ResultSet rs = cst.executeQuery();
		while(rs.next()){
			System.out.println("Roll no :"+rs.getInt(1));
			System.out.println("Name : "+rs.getString(2));
			System.out.println("Middle name : "+rs.getString(3));
		}

		con.close();
	}
	//commit vs autocommit
	public static  void commit() throws SQLException {

		String url = "jdbc:mysql://localhost:3306/tutorial";
		String userName = "root";
		String password = "Thara@1234";

		String query1 = "update student set middlename ='sg' where rollno = 101";
		String query2 = "update student set middlename ='sg' where rollno = 102";
		String query3 = "update student set middlename ='sg' where rollno = 103";
		String query4 = "updat student set middlename ='sg' where rollno = 104";

		Connection con = DriverManager.getConnection(url, userName, password);
		con.setAutoCommit(false);

		Statement st = con.createStatement();
		st.addBatch(query1);
		st.addBatch(query2);
		st.addBatch(query3);
		st.addBatch(query4);
		int[] res = st.executeBatch();
		for (int i : res) {
			System.out.println("Rows affted :"+i);
			
			if(i>0)
				con.commit();
		}
		con.close();
	}

}
