package es.eoi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlumnoRepository {
	
	private Connection openConnection() {

		String url = "jdbc:mysql://localhost:3306/instituto?useSSL=false&serverTimezone=UTC";
		String usuario = "root";
		String pass = "1234";
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				con = DriverManager.getConnection(url, usuario, pass);
			} catch (SQLException e) {
				System.err.println("ERROR al conectar a la BBDD " + e.getMessage());
			}
		} catch (ClassNotFoundException e) {
			System.err.println("No ha encontrado la clase Driver " + e.getMessage());
		}

		return con;
	}
	
	public boolean login(String email, String pass) {
		boolean logado = false;
		Connection con = openConnection();
		
		String sql = "SELECT * FROM alumno WHERE email = ? AND pass = ?";
		
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, pass);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				logado = true;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return logado;
	}
	
}
