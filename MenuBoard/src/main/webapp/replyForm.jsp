<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>답변 작성</title>
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
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #FFC0CB;
            width: 150px;
        }

        .btn-group {
            margin-top: 20px;
        }

        .btn-group button {
            padding: 10px 20px;
            background-color: #FF69B4;
            color: white;
            border: none;
            cursor: pointer;
        }

        .btn-group button:first-child {
            margin-right: 10px;
        }

        .btn-group button:hover {
            background-color: #FF1493;
        }

        .content {
            margin-top: 20px;
        }

        .content input[type="text"],
        .content textarea {
            width: 100%;
            padding: 5px;
            border: 1px solid #ddd;
            resize: none;
        }
    </style>
</head>
<body>
    <h1>답변 작성</h1>
    <form action="reply.do" method="post"> <!-- 답변 작성 폼이 제출될 때 처리할 경로 -->
        <input type="hidden" name="parentId" value="${postId}"> <!-- 부모 게시물 ID를 hidden 필드로 전달 -->
        <input type="hidden" name="step" value="${step}">
        <!-- 답변 작성 폼의 필드들을 구성 -->
        <label for="title">제목:</label>
        <input type="text" name="title" id="title" value="${title}"><br>
        <label for="content">내용:</label><br>
        <textarea name="content" id="content" rows="5" cols="50">${content}</textarea><br>
        <label for="writer">작성자:</label>
        <input type="text" name="writer" id="writer"><br>
        <input type="submit" value="답변 작성">
    </form>
</body>
</html>
