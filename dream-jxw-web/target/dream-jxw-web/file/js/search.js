/**
 * Created by 13510 on 2017/8/6.
 */
//展示历史记录
showHistory();
function showHistory(){
    $('.history').css('display','block');
    $('.tips').css('display','none');
    $('.goodsShow').css('display','none');
    $('.search').css('border-bottom','0.01rem #999999 solid');
    showHistoryList();
}
//展示提示
function showTip(){
    $('.history').css('display','none');
    $('.tips').css('display','block');
    $('.goodsShow').css('display','none');
    $('.search').css('border-bottom','0.01rem transparent solid');
}
//展示搜索结果
function showResult(){
    $('.history').css('display','none');
    $('.tips').css('display','none');
    $('.goodsShow').css('display','block');
    $('.search').css('border-bottom','0.01rem transparent solid');
}
//输入框文字改变时，两种方式：搜索框中的删除按钮，键盘输入
$('.text').on('input propertychange',function(ev){
    var ev=ev||event;
    var str1=$('.text').val();
    var str2='';
    var timer;

    ev.onkeyup=function(){
        str2=$('.text').val();
    };
    ev.onkeydown=function(){
        clearTimeout(timer);
    };
    showTip();
    if(str1!=str2){//如果value有值，并改变的话给后台传字符
        timer=setTimeout(function(){
            $.ajax({
                type:'post',
                data:'{"key":"'+$('.text').val()+'"}',
                url:'http://hansheng.ngrok.cc/search/searchString',////////////
                contentType:'application/json',
                success:function(res){
                    //渲染提示页面
                    //console.log(res);
                    var arrTip=res;
                    console.log(arrTip);
                    var sTipList='';
                    for(var i=0;i<arrTip.length;i++){
                        sTipList+='<li><a href="#">'+arrTip[i]+'</a></li>';
                    }
                    $('.tips').html(sTipList);
                    //点击li，html()复制到搜索框里边，并进行搜索
                    $('.tips li').click(function(){
                        $('.text').val($(this).find('a').html());
                        showResult();
                        drawingResult();
                    });
                }
            });
        },300);
    }
    if($('.text').val()==''){
        showHistory();
    }
});

//删除搜索框字符
$('.delete').click(function(){
    var str=$('.text')[0].value;
    var arr=str.split('');
    arr.pop();
    $('.text').val(arr.join(''));
    if($('.text').val()!=''){
        showTip();
        //渲染提示页面
        $.ajax({
            type:'post',
            data:'{"key":"'+$('.text').val()+'"}',
            url:'http://hansheng.ngrok.cc/search/searchString',////////////////
            contentType:'application/json',
            success:function(res){
                //console.log(res);
                var arrTip=res;
                var sTipList='';
                for(var i=0;i<arrTip.length;i++){
                    sTipList+='<li><a href="#">'+arrTip[i]+'</a></li>';
                }
                $('.tips').html(sTipList);
                //点击li，html()复制到搜索框里边，并进行搜索
                $('.tips li').click(function(){
                    $('.text').val($(this).find('a').html());
                    showResult();
                    drawingResult();
                });
            }
        });
    }else{
        showHistory();
    }
});
//渲染提示页面，点击单个商品进入详情页面
var currentPage=1;
function drawingResult(){
    $.ajax({
        type:'post',
        data:'{"key":"'+$('.text').val()+'","currentPage":"'+currentPage+'"}',
        url:'http://hansheng.ngrok.cc/search/searchWine',
        contentType:'application/json',
        success:function(res){
            alert(currentPage);
            console.log(res);
            //并cookie存储历史记录
            var sValue=$('.text').val();
            sHistoryList =sHistoryList +sValue+',';
            setCookie('history',sHistoryList,1);
            var arrData=res;
            var sTipLi='';
            for(var i=0;i<arrData.length;i++){
                sTipLi+='<li><a href="#">\n'+
                    '<div class="goods-pic"><img src=""></div>\n'+
                    '<div class="goods-details">\n'+
                    '<p class="describe">此处为描述，包含搜索关键字，最多展示两行，多余展示为省略号...</p>\n'+
                    '<p class="price-grade">\n'+
                    '<span class="price">￥<span class="number"></span></span>\n'+
                    '<span class="grade">评分：<span class="number"></span></span>\n'+
                    '</p>\n'+
                    '</div>\n'+
                    '</a></li>';
            }
            $('.goodsShow').html($('.goodsShow').html()+sTipLi);
            for(var i=0;i<arrData.length;i++){
                for(var attr in arrData[i]){
                    if(attr=='winName'){
                        $('.describe').eq(i).html(arrData[i][attr]);
                    }else if(attr=='winPrice'){
                        $('.price-grade .price .number').eq(i).html(arrData[i][attr]);
                    }else if(attr=='wineScore'){
                        $('.price-grade .grade .number').html(arrData[i][attr]);
                    }else if(attr=='winImg'){
                        $('.goods-pic img').eq(i).attr('src',arrData[i][attr]);
                    }else if(attr=='winId'){
                        $('.goodsShow li').eq(i).attr('winId',arrData[i][attr]);
                    }
                }
            }
            /////////////////////////////////////
            //点击单个商品
            $('.goodsShow li').click(function(){
                $.ajax({
                    type:'post',
                    data:'{"winId":'+$(this).attr('winId')+'}',
                    url:'http://hansheng.ngrok.cc/search/searchSelectOne',
                    contentType:'application/json'
                });
            });
        }
    });
}

