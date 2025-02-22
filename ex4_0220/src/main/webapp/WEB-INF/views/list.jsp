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

    <table id="empTable">
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
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script>
  function exe(form) {

    let v = form.value.value;
    let type = form.type.value;

    let inputValue = form.value.value.trim();
    if (inputValue === "" || inputValue.length < 1) {
      alert("검색어를 입력하세요.");
      form.value.focus();
      return false;
    }
    // form.submit();
    // 비동기 통신을 위한 준비
    $.ajax({
      url: "empSearch",
      type: "POST",
      //data: "type=" + encodeURIComponent(type) + "&value=" + encodeURIComponent(v),
      data: {
        "type": type,
        "value": v
      },
      dataType: "json"
    }).done(function (res) {
      // 서버로부터 전달된 내용이 도착하는 곳
      console.log(res);
      let tbody = $("#empTable tbody");

      // tbody 초기화
      tbody.empty();

      for (let i=0; i < res.length; i++) {
       // tr 생성
        let tr = document.createElement("tr");
        let td1 = document.createElement("td");
        let td2 = document.createElement("td");
        let td3 = document.createElement("td");
        let td4 = document.createElement("td");
        let td5 = document.createElement("td");
        let td6 = document.createElement("td");

        td1.innerText = res.ar[i].empno; // 사번
        td2.innerText = res.ar[i].ename; // 이름
        td3.innerText = res.ar[i].job; // 직종
        td4.innerText = res.ar[i].deptno; // 부서번호
        td5.innerText = res.ar[i].hiredate; // 입사일
        td6.innerText = res.ar[i].sal; // 급여

        // td1 ~ td6 을 tr 에 자식요소로 추가
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);

        // tr 을 tbody 에 자식요소로 추가
        tbody.append(tr);
      }
    });
  }
</script>

</body>
</html>
