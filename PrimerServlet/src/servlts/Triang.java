package servlts;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Triang")
public class Triang extends HttpServlet {
private static final long serialVersionUID = 1L;
public Triang() {
super();
}
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
int lado1, lado2, lado3;
response.setContentType("text/html");
PrintWriter flujoEscritura=response.getWriter();
flujoEscritura.append("<!DOCTYPE html>");
flujoEscritura.append("<html><head><meta charset='UTF-8'>");
flujoEscritura.append("<title>Página dinámica</title>");
flujoEscritura.append("</head><body>");
try {
lado1 = Integer.parseInt(request.getParameter("lado12"));
lado2 = Integer.parseInt(request.getParameter("lado22"));
lado3 = Integer.parseInt(request.getParameter("lado32"));
flujoEscritura.append("<h2>El triangulo con lados " + lado1 + ", " +
lado2 + ", " + lado3 + " es ");
if (lado1==lado2 && lado2==lado3)
flujoEscritura.append("EQUILÁTERO</h2>");
else if (lado1==lado2 || lado2==lado3 || lado1==lado3)
flujoEscritura.append("ISÓSCELES</h2>");
else
flujoEscritura.append("ESCALENO</h2>");
} catch (NumberFormatException e) {
flujoEscritura.append("Alguno de los lados que has introducido no es correcto");

}
flujoEscritura.append("</body></html>");
flujoEscritura.close();
}
protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
doGet(request, response);
}
}