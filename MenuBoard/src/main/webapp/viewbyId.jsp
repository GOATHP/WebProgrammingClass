<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글 상세보기</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Hana&display=swap');

        body {
            font-family: 'Hana', sans-serif;
        }

        h1 {
            color: #FF69B4;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #FFC0CB;
        }
    </style>
</head>
<body>
    <h1>글 상세보기</h1>
    <table>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회수</th>
            <th>내용</th>
        </tr>
        <c:forEach items="${viewById}" var="post">
            <tr>
                <td>${post.id}</td>
                <td>${post.title}</td>
                <td>${post.writer}</td>
                <td>${post.writeDate}</td>
                <td>${post.count}</td>
                <td>${post.content}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
