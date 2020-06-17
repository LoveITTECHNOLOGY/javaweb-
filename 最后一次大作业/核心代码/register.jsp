<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="author" content="order by dede58.com"/>
    <title>用户注册</title>
    <link rel="stylesheet" type="text/css" href="./css/login.css">
    <script src="js/jquery-3.3.1.min.js"></script>

</head>
<body>
<form  id="myForm" method="post" action="regedit.do">
    <!--隐藏域-->
    <input type="text" id="codeHidden" value="">
    <div class="regist">
        <div class="regist_center">
            <div class="regist_top">
                <div class="left fl">会员注册</div>
                <div class="right fr"><a href="./index.html" target="_self">小米商城</a></div>
                <div class="clear"></div>
                <div class="xian center"></div>
            </div>
            <div class="regist_main center">
                <div class="username">用&nbsp;&nbsp;户&nbsp;&nbsp;名:&nbsp;&nbsp;<input class="shurukuang"  id="username" type="text" name="username" required="required" placeholder="请输入你的用户名"/><span id="showname" >请不要输入汉字</span></div>
                <div class="username">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;&nbsp;<input class="shurukuang" id="password" type="password" name="password" required placeholder="请输入你的密码"/><span>请输入6位以上字符</span></div>

                <div class="username">确认密码:&nbsp;&nbsp;<input class="shurukuang" type="password" name="repassword" id="repassword" required placeholder="请确认你的密码"/><span>两次密码要输入一致哦</span></div>
                <div class="username">手&nbsp;&nbsp;机&nbsp;&nbsp;号:&nbsp;&nbsp;<input class="shurukuang" type="text" name="tel" id="tel" required placeholder="请填写正确的手机号"/><span>填写下手机号吧，方便我们联系您！</span></div>
                <div class="username">
                    <div style="float: left">验&nbsp;&nbsp;证&nbsp;&nbsp;码:&nbsp;&nbsp;<input class="yanzhengma" type="text" id="inCode1" name="inCode"  class="tel" required placeholder="请输入验证码" style="width:105px"/></div>
                    <div  id="telCode" style="float: left">获取验证码</div>
                </div>
            </div>
            <div class="regist_submit">
                <input class="submit" type="submit" name="submit" required value="立即注册" >
            </div>

        </div>
    </div>
</form>

</body>
<script>
    //jq代码
    //为指定元素绑定事件处理  bind('某个事件',函数)
    $('#telCode').bind('click',show);//让 获取验证码 框有效
    function show(){
        //判断手机号是否符合规则,是否为11位
        if($('#tel').val().length==11){
            var tel=$('#tel').val();// $('#tel').val()是用户输入的手机号码
            //用ha来接收ajax发送请求的返回值
            var ha=$.ajax({
                type: "post",
                url: "send.do",
                data: "tel="+tel,
                async:false
            }).responseText;
            //把验证码放在隐藏域中，然后让用户输入验证码，把两者进行比对
            $("#codeHidden").val(ha);

            //点击了之后，要过1分钟才能再点
            $("#telCode").unbind("click");//点击事件失效
            $("#telCode").addClass("disabled");//获取验证码框失效
            //倒计时
            var count=60;
            var timer=setInterval(function(){
                //定时器每秒钟做的事
                $("#telCode").html(count+"秒后重发");
                count--;
                if(count==0){
                    //一旦倒计时为0，则清除定时器
                    clearInterval(timer);
                    //让获取验证码有效
                    $("#telCode").html("获取验证码");
                    $("#telCode").removeClass("disabled");
                    $('#telCode').bind('click',show);
                }
            },1000);
        }else {
            //手机格式不对
            alert("请输入正确的手机号！");
        }
    }

    //验证用户名是否存在

    var a =false;//a为true则用户存在，用户名就不可用
    //blur()失去焦点事件
    $("#username").blur(function () {
        //失去焦点要做的事情
        //.val()得到文本框的值
        var name=$("#username").val();
        if (name==""){
            //让用户名后面的标签显示用户名不能为空
            //.text()只显示文本内容 .html()显示所有html内容
            //上面的方法，不放参数就是取值，放参数就是设置值
            $("#showname").html("用户名不能为空！")
        }else {
            //每次填新的用户名都让a=false
            a=false;
            var ha=$.ajax({
                type:"post",
                url:"test.do",
                data:"name="+name,
                async:false
            }).responseText;
            //通过判断ha的值 做下一步操作

            if (ha=="用户名已存在!"){
                a=true;

            }

            //将ajax得到的结果显示在用户名文本框后面
            $("#showname").html(ha);



        }
    });
    //表单验证
    $("#myForm").submit(function () {
        //使用正则验证手机号码
        var rege=/^1\d{10}$/;
        var tel=$("#tel").val();
        if(!rege.test(tel)){
            alert("手机号不符合规则！")
            return false;//submit为假，不提交
        }
        //用户名是否可用
        if (a){
            alert("用户名不可用！");
            return false;
        }
        //对比两次密码是否一致
        if ($("#password").val()!=$("#repassword").val()){
            alert("两次密码不一致！");
            return false;
        }

        //验证码是否为空
        if ($("#inCode1").val()==""){
            alert("请输入验证码！");
            return false;
        }
        //验证码是否正确。
        if($("#codeHidden").val()!=$("#inCode1").val()){
            alert("验证码错误！");
            return false;
        }else{
            //验证码一致
            return true;
        }
//
//
//
//
    });


</script>



</html>