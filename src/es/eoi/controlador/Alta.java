package es.eoi.controlador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.eoi.modelo.Alumno;
import es.eoi.repository.AlumnoRepository;

/**
 * Servlet implementation class Alta
 */
@WebServlet("/alta")
public class Alta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Alta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dni = request.getParameter("dni");
		String nom = request.getParameter("nombre");
		String ape = request.getParameter("apellidos");
		int edad = Integer.parseInt(request.getParameter("edad"));
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String rol = request.getParameter("rol");
		
		String mensaje = "";
		String pagina = "index.html";	
		
		if (rol.equals("admin") || rol.equals("visitante")) {
			
			Alumno alu = new Alumno(dni,nom,ape,edad,email,pass,rol);
			AlumnoRepository ar = new AlumnoRepository();
			ar.alta(alu);
		} else {
			mensaje="Rol incorrecto, solo validos (admin) y (visitante)";
			request.setAttribute("msgerr", mensaje);
			pagina="alta.jsp";
		}
		
		// valido que el email dado de alta solo pueda ser con @gmail.com
		if (!email.contains("@gmail.com")) {
			mensaje="Email incorrecto, solo son válidos los @gmail.com";
			request.setAttribute("msgerr", mensaje);
			pagina="alta.jsp";
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher(pagina);
		rd.forward(request, response);
	}

}
