package com.jxw.design.dao.master;

import com.jxw.design.model.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/18 11:31
 */
@Repository
public interface OrderDao {
    /**
     * 添加订单
     *
     * @param order
     * @return
     */
    int insertOrder(@Param("order") Order order);

    /**
     * 根据订单号更改订单状态
     *
     * @param order
     * @return
     */
    int updateOrderStatus(@Param("order") Order order);
}
