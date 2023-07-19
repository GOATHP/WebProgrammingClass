package kr.co.tlf.ex.command;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.dao.MBDao;

public class MBDeleteListCommand implements MBCommand {
    private String viewPage = "list.do";
            
    @Override

    public void execute(HttpServletRequest request, HttpServletResponse response) {
        MBDao dao = new MBDao();
        String[] ids = request.getParameterValues("ids");
        if (ids != null) {
            for (String id : ids) {
                String[] individualIds = id.split(",");
                for (String individualId : individualIds) {
                    dao.deletePost(Integer.parseInt(id));
                }
            }
        }
    }
 


    @Override
    public String getViewPage() {
        // TODO Auto-generated method stub
        return viewPage;
    }

}
