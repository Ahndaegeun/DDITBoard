package service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import dao.CommentDAO;
import vo.BoardVO;
import vo.CommentVO;
import vo.MemberVO;

public class IndexAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		BoardDAO bDao = BoardDAO.getBoardDAO();
		CommentDAO cDao = CommentDAO.getInstance();
		List<BoardVO> boardList = new ArrayList<>();
		boardList = bDao.printOutBoard(1, 4);
 		List<CommentVO> comment = cDao.getCommentList(6);
 		Map<Integer, Integer> commCount = new HashMap<>();
 		commCount = cDao.getCommentCount();
 		
 		String root = "/adg0807/tomcat/webapps/ROOT/storage";
 		File path = new File(root);
 		File[] fileList = path.listFiles();
		String imgName = "defaultImg.png";
		String temp;
		MemberVO vo = (MemberVO)session.getAttribute("user");
		
		if(fileList != null) {
			for(int i = 0; i < fileList.length; i++) {
				temp = fileList[i].getName();
				int dot = temp.lastIndexOf(".");
				String result = temp.substring(0, dot);
				if(result.equals(vo.getMemId())) {
					imgName = temp;
					break;
				}
			}
		}
		
		if(session.getAttribute("user") != null) {
			session.setAttribute("userImg", imgName);
			request.setAttribute("boardList", boardList);
			request.setAttribute("commentList", comment);
			request.setAttribute("commCount", commCount);
			request.getRequestDispatcher("WEB-INF/html/main.jsp").forward(request, response);
		}
	}
}
