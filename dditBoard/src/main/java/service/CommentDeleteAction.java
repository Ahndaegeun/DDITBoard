package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.CommentDAO;
import vo.CommentLHVO;
import vo.CommentVO;

public class CommentDeleteAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		CommentDAO dao = CommentDAO.getInstance();
		CommentVO vo = new CommentVO();
		CommentLHVO colh = new CommentLHVO();
		HttpSession session = request.getSession();
		int coNum = Integer.parseInt(request.getParameter("number"));
		int boNum = Integer.parseInt(request.getParameter("board"));
		
		vo.setBoardIdx(boNum);
		vo.setCommIdx(coNum);
		
		colh.setCommIdx(boNum);
		colh.setMemId(session.getAttribute("user") + "");
		
		boolean coResult = dao.deleteComment(vo);
		
		
		
		out.println("<script>");
		if(coResult) {
			out.println("alert('삭제 완료')");
		} else {
			out.println("alert('삭제 안됨')");
		}
		out.println("location.replace('/member?cmd=index')");
		out.println("</script>");
		
	}
}
