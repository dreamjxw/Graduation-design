package com.jxw.design.service;

import com.jxw.design.model.WineStock;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/8 13:01
 * @Description
 */
public interface WineStockService {
    /**
     * 下订单时扣减库存
     *
     * @param windReal 购买数
     * @param windId
     * @return
     */
    int updateStock(Long windId, Long windReal);

    /**
     * 检查某一酒品库存
     *
     * @param windId
     * @param windReal
     * @return
     */
    boolean checkStock(Long windId, Long windReal);

    /**
     * 添加库存数
     *
     * @param wine
     * @param stock
     * @return
     */
    int insertStock(Long wine, Long stock);

    /**
     * 修改库存数
     *
     * @param wine
     * @param stock
     * @return
     */
    int upStock(Long wine, Long stock);

    /**
     * 删除库存（删除商品时用到）
     *
     * @param wineId
     * @return
     */
    int deleteStock(Long wineId);

    /**
     * 查询库存
     *
     * @param wineId
     * @return
     */
    WineStock selectStock(Long wineId);
}
