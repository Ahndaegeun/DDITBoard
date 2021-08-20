package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import util.SHA256Util;
import vo.MemberVO;

public class ChangePwAction implements Action {
	private static ChangePwAction instance;
	private ChangePwAction() {}
	public static ChangePwAction getChangePwAction() {
		if(instance == null) {
			instance = new ChangePwAction();
		}
		return instance;
	}
	
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		MemberDAO dao = MemberDAO.getMemberDAO();
		SHA256Util hash = SHA256Util.getInstance();
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO) session.getAttribute("user");
		String changePw = request.getParameter("changePw");
		String pw = hash.encrypt(changePw);
		vo.setMemPw(pw);
		
		boolean result = dao.updatePw(vo);

		out.println("<html>");
		out.println("<script>");
		if(result) {
			out.println("alert('비밀번호 변경 완료!')");
		} else {
			out.println("alert('비밀번호 변경 실패...')");
		}
		out.println("location.replace('/member?cmd=index')");
		out.println("</script>");
		out.println("</html>");			
	}
}
