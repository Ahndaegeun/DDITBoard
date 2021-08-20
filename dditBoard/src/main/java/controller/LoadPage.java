package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import dao.BoardDAO;
import dao.CommentDAO;
import dao.MemberDAO;
import vo.BoardVO;
import vo.CommentVO;
import vo.MemberVO;

@WebServlet("/LoadPage")
public class LoadPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoadPage() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		BoardDAO bDao = BoardDAO.getBoardDAO();
		CommentDAO cDao = CommentDAO.getInstance();
		MemberDAO mDao = MemberDAO.getMemberDAO();
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		int bCnt = bDao.countBoard();
		List<BoardVO> boardList = null;
		List<CommentVO> commList = null;
		
		
		if(bCnt > 0) {
			int startNum = Integer.parseInt(request.getParameter("loadedRow"));
			int endNum = startNum + 10;
			if(endNum >= bCnt) {
				boardList = bDao.printOutBoard(startNum, bCnt);
			} else {
				boardList = bDao.printOutBoard(startNum, endNum);				
			}
		}
		System.out.println(request.getParameter("loadedRow"));
		
		JSONObject outer = new JSONObject();
		
		int i = 0;
		for(BoardVO vo : boardList) {
			JSONObject board = new JSONObject();
			board.put("boardIdx", vo.getBoardIdx());
			board.put("boardTitle", vo.getBoardTitle());
			board.put("boardContent", vo.getBoardContent());
			board.put("boardDate", vo.getBoardDate());
			board.put("boardAnon", vo.getBoardAnon());
			board.put("boardLike", vo.getBoardLike());
			board.put("boardHate", vo.getBoardHate());
			board.put("memId", vo.getMemId());
			board.put("commCnt", vo.getComCnt());
			String name = mDao.getUserName(vo.getMemId());
			board.put("boardUserName", name);
			
			if(user.getMemId().equals(vo.getMemId())) {
				board.put("boardUser", user.getMemId());
			}
			
			//Storage에 유저 이미지가 있으면 추가 없으면 기본이미지 추가
			//File dir = new File("/Users/andaegeun/Desktop/95main/dditBoard/src/main/webapp/storage");
			File dir = new File("/adg0807/tomcat/webapps/ROOT/storage");
		    File[] fileList = dir.listFiles();

		    if(fileList != null) {
		    	for(int j = 0; j < fileList.length; j++) {
		    		File file = fileList[j];
		    		if(file.isFile()){
		    			int dot = file.getName().lastIndexOf(".");
		    			String fileName = file.getName().substring(0, dot);
		    			if(fileName.equals(vo.getMemId())) {
		    				board.put("boardUserImg", file.getName());
		    				break;
		    			}
		    		}
		    	}		    	
		    } else {
		    	board.put("boardUserImg", "defaultImg.png");
		    }
		    
		    
			
			JSONObject commOuter = new JSONObject();
			commList = cDao.getCommentList(vo.getBoardIdx());
			
			if(commList != null) {
				int j = 0;
				for(CommentVO cVo : commList) {
					JSONObject commInner = new JSONObject();
					commInner.put("comBoardIdx", cVo.getBoardIdx());
					commInner.put("comIdx", cVo.getCommIdx());
					commInner.put("comMemId", cVo.getMemId());
					commInner.put("comContent", cVo.getCommContent());
					commInner.put("comDate", cVo.getCommDate());
					commInner.put("comAnon", cVo.getCommAnon());
					commInner.put("commState", cVo.getCommState());
					String cName = mDao.getUserName(cVo.getMemId());
					commInner.put("commUserName", cName);
					
					if(user.getMemId().equals(cVo.getMemId())) {
						commInner.put("commentUser", user.getMemId());
					}
					
					commOuter.put(j++, commInner);
				}
			}
			board.put("commList", commOuter);
			outer.put(i++, board);
		}
		
		if(Integer.parseInt(request.getParameter("loadedRow")) != bCnt) {
			response.getWriter().write(outer.toJSONString());			
		}
	}

}
