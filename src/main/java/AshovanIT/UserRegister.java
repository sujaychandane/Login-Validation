package AshovanIT;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UserRegister()
    {
       System.out.println("helo");
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		 response.setContentType("text/html");
		
		String Uname=request.getParameter("name");
		String Umail=request.getParameter("email");
		
		String Ucon=request.getParameter("num");
		Long Umob = Long.parseLong(Ucon);
		
		String Uid=request.getParameter("username");
		String Upass=request.getParameter("pass");
		String Uyop=request.getParameter("yop");
		String Ucity=request.getParameter("city1");
		String Upin=request.getParameter("pin");
		
//		out.println(Uname);
//		out.println(Umail);
//		out.println(Umob);
//		out.println(Uid);
//		out.println(Upass);
//		out.println(Uyop);
//		out.println(Ucity);
//		out.println(Upin);
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ashovanit","root","1234");
			PreparedStatement ps = con.prepareStatement("insert into studentinfo values(?,?,?,?,?,?,?,?)");
		
					ps.setString(1, Uname);
					ps.setString(2, Umail);
					ps.setLong(3, Umob);
					ps.setString(4, Uid);
					ps.setString(5, Upass);
					ps.setString(6, Uyop);
					ps.setString(7, Ucity);
					ps.setString(8, Upin);
					
					
					
					int s = ps.executeUpdate();

					if (s < 0) 
					{
						out.println("<h2 style='color:red'> Data Inserted Fail</h2><br>");
					}
					
					else 
					{
						RequestDispatcher rd= request.getRequestDispatcher("LoginLink.html");
						rd.include(request,response);		 
					}

					
		} 
		catch (Exception e)
		 {
			e.printStackTrace();
		 }
	}

}
