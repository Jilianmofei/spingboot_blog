package com.yoyo.service.impl;

import com.yoyo.pojo.Comment;
import com.yoyo.pojo.CommentCombination;
import com.yoyo.service.BlogService;
import com.yoyo.service.CommentCombinationService;
import com.yoyo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentCombinationServiceImpl implements CommentCombinationService {
    @Autowired
    CommentService commentService;
    @Autowired
    BlogService blogService;
    @Override
    public int insertComment(Comment comment) {
        comment.setTime(new Date());
        return commentService.insertComment(comment);
    }

    @Override
    public int deleteCommentById(Integer id) {
        return commentService.deleteCommentById(id);
    }

    @Override
    public List<CommentCombination> findCommentByPage(Map<String, Object> map) {
        Object title=map.get("blog");
        if (title!=null&&title!=""){
            map.replace("blog",blogService.findBlogIdByName(String.valueOf(title)));
        }
        List<Comment> comments=commentService.findCommentByPage(map);
        List<CommentCombination> commentCombinations=new ArrayList<>();
        for (Comment comment:comments){
            CommentCombination commentCombination=new CommentCombination(comment,blogService.findBlogById(comment.getBlog()));
            commentCombinations.add(commentCombination);
        }
        return commentCombinations;
    }

    @Override
    public CommentCombination findCommentById(Integer id) {
        Comment comment=commentService.findCommentById(id);
        CommentCombination commentCombination=new CommentCombination(comment,blogService.findBlogById(comment.getBlog()));

        return commentCombination;
    }
}
