var myDate = new Date();
//获取当前日
var date=myDate.getDate();
var now=date
$('#nowtime').html(now);

$.ajax({
    type:'post',
    data:'{"openid":"1"}',
    url:' http://dream555.ngrok.cc/userrank/allranking',
    contentType:'application/json',
    success:function(res){
        console.log(res);
        var arrData=res;
        for(var i=0;i<arrData[0].length;i++){
            var goodsTPL ='<div class="section-first ">\n'+
                '<div class="left-image">\n'+
                '<img src="img/index/header-portrait.jpg" class="king-image">\n'+
                '</div>\n'+
                '<div class="right-wine-list" >\n'+
                '<div class="red-wine-name">\n'+
                '<p>#酒单酒单酒单#</p>\n'+
            '<span class="num-collect">num人收藏</span>\n'+
                '</div>\n'+
                '<div class="heart">\n'+
                '<img src="img/index/heart.png" />\n'+
                '</div>\n'+
                '</div>\n'+
                '</div>\n';
            $('section').html($('section')[0].innerHTML+goodsTPL);
        }
        for(var i=0;i<arrData.length;i++){
            for(var attr in arrData[i]){
                // console.log(arrData[i][attr]);
                if(attr == 'fansNum'){
                    $('.num-collect').eq(i).html(arrData[i][attr]);
                    // console.log($('.num-collect').eq(i).html(arrData[i][attr]));
                }
                if(attr == 'nickname'){
                    $('.num-collect').eq(i).html(arrData[i][attr]);
                }

            }
        }
        // for(var i=0;i<arrData.length;i++) {
        //     for (var j = 0; j < arrData[i].length; j++) {
        //         for (var attr in arrData[i][j]) {
        //             if(attr == 'nickname'){
        //                 $('.nick-name').eq(j).html(arrData[i][j][attr]);
        //                 // console.log(arrData[i][j][attr]);
        //             }
        //             if(attr == 'headimgurl'){
        //                 $('.left-image').eq(j)[0].src=arrData[i][j][attr];
        //             }
        //             if(attr == 'userIdentity'){
        //                 $('.identity').eq(j).html(arrData[i][j][attr]);
        //             }
        //             if(attr == 'messageContent'){
        //                 $('.friend-news').eq(j).html(arrData[i][j][attr]);
        //             }
        //             if(attr == 'messageStatus' && arrData[i][j][attr] == 1){
        //                 // alert("1");
        //                 $('.friend-news').eq(j).html('<span id="red-spot" style="display:inline-block;background-color: red;width:0.05rem;height:0.05rem;border-radius:50%;position:absolute;right:0rem;"></span>')
        //             }
        //         }
        //
        //     }
        // }
    },
    error:function(){
        alert(123);
    }
});
