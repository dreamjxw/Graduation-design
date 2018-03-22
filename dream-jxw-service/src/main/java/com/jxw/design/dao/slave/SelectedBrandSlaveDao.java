package com.jxw.design.dao.slave;

import com.jxw.design.model.resp.ShowWineResp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/26 14:51
 * @Description 严选品牌
 */
@Repository
public interface SelectedBrandSlaveDao {
    /**
     * 严选品牌（红葡萄酒）
     *
     * @return
     */
    List<ShowWineResp> redWineBrand();

    /**
     * 严选品牌（白葡萄酒）
     *
     * @return
     */
    List<ShowWineResp> whiteWineBrand();
}
