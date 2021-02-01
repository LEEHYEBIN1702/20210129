package co.micol.board.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.board.common.Command;
import co.micol.board.dao.BoardDao;
import co.micol.board.vo.BoardVo;
import co.micol.board.vo.ReplyVo;

public class BoardView implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 글 상세보기, 상세보기 창에서 댓글 보기 창 만들어줌.. 
		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo();
        ReplyVo rvo = new ReplyVo();
        ArrayList<ReplyVo> list = new  ArrayList<ReplyVo>(); //댓글은 리스트로 넘어오니까..
        
		vo.setbId(Integer.parseInt(request.getParameter("bId")));
		rvo.setBid(Integer.parseInt(request.getParameter("bId"))); //주글 키를 가져가서 그 글에 딸린 댓글을 가져오는 것.
		
		vo = dao.select(vo); //주글 읽어온 후 close됨..
		dao = new BoardDao(); // 다시  Dao를 불러와서 연결 해줘야함.
		list = dao.replySelect(rvo); //댓글
		
		request.setAttribute("vo", vo); //주글 담기
		request.setAttribute("rlist", list); //댓글 담기
		
		return "board/boardView";
	}

}
