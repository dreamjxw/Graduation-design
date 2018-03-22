package com.jxw.design.dao.master;


import com.jxw.design.model.Wine;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/21 19:58
 */
@Repository
public interface WineDao {

    /**
     * 添加商品
     *
     * @param wine
     * @return
     */
    int insertWine(@Param("wine") Wine wine);

    /**
     * 更改商品（价格）
     *
     * @param wine
     * @return
     */
    int updateWine(@Param("wine") Wine wine);

    /**
     * 删除商品
     *
     * @param wine
     * @return
     */
    int deleteWine(@Param("wine") Wine wine);

    /**
     * 根据酒品ID更新酒品评分
     *
     * @param wineId
     * @param wineScore
     * @return
     */
    int updateWineScore(@Param("wineId") Long wineId, @Param("wineScore") double wineScore);
}