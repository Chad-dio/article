package cn.chad.article.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;

// 标明当前实体类是一个文档，对应的MongoDB的集合名
@Document(collection = "Comment")
@Data
// 复合索引
//@CompoundIndex(def = "{'user_id': 1,'article_id': -1}")
public class Comment implements Serializable {
    // 写不写都行
    @Id
    private String id;

    private String content;

    private LocalDateTime publishTime;

    @Field("user_id")
    @Indexed(background = true)
    private String userId;

    @Field("create_time")
    private LocalDateTime createTime;

    @Field("like_num")
    private Integer likeNum;

    @Field("reply_num")
    private Integer replyNum;

    private String state;

    @Field("parent_id")
    private String parentId;

    @Field("article_id")
    private String articleId;

}