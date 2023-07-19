package kr.co.tlf.ex.command;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.dao.MBDao;
import kr.co.tlf.ex.dto.MBDto;

public class MBUpdateCommand implements MBCommand {
    private String viewPage = null;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 수정할 게시물의 정보를 파라미터에서 가져옴
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        
        // 수정할 게시물 객체 생성
        MBDao dao = MBDao.getInstance();
        MBDto dto = dao.getPostById(id);
        
        dto.setCount(dto.getCount()-1);
        dto.setId(id);
        dto.setTitle(title);
        dto.setContent(content);
        // 게시물 수정
        boolean success = dao.updatePost(dto);
        if (success) {
            // 수정 성공 시, 수정된 게시물의 상세 화면으로 이동
            viewPage = "viewContents.do?id=" + id;
        } else {
            // 수정 실패 시, 에러 페이지로 이동하거나 메시지를 표시할 수 있음
            viewPage = "error.jsp";
        }
    }

    @Override
    public String getViewPage() {
        if (viewPage == null) {
            viewPage = "error.jsp";
        }
        return viewPage;
    }
}
