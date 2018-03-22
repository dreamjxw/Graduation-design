package com.jxw.design.dao.master;


import com.jxw.design.model.req.ShopCartReq;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/18 11:32
 */
@Repository
public interface ShopCartDao {
    /**
     * 添加购物车
     *
     * @param shopCartReq
     * @return
     */
    int insertShopCart(@Param("shopCartReq") ShopCartReq shopCartReq);

    /**
     * 更改购物车
     *
     * @param shopCartReq
     * @return
     */
    int updateShopCart(@Param("shopCartReq") ShopCartReq shopCartReq);

    /**
     * 删除购物车（删除某一件商品）
     *
     * @param shopCartReq
     * @return
     */
    int deleteShopCart(@Param("shopCartReq") ShopCartReq shopCartReq);

    /**
     * 清空购物车
     *
     * @param userId
     * @return
     */
    int deleteAllShopCart(@Param("userId") String userId);

    /**
     * 批量删除购物车商品（下订单后购物车中商品将被删除）
     *
     * @param userId
     * @param wineId
     * @return
     */
    int deleteShopCartByWineIdBatch(@Param("userId") String userId, @Param("wineId") int[] wineId);
}