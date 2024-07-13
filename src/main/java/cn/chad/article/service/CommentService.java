package cn.chad.article.service;

import cn.chad.article.dao.CommentRepository;
import cn.chad.article.po.Comment;
import com.mongodb.client.result.UpdateResult;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Resource
    private CommentRepository commentRepository;

    @Resource
    private MongoTemplate mongoTemplate;
    //查询所有的评论
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public List<Comment> findAllSorted() {
        return commentRepository.findAll(Sort.by(Sort.Order.asc("articleId")));
    }
    //通过ID查询评论
    public Optional<Comment> findById(String id) {
        return commentRepository.findById(id);
    }
    //新增评论
    public Comment save(Comment Comment) {
        return commentRepository.save(Comment);
    }
    //通过上级评论ID来查找回复评论
    public List<Comment> findByParentId(String parentId) {
        return commentRepository.findByParentId(parentId);
    }

    public List<Comment> findByParentIdWithPagination(String parentId, Integer current, Integer size) {
        Query query = new Query().addCriteria(Criteria.where("parentId").is(parentId));
        List<Comment> list = mongoTemplate.find(query, Comment.class);
        return list.stream()
                .skip((long) (current - 1) * size)
                .limit(size)
                .collect(Collectors.toList());
    }

    public UpdateResult updateLikeNum(String id, int increment) {
        Query query = Query.query(Criteria.where("id").is(id));
        Update update = new Update().inc("likeNum", increment);
        return mongoTemplate.updateFirst(query, update, Comment.class);
    }
    //删除评论
    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }
}
