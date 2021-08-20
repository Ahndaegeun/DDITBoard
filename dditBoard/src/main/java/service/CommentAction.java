package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CommentDAO;
import vo.CommentVO;
import vo.MemberVO;

public class CommentAction implements Action {
	private static CommentAction instance;
	private CommentAction() {}
	public static CommentAction getCommentAction() {
		if(instance == null) {
			instance = new CommentAction();
		}
		return instance;
	}
	
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentDAO dao = CommentDAO.getInstance();
		CommentVO vo = new CommentVO();
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO) session.getAttribute("user");
		PrintWriter out = response.getWriter();
		
		String[] anony = request.getParameterValues("commAnon");
		String contents = request.getParameter("commArea");
		System.out.println(request.getParameter("comInBoard"));
		int boardNum = Integer.parseInt(request.getParameter("comInBoard"));
		String id = user.getMemId();
		
		if(anony == null) {
			vo.setCommAnon("N");
		} else {
			vo.setCommAnon("Y");			
		}
		vo.setCommContent(contents);
		vo.setMemId(id);
		vo.setBoardIdx(boardNum);
		
		boolean result = dao.insertComment(vo);
		
		out.println("<html>");
		out.println("<script>");
		if(result) {
			out.println("alert('작성 완료!')");
		} else {
			out.println("alert('작성 실패...')");
		}
		out.println("location.replace('/member?cmd=index')");
		out.println("</script>");
		out.println("</html>");
	}
}
