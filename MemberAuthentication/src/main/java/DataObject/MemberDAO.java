package DataObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import jakarta.servlet.http.HttpSession;


public class MemberDAO {
    private DataSource dataSource;
    public MemberDAO() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MemberDTO> memberSelect() {
        ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oraclellg");
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM memberr");

            while (rs.next()) {
                String name = rs.getString("name");
                String id = rs.getString("id");
                String pw = rs.getString("password");
                String contact = rs.getString("contact");
                String email = rs.getString("email");
                String status = rs.getString("status");
                String role = rs.getString("role");
                String withdraw = rs.getString("withdraw");
                MemberDTO dto = new MemberDTO(id, name, pw, contact, email, status, role, withdraw);
                dtos.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

        return dtos;
    }

    public ArrayList<MemberDTO> getWithdrawnMembers() {
        ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oraclellg");
            conn = dataSource.getConnection();
            String query = "SELECT * FROM memberr WHERE withdraw = 'T'";
            stmt = conn.prepareStatement(query);

            rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String id = rs.getString("id");
                String pw = rs.getString("password");
                String contact = rs.getString("contact");
                String email = rs.getString("email");
                String status = rs.getString("status");
                String role = rs.getString("role");
                String withdraw = rs.getString("withdraw");

                MemberDTO dto = new MemberDTO(id, name, pw, contact, email, status, role, withdraw);
                dtos.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

        return dtos;
    }

    public ArrayList<MemberDTO> getLoggedInMember(String loggedInId) {
        ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oraclellg");
            conn = dataSource.getConnection();
            String query = "SELECT * FROM memberr WHERE id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, loggedInId);

            rs = stmt.executeQuery();
            while (rs.next()) {
                
                String name = rs.getString("name");
                String id = rs.getString("id");
                String pw = rs.getString("password");
                String contact = rs.getString("contact");
                String email = rs.getString("email");
                String status = rs.getString("status");
                String role = rs.getString("role");
                String withdraw = rs.getString("withdraw");
                
                MemberDTO dto = new MemberDTO(id, name, pw, contact, email, status, role, withdraw);
                dtos.add(dto);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

        return dtos;
    }
    public boolean updateWithdraw(String memberId, String withdraw) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oraclellg");
            conn = dataSource.getConnection();

            // Update member data
            stmt = conn.prepareStatement(
                "UPDATE MEMBERR SET withdraw = ? WHERE id = ?");
            stmt.setString(1, withdraw);
            stmt.setString(2, memberId);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Member withdrawal status updated successfully.");
                return true;
            } else {
                System.out.println("Failed to update member withdrawal status.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
 // MemberDAO 클래스의 updateWithdraw 메서드 내에 다음 코드를 추가합니다.
    public boolean updateWithdrawStatus(String id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oraclellg");
            conn = dataSource.getConnection();

            // Update member status to '일시정지'
            stmt = conn.prepareStatement(
                "UPDATE MEMBERR SET status = '일시정지' WHERE id = ?");
            stmt.setString(1, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Member status updated to '일시정지' successfully.");
                return true;
            } else {
                System.out.println("Failed to update member status.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean updateMgr(String id, String name, String email, String status, String role) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oraclellg");
            conn = dataSource.getConnection();

            // Update member data
            stmt = conn.prepareStatement(
                "UPDATE MEMBERR SET name = NVL(?, name), email = NVL(?, email), status = ?, role = ? WHERE id = ?");
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, status);
            stmt.setString(4, role);
            stmt.setString(5, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Member information updated successfully.");
                return true;
            } else {
                System.out.println("Failed to update member information.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public boolean update(String id, String password, String name, String phoneNumber, String email) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oraclellg");
            conn = dataSource.getConnection();

            // Check if the new phone number is already in use
            boolean phoneNumberExists = false;
            PreparedStatement phoneStmt =
                    conn.prepareStatement("SELECT * FROM MEMBERR WHERE contact = ?");
            phoneStmt.setString(1, phoneNumber);
            ResultSet phoneRs = phoneStmt.executeQuery();
            if (phoneRs.next()) {
                String existingId = phoneRs.getString("id");
                if (!existingId.equals(id)) {
                    phoneNumberExists = true;
                }
            }
            phoneRs.close();
            phoneStmt.close();

            // Check if the new email is already in use
            boolean emailExists = false;
            PreparedStatement emailStmt =
                    conn.prepareStatement("SELECT * FROM MEMBERR WHERE email = ?");
            emailStmt.setString(1, email);
            ResultSet emailRs = emailStmt.executeQuery();
            if (emailRs.next()) {
                String existingId = emailRs.getString("id");
                if (!existingId.equals(id)) {
                    emailExists = true;
                }
            }
            emailRs.close();
            emailStmt.close();

            if (!phoneNumberExists && !emailExists) {
                // Update member data
                stmt = conn.prepareStatement(
                        "UPDATE MEMBERR SET password = ?, name = NVL(?, name), contact = ?, email = ? WHERE id = ?");
                stmt.setString(1, password);
                stmt.setString(2, name);
                stmt.setString(3, phoneNumber);
                stmt.setString(4, email);
                stmt.setString(5, id);

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Member information updated successfully.");
                    return true;
                } else {
                    System.out.println("Failed to update member information.");
                    return false;
                }
            } else {
                if (phoneNumberExists) {
                    System.out.println("Phone number already exists. Please enter a different phone number.");
                }
                if (emailExists) {
                    System.out.println("Email already exists. Please enter a different email.");
                }
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

public boolean insert(String id, String password, String name, String contact, String email) {
    Connection conn = null;
    PreparedStatement stmt = null;

    try {
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oraclellg");
        conn = dataSource.getConnection();
        
        // Check if the new id is already in use
        boolean idExists = false;
        PreparedStatement idStmt = conn.prepareStatement("SELECT * FROM MEMBERR WHERE id = ?");
        idStmt.setString(1, id);
        ResultSet idRs = idStmt.executeQuery();
        if (idRs.next()) {
            idExists = true;
        }
        idRs.close();
        idStmt.close();

        if (!idExists) {
            // Insert new member data
            stmt = conn.prepareStatement(
                "INSERT INTO MEMBERR (id, password, name, contact, email, status, role, withdraw) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, id);
            stmt.setString(2, password);
            stmt.setString(3, name);
            stmt.setString(4, contact);
            stmt.setString(5, email);
            stmt.setString(6, "승인 전") ;
            stmt.setString(7, "일반사용자");
            stmt.setString(8, "F");
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                stmt.close();
                return true;
            } else {
                stmt.close();
                return false;
            }
        } else {
            return false;
        }
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    } finally {
        try {
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    public void delete(String id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oraclellg");
            conn = dataSource.getConnection();

            // Delete member data
            stmt = conn.prepareStatement("DELETE FROM MEMBERR WHERE id = ?");
            stmt.setString(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Member deleted successfully.");
            } else {
                System.out.println("Failed to delete member. Member not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


