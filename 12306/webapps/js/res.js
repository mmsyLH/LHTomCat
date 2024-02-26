function submitForm() {
    var userName = document.getElementById("userName").value; // 用户名
    var password = document.getElementById("passWord").value; // 登录密码
    var confirmPassword = document.getElementById("confirmPassWord").value; // 确认密码
    var name = document.getElementById("regist_name").value; // 姓名
    var cardCode = document.getElementById("cardCode").value; // 证件号码
    var mobileNo = document.getElementById("mobileNo").value; // 手机号码

    // 检查是否为空
    if (checkEmpty(mobileNo, "手机号码不能为空") ||
        checkEmpty(userName, "用户名不能为空") ||
        checkEmpty(password, "密码不能为空") ||
        checkEmpty(confirmPassword, "确认密码不能为空") ||
        checkEmpty(name, "姓名不能为空") ||
        checkEmpty(cardCode, "证件号码不能为空")
    ) {
        return;
    }
}
function checkEmpty(value, message) {
    console.log(value)
    if (value === "" || value.value === "") {
        alert(message);
        return true;
    }
    return false;
}
function checkEmptyTips(value, message, tipsName) {
    var tips = document.getElementById(tipsName);
    console.log(tips);
    console.log(value)
    if (value === "" || value.value === "") {
        tips.innerHTML = message;

        return true;
    }
    return false;
}
function mOver(ele) {
    console.log("谢谢")
    ele.innerHTML = "谢谢"
}
function mOut(ele) {
    console.log("把鼠标放在上面")
    ele.innerHTML = "把鼠标放在上面"
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
