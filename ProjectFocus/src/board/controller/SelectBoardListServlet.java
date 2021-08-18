package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.Hashtag;
import board.model.vo.Image;
import like.model.service.LikeService;
import like.model.vo.Likey;
import member.model.vo.Member;

/**
 * Servlet implementation class SelectBoardListServlet
 */
@WebServlet("/selectBoardList.bo")
public class SelectBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectBoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double x = Double.parseDouble(request.getSession().getAttribute("userX").toString());
		double y = Double.parseDouble(request.getSession().getAttribute("userY").toString());
		int mNo = ((Member)request.getSession().getAttribute("loginUser")).getmNo();
		
		ArrayList<Likey> lList= new LikeService().selectLikeList(mNo);
		BoardService service = new BoardService();
		ArrayList<Board> bList = service.selectBList(x, y);
		ArrayList<Image> iList = service.selectIList();
		ArrayList<Hashtag> hList = service.selectHList();
		
		if(bList != null && iList != null) {
			request.setAttribute("bList", bList);
			request.setAttribute("iList", iList);
			request.setAttribute("hList", hList);
			request.setAttribute("lList", lList);

			request.getRequestDispatcher("WEB-INF/views/board/4_board.jsp").forward(request, response);
			
		}else {
			request.setAttribute("msg", "게시글 조회에 실패하였습니다.");
			request.getRequestDispatcher("WEB-INF/views/board/4_board.jsp");
			
		}
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
