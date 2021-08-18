package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.BReply;
import board.model.vo.Board;
import board.model.vo.Hashtag;
import board.model.vo.Image;
import like.model.service.LikeService;
import like.model.vo.Likey;
import member.model.vo.Member;
/**
 * Servlet implementation class SelectBoardServlet
 */
@WebServlet("/selectBoardDetail.bo")
public class SelectBoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectBoardDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		int mNo = ((Member)request.getSession().getAttribute("loginUser")).getmNo();
		
		BoardService service = new BoardService();
		Board board = service.selectBoardDetail(bNo);
		ArrayList<Image> image = service.selectImageDetail(bNo);
		Likey like= new LikeService().selectLike(bNo, mNo);
		ArrayList<BReply> rList= new BoardService().selectReplyList(bNo);
		ArrayList<Hashtag> hList = service.selectHList(bNo);
		
		if(board != null && image != null) {
			request.setAttribute("board", board);
			request.setAttribute("image", image);
			request.setAttribute("like", like);
			request.setAttribute("rList", rList);
			request.setAttribute("hList", hList);
			
			request.getRequestDispatcher("WEB-INF/views/board/4_boardDetail.jsp").forward(request, response);
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
