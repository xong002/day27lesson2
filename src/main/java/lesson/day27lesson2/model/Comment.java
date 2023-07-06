package lesson.day27lesson2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private String name;
    private int rating;
    private String comments;
    private int gid;
}
