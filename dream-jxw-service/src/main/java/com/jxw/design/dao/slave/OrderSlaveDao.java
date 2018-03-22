package com.jxw.design.dao.slave;

import com.jxw.design.model.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/18 11:30
 */
@Repository
public interface OrderSlaveDao {

    /**
     * 根据订单号查询订单
     *
     * @param orderId
     * @return
     */
    Order selectByPrimaryKey(@Param("orderId") Long orderId);

    /**
     * 计算用户购买量
     *
     * @param userId
     * @return
     */
    Long countUserBuyTotal(@Param("userId") String userId);

    /**
     * 根据用户Id查询订单
     *
     * @param userId
     * @return
     */
    List<Order> selectByUserId(@Param("userId") String userId);

    /**
     * 根据用户Id查询最近6条订单
     *
     * @param userId
     * @return
     */
    List<Order> selectByUserIdNearToday(@Param("userId") String userId);
}
