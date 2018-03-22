
// 严选品牌
function ajaxStrictWine(id, list) {
    $.ajax({
        method: 'post',
        url: 'http://lzhd.ngrok.cc/redGrapes/viewBrand',
        data: id,            // 传数据给后台
        contentType: 'application/json',
        success: function (res) {
            console.log(res);
            var resLen = res.length;              // 5
            var tName, tImg;
            var count = -1;
            var mbClRecommend = '<li class="mb-cl-recommend">\n' +
                                    '<a href="#">\n' +
                                    '<img class="mb-cl-recommend-img" src="">\n' +
                                    '</a>\n' +
                                    '<span class="mb-cl-recommend-name"></span>\n' +
                                 '</li>\n';

            for (var i = 0; i < resLen; i++) {
                var mbContentList = res[i].length;         // 各个品牌数组的长度 3
                var content = '';
                for (var j = 0; j < mbContentList; j++) {
                    content += mbClRecommend;
                }
                $(list).eq(i).html($(list).eq(i).html() + content);       // 将每种品牌的三个品牌渲染到页面
                for (j = 0; j < mbContentList; j++) {
                    for (var attr in res[i][j]) {
                        if (attr == 'wineBrand') {
                            tName = res[i][j][attr];
                        } else if (attr == 'wineImg') {
                            tImg = res[i][j][attr];
                        }
                    }
                    count++;
                    $('.mb-cl-recommend-name').eq(count).html(tName);
                    $('.mb-cl-recommend-img').eq(count).attr('src' , tImg);
                }
            }
        },
        error: function () {
            console.log('请求失败！');
        }
    });
}

// 点击“严选品牌”渲染红葡萄酒
$('.strict').on('touchstart mousedown', function () {
    ajaxStrictWine('{"wine_class":"1"}' , '.red-wine .mb-content-list');
});

// // 点击“红葡萄酒”渲染红葡萄酒
$('.mb-head-left').on('touchstart mousedown', function () {
    ajaxStrictWine('{"wine_class":"1"}' , '.red-wine .mb-content-list');
});

// // 点击“白葡萄酒”渲染白葡萄酒
$('.mb-head-right').on('touchstart mousedown', function () {
    ajaxStrictWine('{"wine_class":"1"}' , '.white-wine .mb-content-list');
});

