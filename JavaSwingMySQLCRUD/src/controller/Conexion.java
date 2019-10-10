package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private Connection DbConnection;
	
	public Connection conectar() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // esta no esta depreciada
			DbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbeducar_java","root","");
			
			
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DbConnection;
		
	}
}
