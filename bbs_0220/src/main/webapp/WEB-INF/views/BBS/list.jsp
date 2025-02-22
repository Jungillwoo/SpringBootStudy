<%@ page import="com.example.bbs_0220.vo.BbsVO" %>
<%@ page import="com.example.bbs_0220.util.Paging" %>
<%@ page import="com.example.bbs_0220.vo.CommVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
    
        #bbs {
            width: 800px;
            margin: 50px auto;
            background: white;
            padding: 20px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
    
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
    
        .no {width: 10%;}
        .subject {width: 40%; text-align: left;}
        .writer {width: 20%;}
        .reg {width: 20%;}
        .hit {width: 10%;}
    
        /* 페이징 스타일 */
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
<div id="bbs">
    <table summary="게시판 목록">
        <caption>게시판 목록</caption>
        <thead>
        <tr class="title">
            <th class="no">번호</th>
            <th class="subject">제목</th>
            <th class="writer">글쓴이</th>
            <th class="reg">날짜</th>
            <th class="hit">조회수</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
            <td colspan="4">
                <ol class="paging">

                    <c:if test="${requestScope.page ne null}">
                        <c:set var="pvo" value="${requestScope.page}"/>
                        <c:if test="${pvo.startPage < pvo.pagePerBlock}">
                            <li class="disable">&lt;</li>
                        </c:if>
                        <c:if test="${pvo.startPage >= pvo.pagePerBlock}">
                            <li><a href="list?bname=BBS&cPage=${pvo.startPage - pvo.pagePerBlock}">&lt;</a></li>
                        </c:if>
                        <c:forEach begin="${pvo.startPage}" end="${pvo.endPage}" varStatus="status">
                            <c:if test="${status.index eq pvo.nowPage}">
                                <li class="now">${status.index}</li>
                            </c:if>
                            <c:if test="${status.index ne pvo.nowPage}">
                                <li><a href="list?bname=BBS&cPage=${status.index}">${status.index}</a></li>
                            </c:if>
                        </c:forEach>
                        <c:if test="${pvo.endPage < pvo.totalPage}">
                            <li><a href="list?bname=BBS&cPage=${pvo.startPage+pvo.pagePerBlock}">&gt;</a></li>
                        </c:if>
                        <c:if test="${pvo.endPage >= pvo.totalPage}">
                            <li class="disable">&gt;</li>
                        </c:if>
                    </c:if>

                </ol>
            </td>
            <td>
                <input type="button" value="글쓰기"
                       onclick="javascript:location.href='write?bname=BBS&cPage=${nowPage}'"/>
            </td>
        </tr>
        </tfoot>
        <tbody>

        <c:forEach var="vo" items="${ar}" varStatus="vs">
            <tr>
                <td>${pvo.totalRecord-((pvo.nowPage-1)*pvo.numPerPage+vs.index)}</td>
                <td style="text-align: left">
                    <a href="${pageContext.request.contextPath}/view?bname=${bname}&b_idx=${vo.b_idx}&cPage=${pvo.nowPage}">
                        ${vo.subject}
                        <c:if test="${vo.c_list.size() > 0}">
                            (${vo.c_list.size()})
                        </c:if>
                    </a>
                </td>
                <td>${vo.writer}</td>
                <td>${vo.write_date}</td>
                <td>${vo.hit}</td>
            </tr>
        </c:forEach>
        <c:if test="${ar eq null or fn:length(ar) eq 0}">
            <tr>
                <td colspan="5">현재 등록된 데이터가 없습니다.</td>
            </tr>
        </c:if>

        </tbody>
    </table>

</div>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
    // $(function (){
    //
    //   let param = "type=get"
    //   $.ajax({
    //     url: "Controller",
    //     type: "POST",
    //     data: param,
    //     dataType: "html",
    //   }).done(function (res){
    //     $("table>tbody").html(res);
    //   });
    // });
</script>
</body>
</html>