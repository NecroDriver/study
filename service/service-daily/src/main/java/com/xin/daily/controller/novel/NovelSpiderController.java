package com.xin.daily.controller.novel;

import com.xin.daily.service.novel.INovelSpiderService;
import com.xin.web.base.BaseController;
import com.xin.web.pojo.Context;
import com.xin.web.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 小说爬取控制类
 *
 * @author creator mafh 2019/12/2 15:38
 * @author updater
 * @version 1.0.0
 */
@RestController
@RequestMapping("/novel")
public class NovelSpiderController extends BaseController {

    /**
     * 日志抓取service
     */
    @Autowired
    private INovelSpiderService novelSpiderService;

    /**
     * 抓取小说列表
     *
     * @param context   上下文
     * @param novelCode 小说编号
     * @return 结果
     */
    @RequestMapping("/spider/list/{novelCode}")
    public ResultVo spiderNovelList(Context context, @PathVariable String novelCode) {
        Integer num = novelSpiderService.spiderNovelList(context, novelCode);
        return ResultVo.newResultVo(num > 0, num, "抓取小说列表");
    }
}
