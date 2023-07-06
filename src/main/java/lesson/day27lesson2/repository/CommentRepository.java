package lesson.day27lesson2.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import lesson.day27lesson2.model.Comment;

@Repository
public class CommentRepository {

    private static final String A_GID = "gid";
    private static final String C_COMMENT = "comment";
    
    @Autowired
    private MongoTemplate template;

    // db.comment.find(
    // {gid: 20437}
    // )
    // .sort({rating: -1})
    // .limit(5)
    public List<Document> getTopCommentsByGID(int gid, int limit){
        Criteria criteria = Criteria.where(A_GID).is(gid);
        Query query = Query.query(criteria).limit(limit).with(Sort.by(Direction.DESC, "rating"));
        List<Document> result = template.find(query, Document.class, C_COMMENT);
        return result;
    }

    // db.comment.insert(
    //     {
    //         user: 'username',
    //         rating: 10,
    //         c_text: 'comments',
    //         gid: 13
    //     }
    // )
    public void insertComment(Comment comment){
        Document doc = new Document();
        doc.put("user", comment.getName());
        doc.put("rating", comment.getRating());
        doc.put("c_text", comment.getComments());
        doc.put("gid", comment.getGid());
        Document result = template.insert(doc, C_COMMENT);
    }

}
