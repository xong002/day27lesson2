package lesson.day27lesson2.service;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lesson.day27lesson2.model.Game;
import lesson.day27lesson2.repository.GameRepository;

@Service
public class GameService {
    
    @Autowired
    private GameRepository repo;

    public Optional<Game> findGameByName(String name){
        List<Document> result = repo.findGameByName(name);
        if(!result.isEmpty()){
            Document d = result.get(0);
            //refactor to Util static method
            Game game = new Game(
                d.getInteger("gid"),
                d.getString("name"),
                d.getInteger("year"),
                d.getInteger("ranking"),
                d.getInteger("users_rated"),
                d.getString("url"),
                d.getString("image")
            );
            return Optional.of(game);
        }
        return Optional.empty();
    }
}
