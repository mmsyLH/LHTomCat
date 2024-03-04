const showMenu = (toggleId, navbarId, bodyId) => {
    const toggle = document.getElementById(toggleId),
        navbar = document.getElementById(navbarId);
    bodypadding = document.getElementById(bodyId);

    if (toggle && navbar) {
        toggle.addEventListener('click', () => {
            navbar.classList.toggle('expander')
            bodypadding.classList.toggle('body-pd')
        })
    }
}

showMenu('nav-toggle', 'navbar', 'body-pd')


const linkColor = document.querySelectorAll(".nav_link")
function colorLink() {
    linkColor.forEach(l => l.classList.remove('active'))
    this.classList.add('active')
}
linkColor.forEach(l => l.addEventListener('click', colorLink))

const linkCollapse = document.getElementsByClassName('collapse__link')
var i

for (i = 0; i < linkCollapse.length; i++) {
    linkCollapse[i].addEventListener('click', function () {
        const collapseMenu = this.nextElementSibling
        collapseMenu.classList.toggle('showCollapse')

        const rotate = collapseMenu.previousElementSibling
        rotate.classList.toggle("")
    })
}

///////////////////////////////////////////////
// 获取需要设置内容的iframe
var iframe = document.getElementById('myFrame');

// 获取所有的<a>标签
var links = document.querySelectorAll('.nav_link');

// 给每个<a>标签添加点击事件监听器
links.forEach(function (link) {
    link.addEventListener('click', function (event) {
        // 阻止默认行为，即阻止<a>标签的默认跳转行为
        event.preventDefault();

        // 获取点击的<a>标签的href属性值，即链接地址
        var href = this.getAttribute('href');

        // 设置iframe的src为点击的链接地址
        iframe.src = href;
    });
});