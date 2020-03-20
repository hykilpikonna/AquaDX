package icu.samnyan.aqua.sega.ongeki.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "OngekiUserChapter")
@Table(name = "ongeki_user_chapter")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserChapter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private int chapterId;

    private int jewelCount;

    private int lastPlayMusicCategory;

    private int lastPlayMusicId;

    @JsonProperty("isStoryWatched")
    private boolean isStoryWatched;

    @JsonProperty("isClear")
    private boolean isClear;

    private int skipTiming1;

    private int skipTiming2;

    public UserChapter(UserData userData) {
        this.user = userData;
    }
}
