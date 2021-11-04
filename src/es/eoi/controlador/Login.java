package es.eoi.controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.eoi.modelo.Alumno;
import es.eoi.repository.AlumnoRepository;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		AlumnoRepository ar = new AlumnoRepository();
		Alumno alu = ar.login(email, pass);
		
		HttpSession sesion = request.getSession();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if (alu != null) {
			sesion.setAttribute("alumno", alu);
			
			Alumno alusesion = (Alumno)sesion.getAttribute("alumno");
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<body>");
			out.println("<h1> Bienvenido " + alusesion.getNombre() + " " + alusesion.getApellidos() + "</h1>");
			out.println("<br>");
			out.println("<a href='logout'>Cerrar sesion</a>");
			out.println("</body>");
			out.println("</html>");
		} else {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<body>");
			out.println("<h1> El usuario no existe en la BBDD</h1>");
			out.println("<a href='index.html'>Reintentar</a>");
			out.println("</body>");
			out.println("</html>");
		}
	}

}
