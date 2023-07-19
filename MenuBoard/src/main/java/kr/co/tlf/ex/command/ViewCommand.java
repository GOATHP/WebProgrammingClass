package kr.co.tlf.ex.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.dao.MBDao;
import kr.co.tlf.ex.dto.MBDto;

public class ViewCommand implements MBCommand {    
    String viewPage = "viewById.jsp";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter("id");
        int id = Integer.parseInt(idParam);
        MBDao dao = new MBDao();
        MBDto dto = dao.getPostById(id);
        System.out.println("아이디로 구분 확인2");
        request.setAttribute("viewById", dto);
        
    }

    @Override
    public String getViewPage() {
        // TODO Auto-generated method stub
        return viewPage;
    }

}
