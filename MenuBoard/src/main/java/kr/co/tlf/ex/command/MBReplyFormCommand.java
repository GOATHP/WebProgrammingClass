package kr.co.tlf.ex.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MBReplyFormCommand implements MBCommand {
    String viewPage = "replyForm.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // 답변 작성 폼으로 이동하기 위해 필요한 데이터를 설정
        int postId = Integer.parseInt(request.getParameter("id"));
        /*
         * int groups = Integer.parseInt(request.getParameter("groups")); int step =
         * Integer.parseInt(request.getParameter("step")); int indent =
         * Integer.parseInt(request.getParameter("indent")); String title =
         * request.getParameter("title"); String content = request.getParameter("content");
         */
        // TODO: 글 정보 조회 등 필요한 로직을 구현

        // 글 정보를 request에 설정
        request.setAttribute("postId", postId);
//        /*
//         * request.setAttribute("groups", groups); request.setAttribute("step", step);
//         * request.setAttribute("indent", indent); request.setAttribute("content", content);
//         * request.setAttribute("title", title);
//         */
        // 답변 작성 폼으로 이동
    }

    @Override
    public String getViewPage() {
        return viewPage;
    }
}
