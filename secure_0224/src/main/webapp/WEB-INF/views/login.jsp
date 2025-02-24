<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#t1{
		border-collapse: collapse;
		width: 400px;
	}
	#t1 caption{
		text-indent: -9999px;
		height: 0;
	}
	#t1 th, #t1 td{
		border: 1px solid black;
		padding: 4px;
	}
	#t1 thead tr{
		background-color: #cdcdcd;
	}
	
	.modal_bg {
		
		display: none;
		width: 100%;
		height: 100%;
		position: fixed; 
		top: 0;
		left: 0;
		right: 0;
		background: rgba(0, 0, 0, 0.6);
		z-index: 999; 

	}
	
	.modal_wrap {
		
		display: none;
		position: absolute; 
		top: 50%;
		left: 50%;
		transform:translate(-50%,-50%);
		width: 400px;
		height: 400px;
		background: #fff;
		z-index: 1000; 

	}
	.modal_btn{
		text-align: center; 
		padding-top: 20px;
		position: relative;
		bottom: 0px;
	}
	
	.table{
		border-collapse: collapse;
		width: 380px;
		margin: auto;
	}
	.table th, .table td{
		border: 1px solid black;
		padding: 4px;
	}
	.table thead tr{
		background-color: #cdcdcd;
	}
</style>
</head>
<body>
	<h1>로그인페이지</h1>
	<hr/>
	<div id="box">
        <form action="/reqLogin" method="post">
            <table summery="로그인테이블">
                <caption>로그인테이블</caption>
                <tbody>
                    <tr>
                        <th>아이디</th>
                        <td>
                            <input type="text" id="m_id"
                                name="m_id"/>
                        </td>
                    </tr>
                    <tr>
                        <th>비밀번호</th>
                        <td>
                            <input type="password" id="m_pw"
                                name="m_pw"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="button" id="btn"
                                onclick="sendData(this.form)"
                                value="로그인"/>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
    </div>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script>
        function sendData(ff){
            let mid = $("#m_id").val();
            let mpw = $("#m_pw").val();

            if(mid.trim().length < 1){ //아이디 유효성 검사
                alert("아이디를 입력하세요");
                $("#m_id").focus();
                return;
            }

            if(mpw.trim().length < 1){ //비밀번호 유효성 검사
                alert("비밀번호를 입력하세요");
                $("#m_pw").focus();
                return;
            }

            ff.submit();
        }
    </script>
</body>
</html>
