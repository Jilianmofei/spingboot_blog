package com.yoyo.service;

import com.yoyo.pojo.Comment;
import com.yoyo.pojo.CommentCombination;

import java.util.List;
import java.util.Map;

/**
 * @author Chester
 */

public interface CommentCombinationService {
    /**
     * 插入一条评论
     * @param comment
     * @return
     */
    int insertComment(Comment comment);

    /**
     * 根据主键删除一条评论
     * @param id
     * @return
     */
    int deleteCommentById(Integer id);

    /**
     * 分页查找
     * @param map
     * @return
     */
    List<CommentCombination> findCommentByPage(Map<String,Object> map);

    /**
     * 根据主键id查询评论
     * @param id
     * @return
     */
    CommentCombination findCommentById(Integer id);
}
