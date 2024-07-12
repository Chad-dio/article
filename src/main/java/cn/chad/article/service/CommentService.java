package cn.chad.article.service;

import cn.chad.article.dao.CommentRepository;
import cn.chad.article.po.Comment;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Resource
    private CommentRepository commentRepository;

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public List<Comment> findAllSorted() {
        return commentRepository.findAll(Sort.by(Sort.Order.asc("articleId")));
    }

    public Optional<Comment> findById(String id) {
        return commentRepository.findById(id);
    }

    public Comment save(Comment Comment) {
        return commentRepository.save(Comment);
    }

    public List<Comment> findByParentId(String parentId) {
        return commentRepository.findByParentId(parentId);
    }

    public List<Comment> findByParentIdWithPagination(String parentId, Integer current, Integer size) {
//        Query query = new Query().addCriteria(Criteria.where("parentId").is(parentId));
//        List<Comment> list = mongoTemplate.find(query, Comment.class);
//        return list.stream()
//                .skip((long) (current - 1) * size)
//                .limit(size)
//                .collect(Collectors.toList());
        return null;
    }

//    public UpdateResult updateLikeNum(String id, int increment) {
//        Query query = Query.query(Criteria.where("id").is(id));
//        Update update = new Update().inc("likeNum", increment);
//        return mongoTemplate.updateFirst(query, update, Comment.class);
//    }

    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }
}
