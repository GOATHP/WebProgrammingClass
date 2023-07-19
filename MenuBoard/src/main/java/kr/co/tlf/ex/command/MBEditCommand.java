package kr.co.tlf.ex.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.dao.MBDao;
import kr.co.tlf.ex.dto.MBDto;

public class MBEditCommand implements MBCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // 수정할 게시물의 ID를 파라미터에서 가져옴
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        // 게시물 정보를 DB에서 가져옴
        MBDao dao = MBDao.getInstance();
        MBDto dto = dao.getPostById(id);

        // 게시물 정보를 JSP에서 사용할 수 있도록 request에 속성으로 설정
        request.setAttribute("post", dto);
    }

    @Override
    public String getViewPage() {
        return "edit.jsp";
    }
}
