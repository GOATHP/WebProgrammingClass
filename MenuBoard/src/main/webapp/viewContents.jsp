<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글 내용</title>
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
    <h1>글 내용</h1>
    <table>
        <tr>
            <th>번호</th>
            <td>${post.id}</td>
        </tr>
        <tr>
            <th>조회수</th>
            <td>${post.count}</td>
        </tr>
        <tr>
            <th>작성자</th>
            <td>${post.writer}</td>
        </tr>
        <tr>
            <th>작성일</th>
            <td>${post.writeDate}</td>
        </tr>
        <tr>
            <th>제목</th>
            <td>${post.title}</td>
        </tr>
        <tr>
            <th>내용</th>
            <td>${post.content}</td>
        </tr>
    </table>
    <div class="btn-group">
        <button onclick="location.href='list.do'">목록 보기</button>
        <button onclick="location.href='edit.do?id=${post.id}'">수정하기</button>
        <button onclick="confirmDelete(${post.id})">삭제하기</button>
        <button onclick="location.href='replyForm.do?id=${post.id}'">답변 달기</button>
    </div>
    <script>
        function confirmDelete(postId) {
            if (confirm("정말로 삭제하시겠습니까?")) {
                // '예'를 선택한 경우
                window.location.href = `delete.do?id=${post.id}`;
            } else {
                // '아니오'를 선택한 경우
                // 아무 작업을 수행하지 않음
            }
        }
    </script>
</body>
</html>
