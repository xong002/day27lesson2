package lesson.day27lesson2.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepository {
    
    @Autowired
    private MongoTemplate template;

    private static final String A_NAME = "name";
    private static final String C_BGG = "game";

    // db.game.find(
    // {name: {$regex: "catan", $options: "i"}}
    // )
    public List<Document> findGameByName(String name){
        // Criteria criteria = Criteria.where(A_NAME).regex(name, "i");
        Criteria criteria = Criteria.where(A_NAME).is(name);
        Query query = Query.query(criteria);
        List<Document> result = template.find(query, Document.class, C_BGG);
        return result;
    }

}
