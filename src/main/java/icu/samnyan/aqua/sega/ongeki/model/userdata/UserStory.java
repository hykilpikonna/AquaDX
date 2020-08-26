package icu.samnyan.aqua.sega.ongeki.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "OngekiUserStory")
@Table(name = "ongeki_user_story")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private int storyId;

    private int lastChapterId;

    private int jewelCount;

    private int lastPlayMusicId;

    private int lastPlayMusicCategory;

    private int lastPlayMusicLevel;

    public UserStory(UserData userData) {
        this.user = userData;
    }
}
