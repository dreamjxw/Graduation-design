package com.jxw.design.service;

import com.jxw.design.model.ShopCart;
import com.jxw.design.model.req.ShopCartReq;

import java.util.List;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/26 19:43
 * @Description
 */
public interface ShopCartService {
    /**
     * 添加购物车
     *
     * @param shopCartReq
     * @return
     */
    int insertShopCart(ShopCartReq shopCartReq);

    /**
     * 根据userId查询购物车信息
     *
     * @param userId
     * @return
     */
    List<ShopCart> selectByUserId(String userId);

    /**
     * 更改购物车（主要是商品数量）
     *
     * @param shopCartReq
     * @return
     */
    int updateShopCart(ShopCartReq shopCartReq);

    /**
     * 删除购物车（删除某一件商品）
     *
     * @param shopCartReq
     * @return
     */
    int deleteShopCart(ShopCartReq shopCartReq);

    /**
     * 清空购物车
     *
     * @param userId
     * @return
     */
    int deleteAllShopCart(String userId);
}
