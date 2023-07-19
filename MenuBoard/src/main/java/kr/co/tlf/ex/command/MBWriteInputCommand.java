package kr.co.tlf.ex.command;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MBWriteInputCommand implements MBCommand {
    String viewPage = "writeinput.jsp";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
    {}
    
    @Override
    public String getViewPage() {
        return viewPage;  // 필요하지 않음
    }
}
