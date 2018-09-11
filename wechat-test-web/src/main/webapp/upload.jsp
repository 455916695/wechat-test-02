<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Cache-Control" content="max-age=300" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${query} - 商品搜索 - 宜立方商城</title>
<script type="text/javascript" src="/js/jquery.js"></script>
</head>
<body>
        <form id="uploadFile" action="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=${accessToken}&type=${type}"  enctype="multipart/form-data" method="POST" >
                <input type="hidden" id="token" value="${accessToken}"/>
                <input type="hidden" id="type" value="${type}"/>
                selectimage: <input id="upload" type="file" name="myfile"/><br>
                <input id="button" type="button" value="upload"/>
                <%--<a id="button">--%>
                        <%--<span >提交</span>--%>
                <%--</a>--%>
        </form>


        <script type="text/javascript">
            $(function () {
                alert("页面加载事件触发了");
                $("#button").click(function () {
                    alert("点击事件触发了");
                    var token = $("#token").val();
                    var type =  $("#type").val();
                    alert(token +"==="+type);
                    var url  = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+token+"&type="+type;
                    alert(url);
                    var formData  = new FormData();
                    alert("创建 FormData");
                    var img_file = document.getElementById("upload");
                    alert(img_file);
                    var  fileObj = img_file.files[0];
                    alert(fileObj);
                      formData.append("classIcon",fileObj);
                    alert("ajax之前");
                    $.ajax({
                        url : url,
                        type: 'POST',
                        dataType : 'json',
                        data : formData,
                        async : false,
                        processData : false,   //告诉jquery不要去处理发送的收据
                        contentType : false,  //告诉jquery不要去设置Content-Type
                        success : function (data) {
                            alert("success");
                            alert(data);
                        },
                        error : function () {
                            alert();
                        }
                    });

                });
            });

        </script>
</body>
</div>
</html>