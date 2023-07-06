package lesson.day27lesson2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lesson.day27lesson2.model.Comment;
import lesson.day27lesson2.repository.CommentRepository;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository repo;

    public Optional<List<Comment>> getTopCommentsByGID(int gid, int limit){
        List<Document> result = repo.getTopCommentsByGID(gid, limit);
        if(!result.isEmpty()) {
            List<Comment> commentList = new ArrayList<>();
            for(Document d : result){
                Comment comment = new Comment(
                    d.getString("user"),
                    d.getInteger("rating"),
                    d.getString("c_text"),
                    d.getInteger("gid")
                );
                commentList.add(comment);
            }
            return Optional.of(commentList);
        }
        return Optional.empty();
    }

    public void insertComment(Comment comment){
        repo.insertComment(comment);
    }
}
