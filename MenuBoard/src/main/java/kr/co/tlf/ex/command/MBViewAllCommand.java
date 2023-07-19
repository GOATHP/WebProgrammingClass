package kr.co.tlf.ex.command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.dao.MBDao;
import kr.co.tlf.ex.dto.MBDto;

import java.util.ArrayList;
import java.util.List;

public class MBViewAllCommand implements MBCommand {
    private String viewPage = "list.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        MBDao dao = new MBDao();
        List<MBDto> dtos = dao.getViewAll();
        
        for (MBDto dto : dtos) {
            System.out.println(dto.toString()); // 혹은 dto의 필드를 개별적으로 출력할 수도 있습니다.
        }
        request.setAttribute("postList", dtos);
    }

    @Override
    public String getViewPage() {
        return viewPage;
    }
}
