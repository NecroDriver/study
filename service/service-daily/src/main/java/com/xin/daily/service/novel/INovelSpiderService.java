package com.xin.daily.service.novel;

import com.xin.web.pojo.Context;

/**
 * 小说抓取service
 *
 * @author creator mafh 2019/12/2 21:16
 * @author updater
 * @version 1.0.0
 */
public interface INovelSpiderService {

    /**
     * 抓取小说列表
     *
     * @param context   上下文
     * @param novelCode 小说编号
     * @return 结果
     */
    Integer spiderNovelList(Context context, String novelCode);
}
