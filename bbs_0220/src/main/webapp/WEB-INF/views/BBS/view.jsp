<%@ page import="com.example.bbs_0220.vo.BbsVO" %>
<%@ page import="com.example.bbs_0220.vo.CommVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.14.1/themes/base/jquery-ui.css">
    <style type="text/css">
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
       }

        #bbs {
            width: 1100px;
            margin: 50px auto;
            background: white;
            padding: 20px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }

        /* 테이블 스타일 */
        #bbs table {
            width: 100%;
            border-collapse: collapse;
            font-size: 16px;
        }

        #bbs table caption {
            font-size: 22px;
            font-weight: bold;
            margin-bottom: 15px;
            color: #333;
        }

        #bbs table th, #bbs table td {
            text-align: center;
            border: 1px solid #ddd;
            padding: 10px;
        }

        #bbs table th {
            background: #007bff;
            color: white;
            font-weight: bold;
        }

        #bbs table tbody tr:nth-child(odd) {
            background: #f9f9f9;
        }

        #bbs table tbody tr:hover {
            background: #e2e6ea;
            transition: 0.3s;
        }

        .no { width: 10%; }
        .subject { width: 40%; text-align: left; }
        .writer { width: 20%; }
        .reg { width: 20%; }
        .hit { width: 10%; }

        /* 페이지네이션 스타일 */
        .paging {
            list-style: none;
            display: flex;
            justify-content: center;
            padding: 10px 0;
        }

        .paging li {
            margin: 0 5px;
        }

        .paging li a, .paging .now {
            display: block;
            padding: 8px 12px;
            border-radius: 5px;
            text-decoration: none;
            font-weight: bold;
            transition: 0.3s;
        }

        .paging li a {
            background: white;
            border: 1px solid #007bff;
            color: #007bff;
        }

        .paging li a:hover {
            background: #007bff;
            color: white;
        }

        .paging .now {
            background: #ff4aa5;
            color: white;
            border: 1px solid #ff4aa5;
        }

        /* 비활성화된 버튼 */
        .disable {
            color: #ccc;
            border: 1px solid #ccc;
            padding: 8px 12px;
            border-radius: 5px;
        }

        /* 글쓰기 버튼 */
        .write-btn {
            display: block;
            width: 100px;
            margin: 20px auto;
            padding: 10px;
            background: #28a745;
            color: white;
            font-size: 16px;
            text-align: center;
            font-weight: bold;
            border-radius: 5px;
            text-decoration: none;
            transition: 0.3s;
        }

        .write-btn:hover {
            background: #218838;
        }

    </style>

</head>
<body>
<c:if test="${requestScope.vo ne null}">
    <div id="bbs">
        <form method="post">
            <table summary="게시판 글쓰기">
                <caption>게시판 글쓰기</caption>
                <tbody>
                <tr>
                    <th>제목:</th>
                    <td>${vo.subject}</td>
                </tr>
                <c:if test="${vo.file_name ne null and vo.file_name.length() > 4}">
                    <tr>
                        <th>첨부파일:</th>
                        <td>
                            <a href="javascript:down('${vo.file_name}')">
                                ${vo.ori_name}
                            </a>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <th>이름:</th>
                    <td>${vo.writer}</td>
                </tr>
                <tr>
                    <th>내용:</th>
                    <td>${vo.content}</td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="button" value="수정" onclick="goEdit()"/>
                        <input type="button" value="삭제" onclick="goDel()"/>
                        <input type="button" value="목록" onclick="goList()"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <form method="post" action="comm">
            이름:<input type="text" name="writer"/><br/>
            내용:<textarea rows="4" cols="55" name="content"></textarea><br/>
            비밀번호:<input type="password" name="pwd"/><br/>


            <input type="hidden" name="b_idx" value="${vo.b_idx}">
            <input type="hidden" name="cPage" value="${cPage}"/>
            <input type="submit" value="저장하기"/>
        </form>

        <form name="frm" method="post">
            <input type="hidden" name="type"/>
            <input type="hidden" name="f_name"/>
            <input type="hidden" name="b_idx" value="${vo.b_idx}"/>
            <input type="hidden" name="cPage" value="${cPage}"/>
            <input type="hidden" name="bname" value="${bname}"/>
        </form>

            <%-- 삭제시 보여주는 팝업창 --%>
        <div id="del_dialog" title="삭제" class="hide">
            <form action="" method="post">
                <label for="pwd">비밀번호:</label>
                <input type="password" id="pwd" name="pwd" size="10"/>
                <br/><br/>

                <input type="hidden" name="type" value="del"/>
                <input type="hidden" name="cPage" value="${cPage}"/>
                <input type="hidden" name="b_idx" value="${vo.b_idx}"/>
                <input type="button" value="삭제" onclick="delBbs(this.form)"/>
            </form>
        </div>
            <%-- ----------------- --%>

        댓글들<hr/>

        <c:forEach var="cvo" items="${vo.c_list}">
            <div>
                이름:${cvo.writer}
                날짜:${cvo.write_date}<br/>
                내용:${cvo.content}
            </div>
        </c:forEach>
    </div>
</c:if>
<c:if test="${requestScope.vo eq null}">
    <c:redirect url="">
        <c:param name="type" value="list"/>
        <c:param name="cPage" value="1"/>
    </c:redirect>
</c:if>

</body>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.14.1/jquery-ui.js"></script>
<script>
    function goList(){
      // 현재 문서안에 이름이 frm인 폼객체의 action을 Controller로 지정한다.
      document.frm.action="list";
      document.frm.submit();
    }
    function goEdit(){
      // 현재 문서안에 이름이 frm인 폼객체의 action을 Controller로 지정한다.
      document.frm.action="";
      document.frm.submit();
    }
    function goDel(){
      /*
      if (confirm("정말 삭제하시겠습니까?")) {
        document.frm.action = "Controller";
        document.frm.type.value = "del";
        document.frm.submit();
      }
       */
      $( "#del_dialog" ).dialog(); // 대화 팝업창 보여주기
    }
    function delBbs(frm){
      let pwd = $("#pwd").val().trim();
      if (pwd.length < 1){
        alert("비밀번호를 입력하세요.");
        $("#pwd").val('');
        $("#pwd").focus();
        return;
      }
      frm.submit(); // 결국 Controller를 거쳐서 DelAction으로 간다.
    }
    function down(fname){
      document.frm.action="download";
      document.frm.f_name.value=fname;
      document.frm.submit();
    }
</script>
</html>

<%--app.react--%>
<%--<html>--%>
<%--<import: board.react></import:>--%>

<%--<body>--%>
<%--    <board prop="recent"/>--%>
<%--    <board prop="forward"/>--%>
<%--</body>--%>
<%--</html>--%>

<%--board.react--%>
<%--<div>--%>
<%--    asdf--%>
<%--    for() {--%>
<%--     <div>상영리스트</div>--%>
<%--    }--%>
<%--</div>--%>
<%--export data--%>
<%--data:{--%>
<%--    list:{}--%>
<%--}--%>
<%--method:{--%>
<%--    getList(prop) {--%>
<%--        if(prop == "forward"){--%>
<%--            $.ajax({"개봉예정작 가져올 주소"})--%>
<%--        }--%>
<%--    }--%>
<%--}--%>