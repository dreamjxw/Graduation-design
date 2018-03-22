package com.jxw.design.dao.slave;


import com.jxw.design.model.Wine;
import com.jxw.design.model.resp.BannerPictureResp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/21 19:57
 */
@Repository
public interface WineSlaveDao {
    /**
     * 根据酒品ID查询
     *
     * @param wineId
     * @return
     */
    Wine selectByWineId(Long wineId);

    /**
     * 根据酒品评分排序
     *
     * @return
     */
    List<Wine> selectWineScoreDesc();

    /**
     * 酒品评分获取Top 5
     *
     * @return
     */
    List<BannerPictureResp> selectWineScoreTop();

    /**
     * 批量查询
     *
     * @param data
     * @return
     */
    List<Wine> selectWineByWineIdBatch(@Param("data") Integer[] data);

    /**
     * 私人酒窖推荐
     *
     * @param wineClass
     * @param wineBrand
     * @param wineAddress
     * @return
     */
    List<Wine> selectPrivateWine(@Param("wineClass") String wineClass, @Param("wineBrand") String wineBrand, @Param
            ("wineAddress") String wineAddress);

    /**
     * 搜索
     *
     * @param content
     * @return
     */
    List<Wine> searchWine(@Param("content") String content);

}