var doms = {
    carousel: document.querySelector('.banner .carousel'),
    indicators: document.querySelectorAll('.banner .indicator span'),
  };
  console.log("banner",doms)
  var currentIndex = 0; //轮播图索引

  //移动轮播图到第几个板块
  function moveTo(index) {
    doms.carousel.style.transform = `translateX(-${index}00%)`;
    //去除当前选中的指示器 
    var active = document.querySelector('.banner .indicator span.active');
    active.classList.remove('active');
    //重新设置选择的指示器
    doms.indicators[index].classList.add('active');
    currentIndex = index; // 更新当前索引
  }

  // 注册点击事件
  doms.indicators.forEach(function(item, i) {
    item.onclick = function() {
      moveTo(i)
    }
  })

  // 自动切换轮播图
  window.setInterval(function() {
    currentIndex = (currentIndex + 1) % doms.indicators.length; // 更新索引
    moveTo(currentIndex);
  }, 2000);