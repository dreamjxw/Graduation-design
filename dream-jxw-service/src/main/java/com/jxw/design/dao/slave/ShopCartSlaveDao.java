package com.jxw.design.dao.slave;


import com.jxw.design.model.ShopCart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/18 11:55
 */
@Repository
public interface ShopCartSlaveDao {
    /**
     * 添加购物车时检测商品重复性
     *
     * @param userId
     * @param wineId
     * @return
     */
    ShopCart checkRepeat(@Param("userId") String userId, @Param("wineId") Long wineId);

    /**
     * 根据userId查询购物车信息
     *
     * @param userId
     * @return
     */
    List<ShopCart> selectByUserId(@Param("userId") String userId);

}