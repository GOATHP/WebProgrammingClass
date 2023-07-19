package kr.co.tlf.ex.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.tlf.ex.dao.MBDao;
import kr.co.tlf.ex.dto.MBDto;

public class MBReplyCommand implements MBCommand {
    private String viewPage = "list.do";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // 답변 작성 폼에서 전달된 데이터 가져오기
        System.out.println("asdad");
        int parentId = Integer.parseInt(request.getParameter("parentId"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String writer = request.getParameter("writer");
       
        MBDao dao = MBDao.getInstance();
        System.out.println("ydd");
        MBDto dto = dao.getPostById(parentId);
        
        // TODO: 답변 작성 로직 구현
        // 답변 작성에 필요한 데이터를 사용하여 답변 게시물을 생성하고 DB에 저장하는 코드를 추가하세요.
        dto.setGroups(parentId);
        dto.setTitle(title);
        dto.setContent(content);
        dto.setWriter(writer);
        dto.setStep(dto.getStep()+1);
        dto.setIndent(dto.getIndent()+1);
        // 예시: DAO 클래스를 사용하여 답변 작성 로직 구현
        dao.createReply(dto);
    }

    @Override
    public String getViewPage() {
        return viewPage; // 답변 작성 완료 페이지로 이동
    }
}