package com.jxw.design.controller;

import com.google.gson.Gson;
import com.jxw.design.model.Wine;
import com.jxw.design.model.resp.ShowWineResp;
import com.jxw.design.service.RecommendService;
import com.jxw.design.service.SelectedBrandService;
import com.jxw.design.view.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/26 15:11
 * @Description 严选品牌
 */
@CrossOrigin
@Controller
@RequestMapping("/jxw/design/selectBrand")
public class SelectBrandController {
    private static Logger logger = LoggerFactory.getLogger(SelectBrandController.class);
    @Autowired
    private SelectedBrandService brandService;
    @Autowired
    private RecommendService recommendService;

    @RequestMapping(value = "redWineBrand.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result redWineBrand() {
        try {
            logger.info("【严选品牌】请求获取红葡萄酒榜");
            List<ShowWineResp> showWineResps = brandService.redWineBrand();
            if (CollectionUtils.isEmpty(showWineResps)) {
                logger.info("【严选品牌】查询红葡萄酒列表为空");
                return Result.buildFailedResult(-1, "查询红葡萄酒列表为空");
            }
            logger.info("【严选品牌】查询红葡萄酒列表", new Gson().toJson(showWineResps));
            return Result.buildSuccessResult(showWineResps);
        } catch (IllegalArgumentException ie) {
            logger.error("【严选品牌】非法参数异常", ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【严选品牌】查询红葡萄酒列表时出现异常", e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

    @RequestMapping(value = "whiteWineBrand.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result whiteWineBrand() {
        try {
            logger.info("【严选品牌】请求获取白葡萄酒榜");
            List<ShowWineResp> showWineResps = brandService.whiteWineBrand();
            if (CollectionUtils.isEmpty(showWineResps)) {
                logger.info("【严选品牌】查询白葡萄酒列表为空");
                return Result.buildFailedResult(-1, "查询白葡萄酒列表为空");
            }
            logger.info("【严选品牌】查询白葡萄酒列表", new Gson().toJson(showWineResps));
            return Result.buildSuccessResult(showWineResps);
        } catch (IllegalArgumentException ie) {
            logger.error("【严选品牌】非法参数异常", ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【严选品牌】查询白葡萄酒列表时出现异常", e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

    @RequestMapping(value = "hotWineBrand.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result hotWineBrand() {
        try {
            logger.info("【严选品牌】请求获取热销榜");
            List<Wine> wineList = recommendService.hotListWine();
            if (CollectionUtils.isEmpty(wineList)) {
                logger.info("【严选品牌】获取热销榜失败");
                return Result.buildFailedResult(-1, "获取热销榜失败");
            }
            logger.info("【严选品牌】请求获取热销榜成功,返回参数:{}", new Gson().toJson(wineList));
            return Result.buildSuccessResult(wineList);
        } catch (IllegalArgumentException ie) {
            logger.error("【严选品牌】非法参数异常", ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【严选品牌】获取热销榜时出现异常", e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }
}
