package AshovanIT;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public login() 
    {
        super();
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
	  response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String Uid=request.getParameter("UserId");
		String Upass=request.getParameter("pass");
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ashovanit","root","1234");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select  UserId,Password from studentinfo");
			
			int count=0;
			while (rs.next())
			{
			 String id=rs.getString(1);
			 String pass=rs.getString(2);
			 
			  HashMap<String, String> mp=new HashMap<>();
			  mp.put(pass, id);
	 
			  
			          for (Map.Entry<String, String> entry : mp.entrySet())
			          {
			              String ps = entry.getKey();
			              String ied = entry.getValue();
			              System.out.println("Key=" + ps + ", Value=" + ied);
			              
			              if (Uid.equals(ied) && Upass.equals(ps))
			              {
							count++;
						}
			          }
			      }
			  
			if (count>0) 
			{
				 out.println("<h3 style='color:green';text-align: center> Congratulations You Login Sucessfully </h3>");	
				 out.println("<h3 style='color:green';text-align: center> Welcome To Sujay World </h3>");	
			}
			else
			{
				out.println("<center><h2 style='color:black'> Please First Register Then Login </h2></center>");
		    	
		  		  // out.println("No");
				RequestDispatcher rd=request.getRequestDispatcher("registretion.html");
				rd.include(request,response);
			}
		} 
		catch (ClassNotFoundException | SQLException e)
		{
				e.printStackTrace();
		}
	}
}
