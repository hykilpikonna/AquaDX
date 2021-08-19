package icu.samnyan.aqua.sega.maimai2.model.userdata;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "Maimai2UserUdemae")
@Table(name = "maimai2_user_udemae")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUdemae implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetail user;

    private int rate;
    private int maxRate;
    private int classValue;
    private int maxClassValue;
    private int totalWinNum;
    private int totalLoseNum;
    private int maxWinNum;
    private int maxLoseNum;
    private int winNum;
    private int loseNum;
    private int npcTotalWinNum;
    private int npcTotalLoseNum;
    private int npcMaxWinNum;
    private int npcMaxLoseNum;
    private int npcWinNum;
    private int npcLoseNum;

    public UserUdemae(UserDetail user) {
        this.user = user;
    }
}
