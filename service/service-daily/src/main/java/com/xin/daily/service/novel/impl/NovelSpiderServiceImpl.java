package com.xin.daily.service.novel.impl;

import com.xin.daily.common.consts.CommonConst;
import com.xin.daily.dao.MybatisUtils;
import com.xin.daily.dao.novel.NovelChapterMapper;
import com.xin.daily.dao.novel.NovelMapper;
import com.xin.daily.entity.novel.Novel;
import com.xin.daily.entity.novel.NovelChapter;
import com.xin.daily.service.novel.INovelSpiderService;
import com.xin.daily.service.novel.analyzer.NovelDocumentAnalyzer;
import com.xin.web.base.BaseService;
import com.xin.web.pojo.Context;
import com.xin.web.utils.jsoup.JsoupUtils;
import com.xin.web.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 小说抓取service实现类
 *
 * @author creator mafh 2019/12/2 22:02
 * @author updater
 * @version 1.0.0
 */
@Service
@Transactional
public class NovelSpiderServiceImpl extends BaseService implements INovelSpiderService {

    /**
     * 小说数据映射
     */
    @Autowired
    private NovelMapper novelMapper;
    /**
     * 小说章节数据映射
     */
    @Autowired
    private NovelChapterMapper novelChapterMapper;
    /**
     * 小说文档解析器
     */
    @Autowired
    private NovelDocumentAnalyzer novelDocumentAnalyzer;
    /**
     * Mybatis工具类
     */
    @Autowired
    private MybatisUtils mybatisUtils;

    /**
     * 抓取小说列表
     *
     * @param context   上下文
     * @param novelCode 小说编号
     * @return 结果
     */
    @Override
    public Integer spiderNovelList(Context context, String novelCode) {

        /*--------------------------------------------- 日志记录 -----------------------------------------------------*/
        logger.debug("抓取小说列表，小说编号：{}", novelCode);

        /*--------------------------------------------- 参数校验 -----------------------------------------------------*/
        Assert.notNull(novelCode, "小说编号不能为空！");
        UserVo userVo = context.getUser();
        Integer results = 0;
        Date nowDate = new Date();

        /*--------------------------------------------- 业务处理 -----------------------------------------------------*/
        // 获取小说
        Novel novel = novelMapper.selectByNovelCode(novelCode);
        Assert.notNull(novel, "未查询到相关小说记录！");
        try {
            List<NovelChapter> novelChapterList = JsoupUtils.getDocumentList(novel.getUrl(), novelDocumentAnalyzer, NovelChapter.class);
            for (int i = 0; i < novelChapterList.size(); i++) {
                NovelChapter novelChapter = novelChapterList.get(i);
                try {
                    // 获取章节地址
                    String url = novel.getUrl() + novelChapter.getUrl();
                    novelChapter.setUrl(url);
                    // 获取章节内容
                    Map<String, Object> contentMap = JsoupUtils.getDocumentMap(url, novelDocumentAnalyzer);
                    novelChapter.setContent(contentMap.get("content") + "");
                    Thread.sleep(500);
                } catch (Exception e) {
                    logger.error("解析html章节详情异常，错误：{}", e.getMessage());
                }
                novelChapter.setNovelCode(novelCode);
                novelChapter.setDisplayOrder(i);
                novelChapter.setFlagDelete(CommonConst.FLAG_DELETE_NO);
                novelChapter.setCreator(userVo.getUsername());
                novelChapter.setCreatorIp(context.getRequest().getRemoteAddr());
                novelChapter.setCreateTime(nowDate);
                novelChapter.setModifier(userVo.getUsername());
                novelChapter.setModifierIp(context.getRequest().getRemoteAddr());
                novelChapter.setModifyTime(nowDate);
                // 获取比例
                int percent = (i + 1) * 100 / novelChapterList.size();
                // websocket发送实时转换进度 todo
            }
            // 数据存入数据库
            results = mybatisUtils.batchInsert(novelChapterList, "com.xin.daily.dao.novel.NovelChapterMapper.insert", 1000);
        } catch (Exception e) {
            logger.error("解析html章节列表异常，错误：{}", e.getMessage());
        }

        /*--------------------------------------------- 日志记录 -----------------------------------------------------*/
        logger.debug("抓取小说列表结束，插入记录数：{}", results);

        /*--------------------------------------------- 日志记录 -----------------------------------------------------*/
        return results;
    }
}
