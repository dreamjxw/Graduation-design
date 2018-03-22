/**
 * Created by 83858 on 2017/8/7.
 */

// 轮播图
var slideshow = $('.banner').swiper ({
    autoplay: 3000,
    direction: 'horizontal',
    loop: true,

    // 如果需要分页器
    pagination: '.swiper-pagination',
    autoplayDisableOnInteraction : false
    // paginationClickable: true
});


// 大选项卡
$(function() {
    var TabControl_1 = $('.selector-main').swiper ({
        speed: 200,
        noSwiping : true,                 // 静止鼠标拖动
        noSwipingClass : 'stop-swiping',  // 同上
        onSlideChangeStart : function() {
            $('.tabs .active').removeClass('active');
            $('.tabs li').eq(TabControl_1.activeIndex).addClass('active');
        }
    });

    $('.tabs li').on('touchstart mousedown', function(e) {
        e.preventDefault();
        $('.tabs .active').removeClass('active');
        $(this).addClass('active');
        TabControl_1.swipeTo($(this).index());
    });

    $('.tabs li').click(function(e) {
        e.preventDefault();
    });
});


// 严选品牌（红葡萄酒、白葡萄酒）

$(function() {
    var TabControl_2 = $('.selector-wine').swiper ({
        speed : 500,
        noSwiping : true,                 // 静止鼠标拖动
        noSwipingClass : 'stop-swiping',  // 同上
        onSlideChangeStart : function() {
            $('.tabs_2 .active').removeClass('active');
            $('.tabs_2 .mb-head-list').eq(TabControl_2.activeIndex).addClass('active');
        }
    });

    $('.tabs_2 .mb-head-list').on('touchstart mousedown', function(e) {
        e.preventDefault();
        $('.tabs_2 .active').removeClass('active');
        $(this).addClass('active');
        TabControl_2.swipeTo($(this).index());
    });

    $('.tabs_2 .mb-head-list').click(function(e) {
        e.preventDefault();
    });


});
