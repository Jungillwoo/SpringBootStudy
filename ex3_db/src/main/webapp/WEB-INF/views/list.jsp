<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>사원 목록</title>
  <style>
    /* 전체 페이지 스타일 */
    body {
      font-family: 'Arial', sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      min-height: 100vh;
    }

    /* 컨테이너 */
    #wrap {
      width: 80%;
      max-width: 800px;
      min-height: 1000px;
      background: white;
      box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
      padding: 20px;
      border-radius: 8px;
    }

    /* 헤더 */
    header {
      text-align: center;
      padding: 10px 0;
      font-size: 24px;
      font-weight: bold;
      color: #333;
    }

    /* 테이블 스타일 */
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 10px;
      background-color: #fff;
    }

    table th {
      background-color: #007bff;
      color: white;
      padding: 12px;
      text-align: left;
    }

    table td {
      padding: 10px;
      border-bottom: 1px solid #ddd;
    }

    /* 홀수 행 색상 변경 */
    tbody tr:nth-child(odd) {
      background-color: #f9f9f9;
    }

    /* 마우스 오버 효과 */
    tbody tr:hover {
      background-color: #e3f2fd;
      cursor: pointer;
    }

    /* 검색 폼 */
    form {
      display: flex;
      justify-content: center;
      gap: 8px;
      align-items: center;
      margin-bottom: 15px;
    }

    select, input {
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }

    button {
      background-color: #007bff;
      color: white;
      border: none;
      padding: 8px 12px;
      cursor: pointer;
      border-radius: 4px;
    }

    button:hover {
      background-color: #0056b3;
    }
  </style>
</head>
<body>
<div id="wrap">
  <header>사원 목록</header>
  <article>
    <form action="empSearch" method="post">
      <select name="type">
        <option value="0">사번</option>
        <option value="1">이름</option>
        <option value="2">직종</option>
        <option value="3">부서번호</option>
      </select>
      <input type="text" name="value" placeholder="검색어 입력"/>
      <button type="button" onclick="exe(this.form)">검색</button>
    </form>

    <table>
      <thead>
      <tr>
        <th>사번</th>
        <th>이름</th>
        <th>직종</th>
        <th>부서번호</th>
        <th>입사일</th>
        <th>급여</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="vo" items="${ar}">
        <tr>
          <td>${vo.empno}</td>
          <td>${vo.ename}</td>
          <td>${vo.job}</td>
          <td>${vo.deptno}</td>
          <td>${vo.hiredate}</td>
          <td>${vo.sal}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </article>
</div>

<script>
  function exe(form) {
    if (form.value.value.trim() === "" && form.value.value.trim().length < 1) {
      alert("검색어를 입력하세요.");
      form.value.focus();
      return;
    }
    form.submit();
  }
</script>
</body>
</html>
