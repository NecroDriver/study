package com.xin.daily.service.novel;

import com.xin.web.pojo.Context;

/**
 * 小说service
 *
 * @author creator mafh 2019/11/28 16:12
 * @author updater
 * @version 1.0.0
 */
public interface INovelService {

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
    int saveNovelInfo(Context context, String novelName, String url, String coverImg, String description, Byte flagEnd);
}
