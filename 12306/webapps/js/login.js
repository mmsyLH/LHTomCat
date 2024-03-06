// 切换登录、注册
function switchTab(tab) {
    var loginTab = document.getElementById("loginTab");
    var registerTab = document.getElementById("registerTab");
    var loginForm = document.getElementById("loginForm");
    var registerForm = document.getElementById("registerForm");
    if (tab === 'login') {
        loginTab.classList.add("active");
        registerTab.classList.remove("active");
        loginForm.style.display = "block";
        registerForm.style.display = "none";
    } else if (tab === 'register') {
        registerTab.classList.add("active");
        loginTab.classList.remove("active");
        registerForm.style.display = "block";
        loginForm.style.display = "none";
    }
}
// 提交登录 、注册
function submitForm(type) {
    console.log("拿到了账户" + document.getElementById("Username").value)
    var loginForm = document.getElementById("loginForm");
    var registerForm = document.getElementById("registerForm");
    if (type === 'login') {//登录
        var username = loginForm.querySelector("#Username").value;
        var password = loginForm.querySelector("#Password").value;
        var loginUnameError = document.getElementById("loginUname-error");
        var loginPwdError = document.getElementById("loginPwd-error");

        console.log("login", { Username: username, Password: password });
        // 检查用户名和密码是否为空
        if (!checkInputs(username, loginUnameError, 1) || !checkInputs(password, loginPwdError, 1)) {
            // 如果有任何一个输入不符合条件，则停止执行后续操作
            return;
        } else {
            if (username.length < 5) {
                alert("用户名长度不能少于 " + 5 + " 个字符");
                return;
            }
            if (password.length < 5) {
                alert("密码长度不能少于 " + 5 + " 个字符");
                return;
            }
        }
        ajaxLogin(username, password);
    } else if (type === 'register') {
        var email = registerForm.querySelector("#email").value;
        var username = registerForm.querySelector("#Username").value;
        var password = registerForm.querySelector("#Password").value;
        var repeatPassword = registerForm.querySelector("#Passwordrepeat").value;
        console.log("register", { email: email, Username: username, Password: password, Passwordrepeat: repeatPassword });
        if (password != repeatPassword) { alert("密码不一致") }
    }
}
// 检查用户名和密码是否为空
function checkInputs(input, errorElement, minLength) {
    if (input === '') {
        errorElement.style.display = "block";
        return false;
    } else {
        errorElement.style.display = "none";
    }
    return true;
}
// ajax登录
function ajaxLogin(username, password) {
    console.log("username", username);
    console.log("password", password);
    $.ajax({
        url: 'LhTomCat/userServlet',
        type: 'get',
        data: {
            action: "login",
            username: username,
            password: password
        },
        dataType:"JSON",
        success: function (res) {
            console.log("返回的res", res);
            //转对象
            // var jsonObject = $.parseJSON(res)
            if (res.code === 200) {
                sessionStorage.setItem("userName", username);
                alert(res.msg+"<br>3秒后进行跳转")
                setTimeout(function name(params) {
                    // location.href = "index.html";
                },3000);
            }
        },
        error: function (err) {
            // 只有请求不正常（状态码不为200）才会执行
            console.log('error', err);
        },

    });
}
//让线程睡眠
function delay(duration) {
    var start = Date.now();
    while (Date.now() - start < duration) {

    }
}
// 封装自定义弹窗
window.alert = function (str) {
    var shield = document.createElement("div");
    shield.id = "shield";
    shield.style.position = "fixed";
    shield.style.left = "0";
    shield.style.top = "0";
    shield.style.width = "100%";
    shield.style.height = "100%";
    shield.style.backgroundColor = "rgba(0, 0, 0, 0.5)"; // 半透明背景
    shield.style.zIndex = "25";

    var alertFram = document.createElement("div");
    alertFram.id = "alertFram";
    alertFram.style.position = "fixed";
    alertFram.style.width = "280px";
    alertFram.style.height = "200px";
    alertFram.style.left = "50%";
    alertFram.style.top = "50%";
    alertFram.style.transform = "translate(-50%, -50%)"; // 居中显示
    alertFram.style.backgroundColor = "#f9f9f9";
    alertFram.style.border = "1px solid #ccc";
    alertFram.style.borderRadius = "8px";
    alertFram.style.zIndex = "300";

    var strHtml = "<ul style=\"list-style:none;margin:0px;padding:0px;width:100%\">\n";
    strHtml += " <li style=\"background:#729FFB;text-align:left;padding-left:20px;font-size:14px;font-weight:bold;height:25px;line-height:25px;border-top-left-radius: 8px;border-top-right-radius: 8px;color:white\">登录提示框~</li>\n";
    strHtml += " <li style=\"background:#f9f9f9;text-align:center;font-size:12px;height:95px;line-height:95px;border-left:1px solid #ccc;border-right:1px solid #ccc;color:#333\">" + str + "</li>\n";
    strHtml += " <li style=\"background:#f9f9f9;text-align:center;font-weight:bold;height:30px;line-height:30px; border-bottom-left-radius: 8px;border-bottom-right-radius: 8px;\"><input type=\"button\" value=\"确 定\" onclick=\"doOk()\" style=\"width:80px;height:30px;background:#007bff !important;color:white !important;border:1px solid #007bff !important;font-size:14px;outline:none;margin-top: 3px;padding:0;border-radius: 4px;text-align: center\"/></li>\n"; // 修改按钮样式，添加!important关键字
    strHtml += "</ul>\n";
    alertFram.innerHTML = strHtml;

    document.body.appendChild(shield);
    document.body.appendChild(alertFram);

    this.doOk = function () {
        alertFram.style.display = "none";
        shield.style.display = "none";
    }

    alertFram.focus();
    document.body.onselectstart = function () { return false; };
}
