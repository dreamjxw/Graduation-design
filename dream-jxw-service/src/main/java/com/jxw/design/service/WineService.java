package com.jxw.design.service;

import com.jxw.design.model.Wine;

import java.util.List;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/25 20:46
 */
public interface WineService {
    /**
     * 根据酒品Id查询酒品详情
     *
     * @param wineId
     * @return
     */
    Wine selectWineByWineId(Long wineId);
    /**
     * 批量查询
     *
     * @param data
     * @return
     */
    List<Wine> selectWineByWineIdBatch(Integer[] data);

    /**
     * 搜索
     *
     * @param content
     * @return
     */
    List<Wine> searchWine(String content);

    /**
     * 添加商品
     *
     * @param wine
     * @return
     */
    int insertWine(Wine wine);

    /**
     * 删除商品
     *
     * @param wine
     * @return
     */
    int deleteWine(Wine wine);

    /**
     * 修改商品（价格）
     *
     * @param wine
     * @return
     */
    int updateWine(Wine wine);

}
