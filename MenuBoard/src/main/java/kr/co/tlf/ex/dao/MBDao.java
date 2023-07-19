package kr.co.tlf.ex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import jakarta.servlet.http.HttpSession;
import kr.co.tlf.ex.dto.MBDto;


public class MBDao {
    private static MBDao instance;
    
    public MBDao() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws Exception {
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
        return dataSource.getConnection();
    }
    
    

    private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static MBDao getInstance() {
        if (instance == null) {
            instance = new MBDao();
        }
        return instance;
    }

    private List<MBDto> executeQueryAndCreateDTO(String query, PreparedStatement stmt) {
        List<MBDto> dtos = new ArrayList<>();

        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String writer = rs.getString("writer");
                Date writeDate = rs.getDate("write_date");
                int count = rs.getInt("count");
                int groups = rs.getInt("groups");
                int step = rs.getInt("step");
                int indent = rs.getInt("indent");

                MBDto dto = new MBDto(id, title, content, writer, writeDate, count, groups, step, indent);
                dtos.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dtos;
    }

    public List<MBDto> getViewAll() {
        List<MBDto> dtos = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT * FROM board START WITH groups = 0 CONNECT BY PRIOR id = groups ORDER SIBLINGS BY step ASC");
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String writer = rs.getString("writer");
                Date writeDate = rs.getDate("write_date");
                int count = rs.getInt("count");
                int groups = rs.getInt("groups");
                int step = rs.getInt("step");
                int indent = rs.getInt("indent");

                MBDto dto = new MBDto(id, title, content, writer, writeDate, count, groups, step, indent);
                dtos.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }

        return dtos;
    }
    public void increaseCount(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("UPDATE board SET count = count + 1 WHERE id = ?");
            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Count increased successfully.");
            } else {
                System.out.println("Failed to increase count.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    public MBDto getPostById(int id) {
        MBDto dto = null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT * FROM board WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String title = rs.getString("title");
                String content = rs.getString("content");
                String writer = rs.getString("writer");
                Date writeDate = rs.getDate("write_date");
                int count = rs.getInt("count");
                int groups = rs.getInt("groups");
                int step = rs.getInt("step");
                int indent = rs.getInt("indent");

                dto = new MBDto(id, title, content, writer, writeDate, count, groups, step, indent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }

        return dto;
    }

    public void createReply(MBDto replyDto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String insertQuery = "INSERT INTO board (title, content, writer, groups, step, indent) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(insertQuery, new String[] { "ID" });

            stmt.setString(1, replyDto.getTitle());
            stmt.setString(2, replyDto.getContent());
            stmt.setString(3, replyDto.getWriter());
            stmt.setInt(4, replyDto.getGroups());
            stmt.setInt(5, replyDto.getStep());
            stmt.setInt(6, replyDto.getIndent());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                replyDto.setId(id);
            }
            
            System.out.println("----------------------------------------");
            System.out.println("답변 작성 성공");
            System.out.println(replyDto.getId() + replyDto.getGroups());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    public void createPost(MBDto dto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String insertQuery = "INSERT INTO board (title, content, writer, groups) " +
            "VALUES (?, ?, ?, ?)";
//            stmt = conn.prepareStatement("INSERT INTO board (title, content, writer) " +
//                    "VALUES (?, ?, ?)");
            stmt = conn.prepareStatement(insertQuery, new String[] { "ID" });

            stmt.setString(1, dto.getTitle());
            stmt.setString(2, dto.getContent());
            stmt.setString(3, dto.getWriter());
            stmt.setInt(4, dto.getGroups());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            /*
             * if (rs.next()) { int id = rs.getInt(1);
             * 
             * dto.setId(id); }
             */
             /*
              * String updateQuery = "update board set groups = ? where id = ?"; stmt =
              * conn.prepareStatement(updateQuery); stmt.setInt(1, dto.getId()); stmt.setInt(2,
              * dto.getId()); stmt.executeUpdate();
              */
            System.out.println("----------------------------------------");
            System.out.println("글 게시 성공");
            System.out.println(dto.getId() + dto.getGroups());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    public boolean updatePost(MBDto dto) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("UPDATE board SET title = ?, content = ?, writer = ?, write_date = ?, count = ?, groups = ?, step = ?, indent = ? WHERE id = ?");
            stmt.setString(1, dto.getTitle());
            stmt.setString(2, dto.getContent());
            stmt.setString(3, dto.getWriter());
            stmt.setDate(4, new java.sql.Date(dto.getWriteDate().getTime()));
            stmt.setInt(5, dto.getCount());
            stmt.setInt(6, dto.getGroups());
            stmt.setInt(7, dto.getStep());
            stmt.setInt(8, dto.getIndent());
            stmt.setInt(9, dto.getId());

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    public void deletePost(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("DELETE FROM board WHERE id = ?");
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Post deleted successfully.");
            } else {
                System.out.println("Failed to delete post. Post not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
    }
}