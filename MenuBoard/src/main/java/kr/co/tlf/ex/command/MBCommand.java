package kr.co.tlf.ex.command;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MBCommand {
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    
    String getViewPage();
}
