package co.micol.board.common;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.board.web.BoardDelete;
import co.micol.board.web.BoardEditForm;
import co.micol.board.web.BoardForm;
import co.micol.board.web.BoardInsert;
import co.micol.board.web.BoardList;
import co.micol.board.web.BoardUpdate;
import co.micol.board.web.BoardView;
import co.micol.board.web.MainCommand;

@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>();
	
    
    public FrontController() {
        super();
       
    }

	
	public void init(ServletConfig config) throws ServletException {
		map.put("/main.do", new MainCommand());
		map.put("/boardList.do", new BoardList()); //글 목록
		map.put("/boardView.do", new BoardView()); //상세 보기
		map.put("/boardDelete.do", new BoardDelete()); //삭제 
		map.put("/boardForm.do", new BoardForm()); // 새글 등록할 수 있는 폼 호출
		map.put("/boardInsert.do", new BoardInsert()); // 새글 등록
		map.put("/boardEditForm.do", new BoardEditForm()); //글 수정 폼 호출
		map.put("/boardUpdate.do", new BoardUpdate());// 글 수정
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String contextPath = request.getContextPath();
		String uri = request.getRequestURI();
		String path = uri.substring(contextPath.length());
		
		Command command = map.get(path);
		
		String viewPage = command.exec(request, response);
		
		//jsp에 접근할 수 있도록 해주는 작업.. (뷰리절브라고 함)
		//뷰페이지에서 끝단어가 .jsp로 들어오면 뷰페이지 앞에 /WEB-INF/jsp/를 더해주라는 뜻 (접근할 수 있게)
		//if(viewPage.endsWith(".jsp")) viewPage = "/WEB-INF/jsp/" + viewPage;
		
		//이게 정석적인 리절브
		//뒤에가 .do로 끝나지 않으면 뷰페이지에 앞에 "/WEB-INF/jsp/" 붙여주고 뒤에 ".jsp" 붙여줌.
		if(!viewPage.endsWith(".do")) viewPage = "/WEB-INF/jsp/" + viewPage + ".jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}

}
