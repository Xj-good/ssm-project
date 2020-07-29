<%--
  Created by IntelliJ IDEA.
  User: 86131
  Date: 2020/7/22
  Time: 0:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript">
        $(function () {
            <%--alert("http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/")--%>
            $("#btn4").click(function () {
                var student = {
                    "stuId":5,
                    "stuName":"tom",
                    "address":{
                        "province":"广东",
                        "city":"深圳",
                        "street":"后瑞",
                    },
                    "subjectList":[{
                        "subjectName":"JavaSE",
                        "subjectScore":100
                    },{
                        "subjectName":"SSM",
                        "subjectScore":99
                    }],
                    "map":{
                        "k1":"v1",
                        "k2":"v2"
                    }
                }
                var requestBody = JSON.stringify(student);
                $.ajax({
                    "url":"send/compose/object.json",
                    "type":"post",
                    "data":requestBody,
                    "contentType": "application/json;charset=UTF-8",
                    "dataType":"json",
                    "success":function (response) {
                        console.log(response);
                    },
                    "error":function (response) {
                        console.log(response);
                    }
                });
            });

            $("#btn3").click(function () {
                var array = [5,8,12 ];
                console.log(array.length);
                var requestBody = JSON.stringify(array);
                console.log(requestBody.length);

                $.ajax({
                    "url":"send/array/three.html",
                    "type":"post",
                    "data":requestBody,
                    "contentType": "application/json;charset=UTF-8",
                    "dataType":"text",
                    "success":function (response) {
                        alert(response);
                    },
                    "error":function (response) {
                        alert(response);
                    }
                });
            });
            $("#btn2").click(function () {
                $.ajax({
                    "url":"send/array/two.html",
                    "type":"post",
                    "data":{
                        "array[0]":5,
                        "array[1]":8,
                        "array[2]":12
                    },
                    "dataType":"text",
                    "success":function (response) {
                        alert(response);
                    },
                    "error":function (response) {
                        alert(response);
                    }
                });
            });
            $("#btn1").click(function () {
                $.ajax({
                    "url":"send/array/one.html",
                    "type":"post",
                    "data":{
                        "array":[5,8,12]
                    },
                    "dataType":"text",
                    "success":function (response) {
                        alert(response);
                    },
                    "error":function (response) {
                        alert(response);
                    }
                });
            });
            $("#btn5").click(function () {
                layer.msg("aaaaaaaaaaa");
            });
        });
    </script>
</head>
<body>
    <a href="test/ssm.html">测试ssm整合的环境</a>

    <br/>
    <br/>

    <%--<a href="/test/ssm.html">测试ssm整合的环境</a>--%>
    <button id="btn1">Send [5,8,12] One</button>

    <br/>
    <br/>

    <%--<a href="/test/ssm.html">测试ssm整合的环境</a>--%>
    <button id="btn2">Send [5,8,12] Two</button>

    <br/>
    <br/>

    <%--<a href="/test/ssm.html">测试ssm整合的环境</a>--%>
    <button id="btn3">Send [5,8,12] Three</button>

    <br/>
    <br/>

    <%--<a href="/test/ssm.html">测试ssm整合的环境</a>--%>
    <button id="btn4">Send Compose Object</button>

    <br/>
    <br/>

    <button id="btn5">点我弹框</button>
</body>
</html>
