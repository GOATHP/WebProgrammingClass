<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글 작성</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Hana&display=swap');

        body {
            font-family: 'Hana', sans-serif;
        }

        h1 {
            color: #FF69B4;
        }

        .form-group {
            margin-top: 20px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
        }

        .form-group input[type="text"],
        .form-group textarea {
            width: 100%;
            padding: 5px;
            border: 1px solid #ddd;
            resize: none;
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
    </style>
</head>
<body>
    <h1>글 작성</h1>
    <form action="write.do" method="POST">
        <div class="form-group">
            <label for="title">제목</label>
            <input type="text" id="title" name="title">
        </div>
        <div class="form-group">
            <label for="content">내용</label>
            <textarea id="content" name="content" rows="5"></textarea>
        </div>
        <div class="form-group">
            <label for="writer">작성자</label>
            <input type="text" id="writer" name="writer">
        </div>
        <div class="btn-group">
            <button type="submit">작성 완료</button>
            <button onclick="location.href='list.do'">취소</button>
        </div>
    </form>
</body>
</html>
