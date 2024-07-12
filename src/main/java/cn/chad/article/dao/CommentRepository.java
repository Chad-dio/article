package cn.chad.article.dao;

import cn.chad.article.po.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findByArticleidAndParentid(String articleid, String parentid);
}
