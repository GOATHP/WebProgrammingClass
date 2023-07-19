<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="DataObject.MemberDAO"%>
<%@ page import="DataObject.MemberDTO"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
    <title>Member List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }

        h1 {
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        table th,
        table td {
            padding: 10px;
            border: 1px solid #ccc;
        }

        button {
            width: 200px;
            padding: 8px;
            border: none;
            background-color: #555;
            color: #fff;
            cursor: pointer;
            border-radius: 4px;
            margin: 0 auto;
            display: block;
        }

        button:hover {
            background-color: #333;
        }

        .message {
            text-align: center;
        }
    </style>
</head>
<body>
<%
    request.setCharacterEncoding("EUC-KR");
    response.setCharacterEncoding("EUC-KR");
    String id = request.getParameter("id");
    String password = request.getParameter("password");
    String name = request.getParameter("name");
    String phone_number = request.getParameter("phone_number");
    String email = request.getParameter("email");
    MemberDAO memberDAO = new MemberDAO();

    boolean success = memberDAO.insert(id, password, name, phone_number, email);
    if (success) {
        System.out.println("가입 성공");
        // 회원 상태 확인
        ArrayList<MemberDTO> members = memberDAO.getLoggedInMember(id);
        String statusMessage;
        if (!members.isEmpty()) {
            MemberDTO member = members.get(0);

            if (member.getStatus().equals("정상")) {
                statusMessage = "회원 가입이 완료되었습니다.";
            } else {
                statusMessage = "회원 가입이 승인 대기중입니다.";
            }
        } else {
            statusMessage = "회원 정보를 가져오지 못했습니다.";
        }
        System.out.println(statusMessage);
        %>
        <h1><%= statusMessage %></h1>
        <%
    } else {
        System.out.println("가입 실패 또는 에러 발생");
        %>
        <p class="message">가입 실패 또는 에러 발생 - 이미 존재하는 ID 또는 전화번호 또는 이메일입니다. </p>
        <button onclick="location.href='../join/join.html'">회원가입 페이지로 이동</button>
        <%
    }
    %>
    <br>
    <button onclick="location.href='../login/login.html'">로그인 페이지로 이동</button>
</body>
</html>
