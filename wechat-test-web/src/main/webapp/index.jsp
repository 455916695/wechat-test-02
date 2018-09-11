<%--
  Created by IntelliJ IDEA.
  User: dell3020mt-50
  Date: 2018/6/27
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>

  </head>

  <body>
      <%--<jsp:forward page="${pageContext.request.contextPath}/downLoad.action"  />--%>
    <%--13_TCNrFLWnKPjux1LEC0Q4g63tvEqoJlA1T413Jsv8wPf43JkYVfmByks61T6sIF4EWBClANSEHClbw6jfBXy-s3d-zHRdVW6Y6kdJR9dxuvhYGETVMtfbgJciQZ4RKOjAGAMFJ--%>
      <form  action="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=${accessToken}&type=${type}"  enctype="multipart/form-data" method="POST" >
        selectimage: <input id="upload" type="file" name="myfile"/><br>
        <input id="button" type="submit" value="upload"/>
      </form>
  </body>

</html>
