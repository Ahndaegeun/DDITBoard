package service;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.BoardDAO;
import dao.CommentDAO;
import vo.BoardVO;

public class BoardDeleteAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO dDao = BoardDAO.getBoardDAO();
		CommentDAO cDao = CommentDAO.getInstance();
		BoardVO vo = new BoardVO();
		PrintWriter out = response.getWriter();
		int bIdx = Integer.parseInt(request.getParameter("boardNum"));
		
		vo.setBoardIdx(bIdx);
		
		cDao.deleteComLHAll(vo);
		cDao.deleteBoardComment(vo);
		
		boolean dDel = dDao.deleteBoard(vo);
		
		out.println("<script>");
		if(dDel) {
			out.println("alert('삭제 완료')");
		} else {
			out.println("alert('삭제 실패')");
		}
		out.println("location.replace('/member?cmd=index')");
		out.println("</script>");
	}
}
