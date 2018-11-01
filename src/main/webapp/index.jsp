<%@ page language="java" pageEncoding="UTF-8" %>
2 <!DOCTYPE html>
3 <html>
4 <head>
    5     <title>Java后端WebSocket的Tomcat实现</title>
    6 </head>
7 <body>
8     Welcome<br/><input id="text" type="text"/>
9     <button onclick="send()">发送消息</button>
10     <hr/>
11     <button onclick="closeWebSocket()">关闭WebSocket连接</button>
12     <hr/>
13     <div id="message"></div>
14 </body>
15
16 <script type="text/javascript">
    17     var websocket = null;
    18     //判断当前浏览器是否支持WebSocket
    19     if ('WebSocket' in window) {
        20         websocket = new WebSocket("ws://localhost:8081/websocket");
        21     }
    22     else {
        23         alert('当前浏览器 Not support websocket')
        24     }
    25
    26     //连接发生错误的回调方法
    27     websocket.onerror = function () {
        28         setMessageInnerHTML("WebSocket连接发生错误");
        29     };
    30
    31     //连接成功建立的回调方法
    32     websocket.onopen = function () {
        33         setMessageInnerHTML("WebSocket连接成功");
        34     }
    35
    36     //接收到消息的回调方法
    37     websocket.onmessage = function (event) {
        38         setMessageInnerHTML(event.data);
        39     }
    40
    41     //连接关闭的回调方法
    42     websocket.onclose = function () {
        43         setMessageInnerHTML("WebSocket连接关闭");
        44     }
    45
    46     //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    47     window.onbeforeunload = function () {
        48         closeWebSocket();
        49     }
    50
    51     //将消息显示在网页上
    52     function setMessageInnerHTML(innerHTML) {
        53         document.getElementById('message').innerHTML += innerHTML + '<br/>';
        54     }
    55
    56     //关闭WebSocket连接
    57     function closeWebSocket() {
        58         websocket.close();
        59     }
    60
    61     //发送消息
    62     function send() {
        63         var message = document.getElementById('text').value;
        64         websocket.send(message);
        65     }
    66 </script>
67 </html>