package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import vo.MemberVO;

public class SearchIdAction implements Action {
	private static SearchIdAction instance;
	private SearchIdAction() {}
	public static SearchIdAction getSearchIdAction() {
		if(instance == null) {
			instance = new SearchIdAction();
		}
		return instance;
	}
	
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = MemberDAO.getMemberDAO();
		MemberVO vo = new MemberVO();
		
		vo.setMemNm(request.getParameter("idName"));
		vo.setMemRegno(request.getParameter("idRegno"));
		
		
		MemberVO result = dao.findId(vo);
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<script>");

		if(result != null) {
			out.println("alert('" + result.getMemId() + "')");
		} else {
			out.println("alert('입력 확인!')");
		}
		
		out.println("history.back()");
		out.println("</script>");
		out.println("</html>");			
	}
	
}
