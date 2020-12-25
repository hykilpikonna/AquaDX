package icu.samnyan.aqua.sega.maimai.model.userdata;

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
@Entity(name = "MaimaiUserWebOption")
@Table(name = "maimai_user_web_option")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWebOption implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    @JsonProperty("isNetMember")
    private boolean isNetMember = true;

    private int dispRate;

    private int dispJudgeStyle;

    private int dispRank;

    private int dispHomeRanker;

    private int dispTotalLv;

    public UserWebOption(UserData user) {
        this.user = user;
    }
}
