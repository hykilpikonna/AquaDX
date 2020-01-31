package icu.samnyan.aqua.sega.chunithm.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChuniUserCourse")
@Table(name = "chuni_user_course", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "course_id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCourse {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    @Column(name = "course_id")
    private int courseId;

    private int classId;

    private int playCount;

    private int scoreMax;

    @JsonProperty("isFullCombo")
    private boolean isFullCombo;

    @JsonProperty("isAllJustice")
    private boolean isAllJustice;

    @JsonProperty("isSuccess")
    private boolean isSuccess;

    private int scoreRank;

    private int eventId;

    private LocalDateTime lastPlayDate;

    private int param1;

    private int param2;

    private int param3;

    private int param4;

    @JsonProperty("isClear")
    private boolean isClear;

    public UserCourse(UserData userData) {
        user = userData;
    }

    public UserCourse(int classId) {
        this.classId = classId;
    }
}
