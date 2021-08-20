package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.SendMail;

@WebServlet("/sendEmail")
public class SendEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SendEmail() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SendMail sm = SendMail.getInstance();
		String mail = request.getParameter("usermail");
		
		SendMail.setLicenseNumber(sm.CreateLicenseNumber(), mail);
		
		sm.ToLicenseMail(mail, SendMail.getLicenseNumber().get(mail));
	}

}
