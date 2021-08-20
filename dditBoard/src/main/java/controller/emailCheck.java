package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.SendMail;

@WebServlet("/emailCheck")
public class emailCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public emailCheck() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lisence = request.getParameter("lisence");
		String email = request.getParameter("userEmail");
		
		String result = SendMail.getLicenseNumber().get(email);
		if(lisence.equals(result)) {
			response.getWriter().write("1");
		} else {
			response.getWriter().write("0");
		}
	}

}
