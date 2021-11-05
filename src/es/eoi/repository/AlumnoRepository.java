package es.eoi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.eoi.modelo.Alumno;

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
	
	public Alumno login(String email, String pass) {
		Alumno alu = null;
		Connection con = openConnection();
		
		String sql = "SELECT * FROM alumno WHERE email = ? AND pass = ?";
		
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, pass);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				alu = new Alumno(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"), rs.getInt("edad"), rs.getString("email"), rs.getString("pass"), rs.getString("rol"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return alu;
	}
	
	public List<Alumno> findAll() {

		List<Alumno> listaAlumnos = new ArrayList<Alumno>();
		Connection con = openConnection();
		Alumno alu = null;
		String sql = "SELECT * FROM alumno";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				alu = new Alumno(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getInt("edad"), rs.getString("email"),rs.getString("pass"), rs.getString("rol"));
				listaAlumnos.add(alu);
			}

		} catch (SQLException e) {
			System.out.println("ERROR al recuperar todos los alumnos " + e.getMessage());
		}

		return listaAlumnos;
	}
	

	
}
