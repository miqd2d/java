import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class lab10 extends HttpServlet {
    
    // Database connection info
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String DB_USER = "root"; // change if needed
    private static final String DB_PASS = "";     // change if needed

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Display Registration Form
        out.println("<html><body style='font-family: Arial;'>");
        out.println("<h2>Student Registration Form</h2>");
        out.println("<form method='post'>");
        out.println("Name: <input type='text' name='name'/><br/><br/>");
        out.println("Email: <input type='text' name='email'/><br/><br/>");
        out.println("Course: <input type='text' name='course'/><br/><br/>");
        out.println("<input type='submit' value='Register'/>");
        out.println("</form>");

        // Display all registered students
        out.println("<h3>Registered Students:</h3>");
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            out.println("<table border='1' cellpadding='8'><tr><th>ID</th><th>Name</th><th>Email</th><th>Course</th></tr>");
            while(rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td><td>"
                                   + rs.getString("name") + "</td><td>"
                                   + rs.getString("email") + "</td><td>"
                                   + rs.getString("course") + "</td></tr>");
            }
            out.println("</table>");

            rs.close();
            stmt.close();
            conn.close();
        } catch(Exception e) {
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        }

        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String course = request.getParameter("course");

        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO students (name, email, course) VALUES (?, ?, ?)");

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, course);
            ps.executeUpdate();

            ps.close();
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        // After inserting, redirect back to the form
        response.sendRedirect("Lab10");
    }
}
