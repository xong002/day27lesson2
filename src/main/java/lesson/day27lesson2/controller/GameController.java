package lesson.day27lesson2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lesson.day27lesson2.model.Comment;
import lesson.day27lesson2.model.Game;
import lesson.day27lesson2.service.CommentService;
import lesson.day27lesson2.service.GameService;

@Controller
@RequestMapping("/")
public class GameController {
    
    @Autowired
    private GameService svc;

    @Autowired
    private CommentService csvc;

    @GetMapping("/game/search")
    public String findGameByName(@RequestParam String gameName, Model model){
        Optional<Game> result = svc.findGameByName(gameName);
        if (result.isPresent()){
            model.addAttribute("game", result.get());
            model.addAttribute("comments", new Comment());
            Integer[] ratingRange = new Integer[]{1,2,3,4,5,6,7,8,9,10};
            model.addAttribute("ratingRange", ratingRange);
            Optional<List<Comment>> commentList = csvc.getTopCommentsByGID(result.get().gid(), 5);
            model.addAttribute("commentList", commentList.get()); //code for if list empty
            return "result";
        }
        return "notfound";
    }

    @PostMapping("/comments/submit")
    public String submitComment(@ModelAttribute Comment comments){
        csvc.insertComment(comments);
        return "submitted";
    }
}
