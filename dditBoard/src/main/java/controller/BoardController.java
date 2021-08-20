package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Action;
import service.BoardDeleteAction;
import service.CommentAction;
import service.CommentDeleteAction;
import service.WriteAction;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String cmd = request.getParameter("cmd");
		Action action = null;
		
		if(cmd.equals("writePro")) {
			action = WriteAction.getWriteAction();
		} else if(cmd.equals("commentPro")) {
			action = CommentAction.getCommentAction();
		} else if(cmd.equals("boardDelete")) {
			action = new BoardDeleteAction();
		} else if(cmd.equals("commentDelete")) {
			action = new CommentDeleteAction();
		}
		
		
		
		action.excute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		doGet(request, response);
	}

}
