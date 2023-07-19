package kr.co.tlf.ex.frontcontroller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.command.MBCommand;
import kr.co.tlf.ex.command.MBDeleteCommand;
import kr.co.tlf.ex.command.MBDeleteListCommand;
import kr.co.tlf.ex.command.MBEditCommand;
import kr.co.tlf.ex.command.MBReplyCommand;
import kr.co.tlf.ex.command.MBReplyFormCommand;
import kr.co.tlf.ex.command.MBUpdateCommand;
import kr.co.tlf.ex.command.MBViewAllCommand;
import kr.co.tlf.ex.command.MBViewContentsCommand;
import kr.co.tlf.ex.command.MBWriteCommand;
import kr.co.tlf.ex.command.MBWriteInputCommand;
import kr.co.tlf.ex.command.ViewCommand;
import java.io.IOException;

/**
 * Servlet implementation class MBFrontController
 */
@WebServlet("*.do")
public class MBFrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MBFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        actionDo(request, response);



    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        actionDo(request, response);
    }

    private void actionDo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = uri.substring(contextPath.length());

        // Command 객체 생성
        MBCommand mbCommand = null;
        if (command.equals("/write.do")) {
            mbCommand = new MBWriteCommand();
        } else if (command.equals("/list.do")) {
            mbCommand = new MBViewAllCommand();
        } else if (command.equals("/viewById.do")) {
            mbCommand = new ViewCommand();
        } else if (command.equals("/viewContents.do")) {
            mbCommand = new MBViewContentsCommand();
        } else if (command.equals("/writeinput.do")) {
            mbCommand = new MBWriteInputCommand();
        } else if (command.equals("/write.do")) {
            mbCommand = new MBWriteCommand();
        } else if (command.equals("/edit.do")) {
            mbCommand = new MBEditCommand();
        } else if (command.equals("/update.do")) {
            mbCommand = new MBUpdateCommand();
        } else if (command.equals("/delete.do")) {
            mbCommand = new MBDeleteCommand();
        } else if (command.equals("/replyForm.do")) {
            mbCommand = new MBReplyFormCommand();
        } else if (command.equals("/reply.do")) {
            mbCommand = new MBReplyCommand();
        } else if (command.equals("/deleteList.do")) {
            mbCommand = new MBDeleteListCommand();
        } else {
            System.out.println("유효하지 않은 커맨드입니다.");
        }

        // Command 실행
        if (mbCommand != null) {
            mbCommand.execute(request, response);
            System.out.println("확인2-삭제");
            String viewPage = mbCommand.getViewPage();
            System.out.println("확인2-삭제");// 커맨드에서 지정한 뷰 페이지 경로 가져오기
            request.getRequestDispatcher(viewPage).forward(request, response); // 뷰 페이지로 포워딩
            System.out.println("확인3-삭제");
        } else {
            // 유효하지 않은 command 처리에 대한 예외 처리
            // 예: response.sendRedirect("error.jsp");
        }
    }
}


