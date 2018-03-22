package com.jxw.design.dao.slave;


import com.jxw.design.model.WineStock;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/18 11:48
 */
@Repository
public interface WineStockSlaveDao {
    /**
     * 根据酒品ID查询酒品库存
     *
     * @param wineId
     * @return
     */
    WineStock selectByWineId(@Param("wineId") Long wineId);

    /**
     * 查询所有酒品库存
     *
     * @return
     */
    List<WineStock> selectAllWineStock();
}