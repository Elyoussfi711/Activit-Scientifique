package DAO;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.SQLException;
public class Connexion {
	private static Connection connection;
	//System.out.println("AAA");
	static {
		String url="jdbc:mysql://localhost:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user="root";
		String password="Medmed 2000";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url,user,password);
			System.out.println("connexion établie !!!");
		} 
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		return connection;
	}

}




























