package com.xin.daily.service.novel.impl;

import com.xin.daily.common.consts.CommonConst;
import com.xin.daily.dao.novel.NovelMapper;
import com.xin.daily.entity.novel.Novel;
import com.xin.daily.service.novel.INovelService;
import com.xin.web.base.BaseService;
import com.xin.web.pojo.Context;
import com.xin.web.utils.crypt.SnowFlake;
import com.xin.web.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * 小说service实现类
 *
 * @author creator mafh 2019/11/28 16:12
 * @author updater
 * @version 1.0.0
 */
@Service
@Transactional
public class NovelServiceImpl extends BaseService implements INovelService {

    /**
     * 小说dao
     */
    @Autowired
    private NovelMapper novelMapper;

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
    @Override
    public int saveNovelInfo(Context context, String novelName, String url, String coverImg, String description, Byte flagEnd) {

        /*-----------------------------日志记录-----------------------------*/
        logger.debug("保存小说，小说名称：{}，小说地址：{}，封面图片：{}，描述：{}，是否完结：{}", novelName, url, coverImg, description, flagEnd);

        /*-----------------------------参数处理-----------------------------*/
        Assert.notNull(novelName, "小说名称不能为空！");
        Assert.notNull(url, "小说名称不能为空！");
        Assert.notNull(coverImg, "小说地址不能为空！");
        Assert.notNull(description, "封面图片不能为空！");
        Assert.notNull(flagEnd, "是否完结不能为空！");
        Date nowDate = new Date();
        UserVo userVo = context.getUser();

        /*-----------------------------业务处理-----------------------------*/
        // 判别小说名称是否重复
        Assert.isNull(novelMapper.selectByNovelName(novelName), "小说名称已存在！");
        Novel novel = new Novel();
        novel.setNovelName(novelName);
        SnowFlake snowFlake = new SnowFlake(2, 1);
        String novelCode = "XS" + snowFlake.nextId();
        novel.setNovelCode(novelCode);
        novel.setUrl(url);
        novel.setCoverImg(coverImg);
        novel.setDescription(description);
        novel.setFlagEnd(flagEnd);
        novel.setFlagUpdate(CommonConst.FLAG_UPDATE_NO);
        novel.setFlagDelete(CommonConst.FLAG_DELETE_NO);
        novel.setCreator(userVo.getUsername());
        novel.setCreateTime(nowDate);
        novel.setCreatorIp(context.getRequest().getRemoteAddr());
        novel.setModifier(userVo.getUsername());
        novel.setModifyTime(nowDate);
        novel.setModifierIp(context.getRequest().getRemoteAddr());
        int num = novelMapper.insert(novel);

        /*-----------------------------日志记录-----------------------------*/
        logger.debug("保存小说结束，小说id：{}", novel.getId());

        /*-----------------------------方法返回-----------------------------*/
        return num;
    }
}
