package com.yoyo.service.impl;

import com.yoyo.mapper.BlogMapper;
import com.yoyo.mapper.CommentMapper;
import com.yoyo.mapper.TagMapper;
import com.yoyo.mapper.TypeMapper;
import com.yoyo.pojo.Blog;
import com.yoyo.pojo.Tag;
import com.yoyo.pojo.Type;
import com.yoyo.service.BlogService;
import com.yoyo.utils.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    BlogMapper blogMapper;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    TagMapper tagMapper;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    TypeMapper typeMapper;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    CommentMapper commentMapper;
    @Override
    public List<Blog> findBlogAll() {
        return blogMapper.findBlogAll();
    }

    @Override
    @Transactional
    public int addBlog(Blog blog) {
        blog.setDate(new Date());
        change(blog,1);
        return blogMapper.addBlog(blog);
    }

    @Override
    public List<Blog> findBlogByCondition(Map<String, Object> map) {
        return blogMapper.findBlogByCondition(map);
    }

    @Override
    public List<Blog> findBlogByPage(Integer begin, Integer end) {
        return blogMapper.findBlogByPage(begin,end);
    }

    @Override
    public Blog findBlogById(Integer blogId) {
        return blogMapper.findBlogById(blogId);
    }

    @Transactional
    @Override
    public int deleteBlog(Integer id) {
        change(findBlogById(id),-1);
        return blogMapper.deleteBlogById(id);
    }

    @Override
    public int updateBlog(Blog blog) {
        blog.setViews(null);
        return blogMapper.updateBlog(blog);
    }

    @Override
    public Integer findBlogIdByName(String name) {
        return blogMapper.findBlogIdByName(name);
    }

    @Override
    public List<Blog> findBlogAllVisible() {
        return blogMapper.findBlogAllVisible();
    }

    @Override
    public List<Blog> findBlogByConditionVisible(Map<String, Object> map) {
        map.put("state",1);
        return blogMapper.findBlogByCondition(map);
    }


    /**
     * 提取封装的一个私有方法
     * 作用：更新博客的同时要对分类和标签中的博客数量属性进行修改，同时删除对应的评论
     * @param blog 要变化的博客
     * @param i 判断是增加还是减少
     */
    private void change(Blog blog,Integer i){
        //处理标签
        Type type = typeMapper.findTypeById(blog.getType());
        type.setNumber(type.getNumber()+i);
        typeMapper.updateType(type);

        Map<String,Object> map1=new HashMap<>();
        List<Tag> tags=tagMapper.findTagByIds(TagUtil.stringTolist(blog.getTags()));
        for (Tag tag:tags){
            tag.setNumber(tag.getNumber()+i);
            tagMapper.updateTag(tag);
        }
        //处理评论变化
        if (i==-1 ){
            String comments=blog.getComments();
            if (comments!=null){
                commentMapper.deleteCommentByIds(TagUtil.stringTolist(comments));
            }
        }
    }
}
