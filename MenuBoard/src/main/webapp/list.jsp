<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록</title>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Hana&display=swap');

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

tr:nth-child(even) {
	background-color: #FFE4E1;
}

a {
	color: #FF69B4;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

div.btn-group {
	margin-top: 20px;
}

div.btn-group button {
	padding: 10px 20px;
	background-color: #FF69B4;
	color: white;
	border: none;
	cursor: pointer;
}

div.btn-group button:hover {
	background-color: #FF1493;
}
</style>
<script>
function toggleCheckbox(source) {
    var checkboxes = document.getElementsByName('ids');
    for (var i = 0; i < checkboxes.length; i++) {
        checkboxes[i].checked = source.checked;
    }
}
</script>
</head>
<body>
    <h1>글 목록</h1>
    <form action="deleteList.do" method="post"> <!-- 추가: 삭제를 수행할 URL로 설정 -->
        <table>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>조회수</th>
                <th>선택</th> <!-- 추가: 체크박스 컬럼 -->
            </tr>
            <tbody>
                <c:forEach items="${postList}" var="post">
                    <tr>
                        <td>${post.id}</td>
                        <td>
                            <c:if test="${post.indent > 0}">
                                <c:forEach begin="1" end="${post.indent}">
                                    &nbsp;&#9658;
                                </c:forEach>
                            </c:if>
                            <a href="viewContents.do?id=${post.id}">${post.title}</a> <!-- 수정: 제목에 링크 추가 -->
                        </td>
                        <td>${post.writer}</td>
                        <td>${post.writeDate}</td>
                        <td>${post.count}</td>
                        <td><input type="checkbox" name="ids" value="${post.id}"></td> <!-- 추가: 체크박스 -->
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="btn-group">
            <input type="checkbox" onclick="toggleCheckbox(this);"> 전체 선택 <!-- 추가: 전체 선택 체크박스 -->
            <button type="submit">선택 글 삭제</button> <!-- 추가: 전체 삭제 버튼 -->
        </div>
    </form>
    <div class="btn-group">
        <button onclick="location.href='writeinput.do'">글 작성하기</button>
    </div>
</body>
</html>
