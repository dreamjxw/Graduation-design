$.ajax({
	method:'post',
	url:'http://shopcartcbw.ngrok.cc/ShopCartController/totalPrice',
	contentType: 'application/json',
	data:'[{"openid":"111","wineId":"111","wineNum":"10","winePrice":22300,"selStatus":true}]',
	success:function(res){
		console.log(res);
        var arr={'0':'../img/shopping_cart/check.png','1':'../img/shopping_cart/check_y.png'};
		//console.log(res.length);
    	var num = res.length;
		for(var i=0;i<res.length;i++){
		    //var bOn = true;
			var goodsList = '<li>\n'+
					'<div class="section-left">\n'+
                    	'<div class="check">\n'+
                    		'<img src="../img/shopping_cart/check.png" style="width:0.25rem;height:0.25rem">\n'+
                    	'</div>\n'+
                    	'<img src="http://pics.sc.chinaz.com/files/pic/pic9/201703/bpic843.jpg" class="winePicture" style="width:1.5rem;height:1.5rem">\n'+
                	'</div>\n'+
                	'<div class="center">\n'+
                   		'<p class="wineName">'+ res[i].wineName +'</p>\n'+
                    	'<p>\n'+
                    		'<span class="wineGrade">\n'+' 评分 '+ res[i].wineScore +' 分' +
                    		'</span><span class="winePrice">\n'+ ' | 售价 ￥'+ res[i].winePrice + '</span>\n'+
						'</p>\n'+
                    '</div>\n'+
                    '<div class="section-right">\n'+
                    	'<div class="right">\n'+
                         	'<div>'+'编辑'+'</div>\n'+
                    	'</div>\n'+
	                    '<div class="count">\n'+
	                        '<div class="minus">' + '-' + '</div>\n'+
	                        '<div class="number">' + '1' + '</div>\n'+
	                        '<div class="add">' + '+' + '</div>\n'+
	                    '</div>\n'+
                      '<input type="button" name="" class="delet" value="删除">\n'+
                	'</div>\n'+
            	'</li>';
            $('.goods').html($('.goods').html()+goodsList);
            $('.section-right .right').on('touchstart',function(){
                $(this).css('display','none');
                $(this).next().css('display','none');
                $(this).next().next().css('display','block');
                $('.delet').on('touchstart',function(){
                $(this).parent().parent().remove();
                });
            })

            for(var attr in res[i]){
              if(attr == 'wineImg'){
                $('.winePicture').eq(i)[0].src = res[i][attr];
              }
            }

		}
  //  选择商品
    var bOn = true;
    $('.check img').eq($(".check img").length-1).on('touchstart',function(){
      if(bOn){
        $('.check img').each(function(i){
          $('.check img').eq(i)[0].src=arr[1];
          $('.count').eq(i).css('display','flex');
          price += parseInt(res[i].winePrice);
          $('.cart-accounts .price .commodity-price').html(price);
        })
        bOn = !bOn;
      }
      else{
        $('.check img').each(function(i){
          $('.check img').eq(i)[0].src=arr[0];
          $('.count').eq(i).css('display','none');
          $('.cart-accounts .price .commodity-price').html(0);
        })
        bOn = !bOn;
      }
    })
    $('.check img').each(function(i){
      $('.check img').eq(i).bOn = true;
      $('.check img').eq(i).on('touchstart',function(){
          if(this.bOn){
            $(this)[0].src=arr[0];
             this.bOn = !this.bOn;
             price -= parseInt(res[i].winePrice) * parseInt($('.number').eq(i).html());
             $('.cart-accounts .price .commodity-price').html(price);
             $('.count').eq(i).css('display','none');
          }
          else{
             $(this)[0].src=arr[1];
              this.bOn = !this.bOn;
              price += parseInt(res[i].winePrice);
              $('.cart-accounts .price .commodity-price').html(price);
              console.log(price);
              $('.count').eq(i).css('display','flex');
          }
      })
    });


    //加减数量
    $('.add').each(function(i){
      $('.add').eq(i).on('touchstart',function(){
        $('.number').eq(i).html(parseInt($('.number').eq(i).html())+1);
        price += parseInt(res[i].winePrice);
        $('.cart-accounts .price .commodity-price').html(price);
        console.log(price);
      })
    })
    $('.minus').each(function(i){
      $('.minus').eq(i).on('touchstart',function(){
          if(parseInt($('.number').eq(i).html()) > 1)
          {
            $('.number').eq(i).html(parseInt($('.number').eq(i).html())-1);
            price -= parseInt(res[i].winePrice);
            $('.cart-accounts .price .commodity-price').html(price);
            console.log(price);
          }
          else{
            $('.number').eq(i).html(1);
          }
      })
    })

	},
	error:function(){

	}

})
