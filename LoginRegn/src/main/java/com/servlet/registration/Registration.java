package com.servlet.registration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/register")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		String uname = request.getParameter("name");
    		String uemail  = request.getParameter("email");
    		String upwd = request.getParameter("pass");
    		String umobile = request.getParameter("contact");
    		RequestDispatcher dispatcher = null;
    		Connection conn = null;
    		
    		try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/regnlogn?useSSL=false","root","8310081967");
				//conn = DriverManager.getConnection("jdbc:mysql://root:3ah1hag1ghd1Ba251ah2C-32aGDE31C4@viaduct.proxy.rlwy.net:50284/railway","root","3ah1hag1ghd1Ba251ah2C-32aGDE31C4");
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO customer(uname,upwd,uemail,umobile) VALUES (?,?,?,?)");
				pstmt.setString(1,uname);
				pstmt.setString(2, upwd);
				pstmt.setString(3, uemail);
				pstmt.setString(4, umobile);
				
				int rowCount = pstmt.executeUpdate();
				dispatcher = request.getRequestDispatcher("registration.jsp");
				
				if(rowCount > 0) {
					request.setAttribute("status", "success");
				}
				else {
					request.setAttribute("status", "failed");
				}
				dispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
    		
//    		PrintWriter out = response.getWriter();
//    		out.print(uname);
//    		out.print(uemail);
//    		out.print(upwd);
//    		out.print(umobile);
    	}

}
