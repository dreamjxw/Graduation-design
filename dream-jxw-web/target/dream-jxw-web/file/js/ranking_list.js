$('.three-spot').on('click',function (){
    $('#ball-window').show();
});
$('#shopping-cart').on('click',function (){
    $('#ball-window').hide();
});
$('#dislike').on('click',function (){
    $('#ball-window').hide();
});

var myDate = new Date();
//获取当前日
var date=myDate.getDate();
var now=date
$('#nowtime').html(now);
$.ajax({
    type:'post',
    data:'{	"currentPage":"1"}',
    url:'http://oomforjmm.imwork.net:12965/recommend/getHotList',
    contentType:'application/json',
    success:function(res){
        console.log(res);
        for(var attr in res){
            console.log('wines');
            var goodsTPL ='<div class="about-wine" >\n'+
                '<div class="red-wine-name">\n'+
                '<p>酒名酒名酒名</p>\n'+
                '<span class="public-ranking">评分&nbsp;7分</span><span>售价&nbsp;88元</span>\n'+
            '</div>\n'+
            '<div class="three-spot">\n'+
                '<div class="spot-one"></div>\n'+
                '<div class="spot-two"></div>\n'+
                '<div class="spot-three"></div>\n'+
                '</div>\n'+
                '</div>\n'+
                '</div>\n';
            $('.section-one').html($('.section-left')[0].innerHTML+goodsTPL);
        }
        for(var attr in res){
            // console.log(res[5][attr]);
            for(var i=0;i<res.length;i++){
                if(attr  == 'wines'){
                    console.log(res[i][attr]);
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
