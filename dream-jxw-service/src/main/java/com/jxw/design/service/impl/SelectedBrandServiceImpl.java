package com.jxw.design.service.impl;

import com.jxw.design.dao.slave.SelectedBrandSlaveDao;
import com.jxw.design.model.resp.ShowWineResp;
import com.jxw.design.service.SelectedBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/26 15:03
 * @Description
 */
@Service
public class SelectedBrandServiceImpl implements SelectedBrandService {
    private static Logger logger = LoggerFactory.getLogger(SelectedBrandServiceImpl.class);
    @Autowired
    private SelectedBrandSlaveDao brandSlaveDao;

    @Override
    public List<ShowWineResp> redWineBrand() {
        return brandSlaveDao.redWineBrand();
    }

    @Override
    public List<ShowWineResp> whiteWineBrand() {
        return brandSlaveDao.whiteWineBrand();
    }
}
