package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import util.SHA256Util;
import vo.MemberVO;

public class SignupAction implements Action {
	private static SignupAction instance;
	private SignupAction() {}
	public static SignupAction getSignupAction() {
		if(instance == null) {
			instance = new SignupAction();
		}
		return instance;
	}
	
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = MemberDAO.getMemberDAO();
		SHA256Util hash = SHA256Util.getInstance();
		MemberVO vo = new MemberVO();
		PrintWriter out = response.getWriter();
		
		String pw = hash.encrypt(request.getParameter("signUpPw"));
		
		vo.setMemId(request.getParameter("signUpId"));
		vo.setMemPw(pw);
		vo.setMemNm(request.getParameter("signUpName"));
		vo.setMemEmail(request.getParameter("signUpEmail"));
		vo.setMemRegno(request.getParameter("signUpRegno"));
		
		if(dao.joinCheck(vo) && !dao.isDdit(vo)) {
			out.println("<html>");
			out.println("<script>");
			out.println("alert('회원가입 실패!')");
			out.println("history.back()");
			out.println("</script>");
			out.println("</html>");
		} else if(dao.join(vo) && dao.changeDditList(vo)) {
			out.println("<html>");
			out.println("<script>");
			out.println("alert('회원가입 완료!')");
			out.println("</script>");
			out.println("</html>");
			response.sendRedirect("index.jsp");
		} else {
			out.println("<html>");
			out.println("<script>");
			out.println("alert('알 수 없는 오류')");
			out.println("history.back()");
			out.println("</script>");
			out.println("</html>");
		}
		
	}
}
