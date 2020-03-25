package com.shen.ssmschoolshop.service;


import com.shen.ssmschoolshop.entity.Comment;
import com.shen.ssmschoolshop.entity.CommentExample;

import java.util.List;

public interface CommentService {
    public void insertSelective(Comment comment);

    public List<Comment> selectByExample(CommentExample commentExample);
}
