<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="DataObject.MemberDAO"%>
<%@ page import="DataObject.MemberDTO"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>회원 관리 시스템</title>
  <style>
    body {
      background-color: #c5e1d6;
      font-family: 'Hana', Arial, sans-serif;
      margin: 0;
      padding: 20px;
    }

    h1 {
      color: #004d40;
      margin-top: 0;
    }

    ul {
      list-style-type: none;
      margin: 0;
      padding: 0;
    }

    li {
      display: inline-block;
      margin-right: 10px;
    }

    a {
      color: #455a64;
      text-decoration: none;
    }

    .menu-bar {
      background-color: #e0f2f1;
      padding: 10px;
      border-radius: 5px;
      display: inline-block;
      margin-bottom: 10px;
      margin-top: 0;
      margin-left: auto;
      margin-right: auto;
      text-align: center;
    }

    .hidden {
      display: none;
    }
  </style>
  <link href="https://fonts.googleapis.com/css?family=Hana&display=swap" rel="stylesheet">
</head>
<body>
  <header>
    <div style="float: left;">
      <h1>회원 관리 시스템</h1>
    </div>
    <div style="float: right;">
      <nav class="menu-bar">
        <ul>
          <% 
          request.setCharacterEncoding("UTF-8");
          response.setCharacterEncoding("UTF-8");
          String loggedInId = (String) session.getAttribute("loggedInId");
          boolean loggedIn = (session.getAttribute("loggedIn") != null && (boolean) session.getAttribute("loggedIn"));
          boolean isAdmin = (session.getAttribute("isAdmin") != null && (boolean) session.getAttribute("isAdmin"));
          %>
          <% if (loggedIn) { %>
            <% if (isAdmin) { %>
              <li><a href="./admin/manageUser.jsp">회원관리</a></li>
              <li><a href="logout.jsp">로그아웃</a></li>
            <% } else { %>
              <li><a href="./user/showMyInfo.jsp">개인정보관리</a></li>
              <li><a href="./user/wirhdrawalRequest.jsp">회원탈퇴요청</a></li>
              <li><a href="logout.jsp">로그아웃</a></li>
            <% } %>
          <% } else { %>
            <li><a href="./join/join.html">회원가입</a></li>
            <li><a href="./login/login.html">로그인</a></li>
          <% } %>
        </ul>
      </nav>
    </div>
  </header>
</body>
</html>
