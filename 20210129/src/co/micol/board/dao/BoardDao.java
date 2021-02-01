package co.micol.board.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.micol.board.common.DAO;
import co.micol.board.vo.BoardVo;
import co.micol.board.vo.ReplyVo;

public class BoardDao extends DAO {
	private PreparedStatement psmt;
	private ResultSet rs;
	
	public ArrayList<BoardVo> selectList() {
		ArrayList<BoardVo> list = new ArrayList<BoardVo>();
		BoardVo vo;
		String sql = "SELECT * FROM BOARD order by 1";
		try {
			psmt=conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new BoardVo();
				vo.setbId(rs.getInt("bid"));
				vo.setbName(rs.getString("bname"));
				vo.setbTitle(rs.getString("btitle"));
				vo.setbContent(rs.getString("bcontent"));
				vo.setbDate(rs.getDate("bdate"));
				vo.setbHit(rs.getInt("bhit"));
				list.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	
	
	public BoardVo select(BoardVo vo) {
		String sql = "SELECT * FROM BOARD WHERE BID = ?";
		
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, vo.getbId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo = new BoardVo();
				vo.setbId(rs.getInt("bid"));
				vo.setbName(rs.getString("bname"));
				vo.setbTitle(rs.getString("btitle"));
				vo.setbContent(rs.getString("bcontent"));
				vo.setbDate(rs.getDate("bdate"));
				vo.setbHit(rs.getInt("bhit"));
				hitCount(rs.getInt("bid"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
		
	}
	
	public BoardVo editselect(BoardVo vo) {
		String sql = "SELECT * FROM BOARD WHERE BID = ?";
		
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, vo.getbId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo = new BoardVo();
				vo.setbId(rs.getInt("bid"));
				vo.setbName(rs.getString("bname"));
				vo.setbTitle(rs.getString("btitle"));
				vo.setbContent(rs.getString("bcontent"));
				vo.setbDate(rs.getDate("bdate"));
				vo.setbHit(rs.getInt("bhit"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
		
	}
	
		private void hitCount(int bid) {
			String sql ="UPDATE BOARD SET BHIT = BHIT +1 WHERE BID=?";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, bid);
			psmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			
		}
	}
	
	public int insert(BoardVo vo) {
		int n = 0;
		String sql = "INSERT INTO BOARD(BID,BNAME,BTITLE,BCONTENT,BDATE) VALUES(BIDSEQ.NEXTVAL,?,?,?,?)";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, vo.getbName());
			psmt.setString(2, vo.getbTitle());
			psmt.setString(3, vo.getbContent());
			psmt.setDate(4, vo.getbDate());
			
			n = psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}
		
	public int update (BoardVo vo) {
		int n = 0;
		String sql = "UPDATE BOARD SET BCONTENT = ? WHERE BID = ?";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, vo.getbContent());
			psmt.setInt(2, vo.getbId());
			
			n = psmt.executeUpdate();
		}catch (SQLException e) {
		e.printStackTrace();	
		}finally {
			close();
		}
		return n;
	}
	
	public int delete (BoardVo vo) {
		int n = 0;
		String sql = "DELETE FROM BOARD WHERE BID = ?";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, vo.getbId());
			
			n = psmt.executeUpdate();
			
		}catch(SQLException e) {
			
		}finally {
			close();
		}
		return n;
	}
	
	private void close() {
		try {
			if( rs != null)
			rs.close();
			if( psmt != null)
				psmt.close();
			if( conn != null)
				conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public ArrayList<ReplyVo> replySelect(ReplyVo vo){ //댓글 가져오기
		ArrayList<ReplyVo> replyList = new ArrayList<ReplyVo>();
		ReplyVo rvo;
		String sql = "select * from reply where bid= ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getBid());
			rs = psmt.executeQuery();
			while(rs.next()) {
				rvo = new ReplyVo();
				rvo.setBid(rs.getInt("bid"));
				rvo.setRnum(rs.getInt("rnum"));
				rvo.setSubject(rs.getString("subject"));
				rvo.setRdate(rs.getDate("rdate"));
				replyList.add(rvo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return replyList;
	}
}

     
