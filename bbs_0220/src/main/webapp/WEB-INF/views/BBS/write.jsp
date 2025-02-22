<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style type="text/css">
        #bbs table {
            width:850px;
            margin-left:10px;
            border:1px solid black;
            border-collapse:collapse;
            font-size:14px;

        }

        #bbs table caption {
            font-size:20px;
            font-weight:bold;
            margin-bottom:10px;
        }

        #bbs table th {
            text-align:center;
            border:1px solid black;
            padding:4px 10px;
        }

        #bbs table td {
            text-align:left;
            border:1px solid black;
            padding:4px 10px;
        }

        .no {width:15%}
        .subject {width:30%}
        .writer {width:20%}
        .reg {width:20%}
        .hit {width:15%}
        .title{background:lightsteelblue}

        .odd {background:silver}


    </style>
    <link rel="stylesheet" href="resources/css/summernote-lite.css"/>
<%--    <script type="text/javascript">--%>
<%--      function sendData(){--%>
<%--        for(var i=0 ; i<document.forms[0].elements.length ; i++){--%>
<%--          if(document.forms[0].elements[i].value == ""){--%>
<%--            alert(document.forms[0].elements[i].name+--%>
<%--                "를 입력하세요");--%>
<%--            document.forms[0].elements[i].focus();--%>
<%--            return;//수행 중단--%>
<%--          }--%>
<%--        }--%>

<%--//		document.forms[0].action = "test.jsp";--%>
<%--        document.forms[0].submit();--%>
<%--      }--%>
<%--    </script>--%>
</head>
<body>
<div id="bbs">
    <form action="write" method="post"
          encType="multipart/form-data">
        <table summary="게시판 글쓰기">
            <caption>게시판 글쓰기</caption>
            <tbody>
            <tr>
                <th>제목:</th>
                <td><input type="text" id="subject" name="subject" size="45"/></td>
            </tr>
            <tr>
                <th>이름:</th>
                <td><input type="text" id="writer" name="writer" size="12"/></td>
            </tr>
            <tr>
                <th>내용:</th>
                <td><textarea id="content" name="content" cols="50"
                              rows="8"></textarea></td>
            </tr>
            <tr>
                <th>첨부파일:</th>
                <td><input type="file" name="file"/></td>
            </tr>
                <tr>
                    <th>비밀번호:</th>
                    <td><input type="password" id="pwd" name="pwd" size="12"/></td>
                </tr>
            <tr>
                <td colspan="2">
                    <input type="button" value="보내기"
                           onclick="sendData()"/>
                    <input type="button" value="다시"/>
                    <input type="button" value="목록" onclick="javascript:location.href='list'"/>
                </td>
            </tr>
            </tbody>
        </table>
        <input type="hidden" name="bname" value="BBS"/>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="resources/js/summernote-lite.js"></script>
<script src="resources/js/lang/summernote-ko-KR.js"></script>
  <script>
    $(function() {
      $("#content").summernote({
        lang: 'ko-KR',
        width: 750,
        height: 300,
        maxHeight: 400,
        maxHeight: 200,

        callbacks: {
          onImageUpload: function(files, editor) {
            // 사용자가 이미지를 여러 개 넣을 수 있기 때문에 files라는
            // 배열로 인식된다.
            // 이것을 서버로 비동기식 통신을 수행하여
            // 서버에게 업로드 시킨다.
            for (let i=0; i<files.length; i++)
              saveImg(files[i], editor); // 서버로 이미지를 보내는 함수 호출
          }
        }
      });
      $("#content").summernote("lineHeight", 0.3);
    });

    function saveImg(file, editor) {
      //console.log("함수호출확인");

      // 서버로 이미지 파일을 보내기 위해 폼객체 준비
      let frm = new FormData(); // <form></form>

      // 보내고자 하는 자원을 폼에 파라미터 값으로 등록
      frm.append("upload", file); // 폼객체 안에 "upload"라는 이름으로 파일이 등록됨
      frm.append("bname", '${param.bname}');

      // 비동기식 통신
      $.ajax({
        url: "saveImg",
        data: frm, // 전달하고자 하는 파라미터들이 있는 폼객체
        type: "POST",
        contentType: false,
        processData: false, // 이것을 지정해야 일반적인 데이터 전송이 아님을 증명한다.(파일첨부시 반드시 해야함)
        dataType: "json",
      }).done(function(res){
        //console.log(res);
        if(res.url && res.fname) {
            let imageUrl = res.url;
            $("#content").summernote("editor.insertImage", imageUrl + "/" + res.fname);
        } else {
            alert("이미지 업로드 실패");
        }
      }).fail(function() {
        alert("서버 통신 오류");
      });
    }

    function sendData(){
      // 유효성 검사
      let title = $("#subject").val();
      let writer = $("#writer").val();
      let content = $("#content").val();
      let pwd = $("#pwd").val();

      if(title.trim().length < 1) {
        alert("제목을 입력하세요");
        $("#subject").val(""); //청소
        $("#subject").focus();
        return;
      }
      if(writer.trim().length < 1) {
        alert("글쓴이를 입력하세요");
        $("#writer").val(""); //청소
        $("#writer").focus();
        return;
      }
      if(content.trim().length < 1) {
        alert("내용을 입력하세요");
        $("#content").val(""); //청소
        $("#content").focus();
        return;
      }
      if(pwd.trim().length < 1) {
        alert("비밀번호를 입력하세요");
        $("#pwd").val(""); //청소
        $("#pwd").focus();
        return;
      }
      document.forms[0].submit();
    }

</script>
</body>
</html>
