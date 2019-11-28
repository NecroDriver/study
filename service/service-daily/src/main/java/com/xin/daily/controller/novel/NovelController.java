package com.xin.daily.controller.novel;

import com.xin.daily.service.novel.INovelService;
import com.xin.web.base.BaseController;
import com.xin.web.pojo.Context;
import com.xin.web.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 小说controller
 *
 * @author creator mafh 2019/11/28 14:36
 * @author updater
 * @version 1.0.0
 */
@RestController
@RequestMapping("/study/novel")
public class NovelController extends BaseController {

    /**
     * 小说service
     */
    @Autowired
    private INovelService novelService;

    /**
     * 保存小说
     *
     * @param context     上下文
     * @param novelName   小说名称
     * @param url         小说地址
     * @param coverImg    封面图片
     * @param description 描述
     * @param flagEnd     是否完结
     * @return 结果
     */
    @PostMapping("/save")
    public ResultVo saveNovelInfo(Context context, String novelName, String url, String coverImg, String description, Byte flagEnd) {
        int num = novelService.saveNovelInfo(context, novelName, url, coverImg, description, flagEnd);
        return ResultVo.newResultVo(num > 0, num, "小说保存");
    }
}
