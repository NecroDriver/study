package com.xin.daily.dao.novel;

import com.xin.daily.entity.novel.Novel;
import com.xin.daily.entity.novel.NovelChapter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author creator mafh 2019/11/28 17:56
 * @author updater
 * @version 1.0.0
 */
@Mapper
public interface NovelMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(Novel record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Novel record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Novel record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Novel record);

    /**
     * 根据小说名称查询小说
     *
     * @param novelName 小说名称
     * @return 小说对象
     */
    Novel selectByNovelName(String novelName);

    /**
     * 根据小说编号查询小说
     *
     * @param novelCode 小说编号
     * @return 小说对象
     */
    Novel selectByNovelCode(String novelCode);
}