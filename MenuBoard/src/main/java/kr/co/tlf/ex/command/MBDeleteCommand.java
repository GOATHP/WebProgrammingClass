package kr.co.tlf.ex.command;

import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.dao.MBDao;

public class MBDeleteCommand implements MBCommand {
    String viewPage = null;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // id 파라미터 가져오기
        String idParam = request.getParameter("id");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);

            // TODO: id를 사용하여 데이터 삭제하는 로직을 구현하세요.
            // 삭제 작업을 수행하는 코드를 추가하세요.
            MBDao dao = MBDao.getInstance();
            // 예시: DAO 클래스를 사용하여 데이터 삭제
            dao.deletePost(id);
            viewPage = "list.do";
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
