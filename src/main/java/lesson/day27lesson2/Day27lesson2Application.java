package lesson.day27lesson2;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lesson.day27lesson2.repository.CommentRepository;
import lesson.day27lesson2.repository.GameRepository;

@SpringBootApplication
public class Day27lesson2Application implements CommandLineRunner{

	@Autowired
	private CommentRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(Day27lesson2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Document> result = repo.getTopCommentsByGID(20437, 5);
		for(Document d : result){
			System.out.println(d);
		}
	}

}
