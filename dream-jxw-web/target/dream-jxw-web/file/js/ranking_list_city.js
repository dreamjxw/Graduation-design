var myDate = new Date();
//获取当前日
var date=myDate.getDate();
var now=date
$('#nowtime').html(now);

$.ajax({
    type:'post',
    data:'{"openid":"oEqFyv3VKmOe7H2EVO33ClIyP4wI","currentPage":"2"}',
    url:'http://dream555.ngrok.cc/userrank/allranking',
    contentType:'application/json',
    success:function(res){
        console.log(res);
        var arrData=res;
        for(var i=0;i<arrData.length;i++){
            var goodsTPL ='<div class="section-first-one ">\n'+
                '<div class="left-image">\n'+
                '<img src="" class="king-image">\n'+
                '</div>\n'+
                '<div class="right-wine-list" >\n'+
                '<div class="red-wine-name">\n'+
                '<p class="pet-name"></p>\n'+
                '<span class="num-collect">num</span>人关注\n'+
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
                }
                if(attr == 'nickname'){
                    $('.pet-name').eq(i).html(arrData[i][attr]);
                }
                if(attr == 'headimgurl'){
                    $('.king-image').eq(i)[0].src = arrData[i][attr];
                }
            }
        }
    },
    error:function(){
        alert(123);
    }
});

