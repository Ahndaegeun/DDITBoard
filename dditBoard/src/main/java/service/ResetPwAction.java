package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import util.SHA256Util;
import util.SendMail;
import util.TemporaryPassword;
import vo.MemberVO;

public class ResetPwAction implements Action{
	private static ResetPwAction instance;
	private ResetPwAction() {}
	public static ResetPwAction getResetPwAction() {
		if(instance == null) {
			instance = new ResetPwAction();
		}
		return instance;
	}
	
	
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = MemberDAO.getMemberDAO();
		MemberVO vo = new MemberVO();
		TemporaryPassword tp = TemporaryPassword.getInstance();
		SHA256Util sha = SHA256Util.getInstance();
		SendMail sm = SendMail.getInstance();
		
		PrintWriter out = response.getWriter();
		
		vo.setMemId(request.getParameter("pwId"));
		vo.setMemNm(request.getParameter("pwName"));
		vo.setMemRegno(request.getParameter("pwRegno"));
		
		MemberVO userInfo = new MemberVO();
		userInfo = dao.selectInfo(vo);
		
		String pw = tp.getTemporaryPassword();
		sm.ToSendMail(userInfo.getMemEmail(), pw);
		
		pw = sha.encrypt(pw);
		vo.setMemPw(pw);
		
		boolean flag = dao.updatePw(vo);
		
		out.println("<html>");
		out.println("<script>");

		if(flag) {
			out.println("alert('임시 비밀번호를 메일로 보내드렸습니다.')");
		} else {
			out.println("alert('입력을 확인해 주세요.')");
		}
		
		out.println("history.back()");
		out.println("</script>");
		out.println("</html>");
	}
}
