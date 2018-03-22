package com.jxw.design.dao.master;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/18 11:56
 */
@Repository
public interface WineStockDao {
    /**
     * 删除库存（删除商品时用到）
     *
     * @param wineId
     * @return
     */
    int deleteStock(@Param("wineId") Long wineId);

    /**
     * 增加库存数
     *
     * @param wineId
     * @param stock
     * @param wineAlwaysStock
     * @return
     */
    int insertStock(@Param("wineId") Long wineId, @Param("stock") Long stock, @Param("wineAlwaysStock") Long
            wineAlwaysStock);

    /**
     * 下订单时扣减库存
     *
     * @param wineId
     * @param wineReal
     * @param wineAlwaysReal
     * @return
     */
    int updateStock(@Param("wineId") Long wineId, @Param("wineReal") Long wineReal, @Param("wineAlwaysReal") Long
            wineAlwaysReal);

    /**
     * 增加库存
     *
     * @param wineId
     * @param wineStock
     * @param wineAlwaysStock
     * @return
     */
    int upStock(@Param("wineId") Long wineId, @Param("wineStock") Long wineStock, @Param("wineAlwaysStock") Long
            wineAlwaysStock);
}