<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 2026-06-10
  Time: 오후 4:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>getParameterValues 실습</h1>
<form action="sports" method="post">
    <fieldset>
        <legend>좋아하는 운동 및 성별</legend>
        <div>
            야구 <input type="checkbox" name="sports" value="야구">
            축구 <input type="checkbox" name="sports" value="축구">
            농구 <input type="checkbox" name="sports" value="농구">
        </div>
        <div>
            남 <input type="radio" name="sex" value="남">
            여 <input type="radio" name="sex" value="여">
        </div>
        <input type="submit" value="저장 ">
    </fieldset>
</form>
</body>
</html>
