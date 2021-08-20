package dao;
	
import java.util.List;

import vo.CommentVO;
	
public interface CommentInterface {
	boolean insertComment(CommentVO vo);
	boolean deleteComment(CommentVO vo);
	List<CommentVO> getCommentList(int idx);
	boolean clickCommentLike(CommentVO vo);
	boolean clickCommentHate(CommentVO vo);
	List<CommentVO> showTotalCommentLike(CommentVO vo);
	List<CommentVO> showTotalCommentHate(CommentVO vo);
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	