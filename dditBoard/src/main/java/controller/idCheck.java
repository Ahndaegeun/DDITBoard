package controller;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import vo.MemberVO;

@WebServlet("/idCheck")
public class idCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public idCheck() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("utf-8");
		
		MemberDAO dao = MemberDAO.getMemberDAO();
		String id = request.getParameter("userid");
		MemberVO vo = new MemberVO(id);
		String pattern = "^[a-zA-Z]{1}[a-zA-Z0-9_]{5,21}$";
		
		boolean regex = Pattern.matches(pattern, id);
		boolean flag = dao.idCheck(vo);
		
		if(id.isEmpty()) {
			response.getWriter().write("3");
			return;
		}
		
		if(!regex) {
			response.getWriter().write("2");
			return;
		}
		
		if(!flag) {
			response.getWriter().write("1");
			return;
		}
		
		if(flag) {
			response.getWriter().write("0");
			return;
		}
		
	}

}
