package com.yoyo.controller;

import com.yoyo.pojo.Comment;
import com.yoyo.service.BlogService;
import com.yoyo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author Chester
 */
@Controller
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    BlogService blogService;
    @PostMapping("/comment")
    public String insertComment(Comment comment, HttpSession session){
        if (session.getAttribute("user")==null){
            comment.setIsAdmin(0);
        }else {
            comment.setIsAdmin(1);
        }
        commentService.insertComment(comment);
        return "redirect:/page_blog/"+comment.getBlog();
    }

    @DeleteMapping("/comment")
    public ModelAndView deleteComment(Integer id){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("admin/tip");
        if (commentService.deleteCommentById(id)==1){
            mv.addObject("message","评论删除成功！");
        }else{
            mv.addObject("message","评论删除失败！");
        }
        return mv;
    }
}
