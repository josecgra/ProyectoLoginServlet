package es.eoi.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
			List<Alumno> listado = ar.findAll();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<style>");
			out.println("table, th, td { border: 1px solid black; }");
			out.println("</style>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1> Bienvenido " + alusesion.getNombre() + " " + alusesion.getApellidos() + "</h1>");
			out.println("<br>");
			out.println("<table>");
			// cabecera >>
			out.println("<tr>");
			out.println("<th>");
			out.println("dni");
			out.println("</th>");
			out.println("<th>");
			out.println("nombre");
			out.println("</th>");
			out.println("<th>");
			out.println("apellidos");
			out.println("</th>");
			out.println("<th>");
			out.println("edad");
			out.println("</th>");
			out.println("<th>");
			out.println("email");
			out.println("</th>");
			out.println("<th>");
			out.println("pass");
			out.println("</th>");
			out.println("</tr>");
			// cabecera <<
			
			for (Alumno a : listado) {
				out.println("<tr>");
				out.println("<td>");
				out.println(a.getDni());
				out.println("</td>");
				out.println("<td>");
				out.println(a.getNombre());
				out.println("</td>");
				out.println("<td>");
				out.println(a.getApellidos());
				out.println("</td>");
				out.println("<td>");
				out.println(a.getEdad());
				out.println("</td>");
				out.println("<td>");
				out.println(a.getEmail());
				out.println("</td>");
				out.println("<td>");
				out.println(a.getPass());
				out.println("</td>");
				out.println("</tr>");
			}
			
			out.println("</table>");
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
