package kr.co.tlf.ex.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.dao.MBDao;
import kr.co.tlf.ex.dto.MBDto;

public class MBWriteCommand implements MBCommand {
    String viewPage = null;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String writer = request.getParameter("writer");
        System.out.println(title);
        MBDto dto = new MBDto();
        MBDao dao = MBDao.getInstance();        
        dto.setTitle(title);
        dto.setContent(content);
        dto.setWriter(writer);
        dto.setGroups(0);
        dao.createPost(dto);

            // 글 작성 성공 시, 필요한 후속 작업 수행
            // 예: 상세 화면으로 이동, 목록 업데이트 등
            viewPage = "list.do";
/*        } else {
            // 글 작성 실패 시, 에러 처리
            viewPage = "error.jsp";
        }
        
        request.setAttribute("message", "글이 성공적으로 작성되었습니다.");
*/    }
    @Override
    public String getViewPage() {
        if (viewPage == null) {
            viewPage = "error.jsp";
        }
        return viewPage;
    }
}