//封装setCookie,getCookie
function setCookie(key,value,t){
    var oDate = new Date();
    oDate.setDate(oDate.getDate()+t);
    document.cookie=key+'='+value+';expires='+oDate.toGMTString();
}
function getCookie(key) {
    var arr1 = document.cookie.split(';');
    for (var i = 0; i < arr1.length; i++) {
        var arr2 = arr1[i].split('=');
        if (arr2[0] == key) {
            return decodeURI(arr2[1]);
        }
    }
}

//点击搜索
var sHistoryList=getCookie('history');//用于存储cookie,对cookie的长度进行设置，每次设置时，删除第8个逗号后的字符
var arrHistory=[];//用于存储cookie数组，在页面渲染
$('.btn1').click(function(){
    //展示结果页面并渲染
    showResult();
    drawingResult();
    //并进行渲染历史记录页面
    getHistory();
});


//展示历史记录列表
function showHistoryList(){
    getHistory();
    sShowList='';
    //否则展示历史纪录,如果有的话展示，没有的话显示“搜索记录为空”
    if(arrHistory.length!=0){
        for(var i=0;i<arrHistory.length;i++){
            sShowList +='<li>\n'+
                '<span class="historyText">'+arrHistory[i]+'</span>\n'+
                '<span class="delete2"><img src="img/search/delete2.png"/></span>\n'+
                '</li>';
        }
        $('.history-list').html(sShowList);
    }else{
        //alert('搜索记录为空');
    }

    //删除历史记录
    $('.delete2').click(function(){
        //页面删除
        $(this)[0].parentNode.remove();
        //cookie删除
        removeOneCookie('history',$(this).siblings('.historyText').html());
        return false;
    });
    //历史记录点击进行搜索
    $('.history-list li').click(function(){
        $('.text').val($(this). find('.historyText').html());
        showResult();
        drawingResult();
        getHistory();
    });
}
//获取历史记录cookie，并生成一个数组
var sShowList='';//字符串装入html中，生成历史记录
function getHistory(){
    arrHistory = decodeURI(getCookie('history')).split(',');//把字符串变成数组
    //数组去重，新搜历史纪录放在首位
    arrHistory.reverse();
    for(var j = 0; j < arrHistory.length; j++) {
        for (var k = j + 1; k < arrHistory.length; k++) {
            if (arrHistory[k] == arrHistory[j]) {
                arrHistory.splice(k,1);
                k--;
            }
        }
    }
    //搜索之后先对cookie存储字段，再转化为数组，字符串最后多加了逗号，数组多一项，需进行删除；字符串向后添加新东西，数组需进行反序
    arrHistory.shift();
    if(arrHistory.length>=6){
        arrHistory.splice(6,k-6);
    }
    return arrHistory;
}

//删除单个历史纪录
function removeOneCookie(key,value){
    for(var i=0;i<arrHistory.length;i++){
        if(arrHistory[i]==value){
            arrHistory.splice(i,1);
        }
    }
    var newArr=arrHistory.concat();
    newArr.reverse();
    var newSArr=newArr.join(',')+',';
    setCookie('history',newSArr,1);
}


$(window).scroll(function(){
    if($(window).scrollTop()>=$(document).height()-$(window).height()){
        pull();
    }
});
function pull(){
    currentPage++;
    var init = {x:5,y:5,sx:0,sy:0,ex:0,ey:0};
    window.addEventListener("touchstart",function(){
        sTime = new Date().getTime();
        init.sx = event.touches[0].pageX;
        init.sy = event.touches[0].pageY;
        init.ex = init.sx;
        init.ey = init.sy;
    }, false);
    window.addEventListener("touchmove",function() {
        //event.preventDefault();//阻止触摸时浏览器的缩放、滚动条滚动
        init.ex = event.touches[0].pageX;
        init.ey = event.touches[0].pageY;
    }, false);
    window.addEventListener("touchend",function() {
        var changeX = init.sx - init.ex;
        var changeY = init.sy - init.ey;
        if(Math.abs(changeY)>Math.abs(changeX)&&changeY>0){
            drawingResult();
        }
    }, false);
}

