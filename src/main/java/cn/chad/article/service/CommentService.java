package cn.chad.article.service;

import cn.chad.article.dao.CommentRepository;
import cn.chad.article.po.Comment;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Resource
    private CommentRepository commentRepository;

    //添加评论
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }
    //通过ID删除评论
    public void delCommentById(String id){
        commentRepository.deleteById(id);
    }
    //删除评论
    public void delComment(Comment comment){
        commentRepository.delete(comment);
    }
}
