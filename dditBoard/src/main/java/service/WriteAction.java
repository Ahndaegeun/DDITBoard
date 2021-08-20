package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import vo.BoardVO;
import vo.MemberVO;

public class WriteAction implements Action {
	private static WriteAction instance;

	private WriteAction() {
	}

	public static WriteAction getWriteAction() {
		if (instance == null) {
			instance = new WriteAction();
		}
		return instance;
	}

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO dao = BoardDAO.getBoardDAO();
		BoardVO vo = new BoardVO();
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO) session.getAttribute("user");
		PrintWriter out = response.getWriter();

		String[] anony = request.getParameterValues("writeAnon");
		String title = request.getParameter("writeTitle");
		String contents = request.getParameter("writeArea");
		String id = user.getMemId();

		if (anony == null) {
			vo.setBoardAnon("N");
		} else {
			vo.setBoardAnon("Y");
		}
		
		title = title.replaceAll("<", "&lt;");
		title = title.replaceAll(">", "&gt;");
		contents = contents.replaceAll("<", "&lt;");
		contents = contents.replaceAll(">", "&gt;");
		
		contents.replaceAll("\r", "").replaceAll("\n", "<br>");

		vo.setBoardTitle(title);
		vo.setBoardContent(contents);
		vo.setMemId(id);

		int result = dao.registerBoard(vo);

		out.println("<html>");
		out.println("<script>");
		if (result > 0) {
			out.println("alert('작성 완료!')");
		} else {
			out.println("alert('작성 실패...')");
		}
		out.println("location.replace('/member?cmd=index')");
		out.println("</script>");
		out.println("</html>");
	}
}
