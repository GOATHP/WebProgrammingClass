package kr.co.tlf.ex.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.dao.MBDao;
import kr.co.tlf.ex.dto.MBDto;

public class MBViewContentsCommand implements MBCommand {
    private String viewPage = "viewContents.jsp";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id")); // 클릭한 글의 ID 가져오기
        
        MBDao dao = MBDao.getInstance();
        MBDto dto = dao.getPostById(id); // ID에 해당하는 글 정보 가져오기
        
        if (dto != null) {
            // 게시물 조회수 증가
            dao.increaseCount(id);
            MBDto updateDto = dao.getPostById(id); // ID에 해당하는 글 정보 가져오기
            request.setAttribute("post", updateDto);
        } else {
            // 게시물이 없을 경우 처리
        }
    }

    @Override
    public String getViewPage() {
        return viewPage; // 글 내용을 보여줄 뷰 페이지 경로
    }
}
