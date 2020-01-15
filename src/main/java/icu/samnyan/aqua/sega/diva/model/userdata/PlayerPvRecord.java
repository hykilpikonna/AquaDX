package icu.samnyan.aqua.sega.diva.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import icu.samnyan.aqua.sega.diva.model.common.ChallengeKind;
import icu.samnyan.aqua.sega.diva.model.common.ClearResult;
import icu.samnyan.aqua.sega.diva.model.common.Difficulty;
import icu.samnyan.aqua.sega.diva.model.common.Edition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "DivaPlayerPvRecord")
@Table(name = "diva_player_pv_record", uniqueConstraints = {@UniqueConstraint(columnNames = {"pd_id", "pv_id", "edition", "difficulty"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerPvRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @ManyToOne
    @JoinColumn(name = "pd_id")
    @JsonIgnore
    private PlayerProfile pdId;

    @Column(name = "pv_id")
    private int pvId = -1;

    @Enumerated(EnumType.STRING)
    private Edition edition = Edition.ORIGINAL;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty = Difficulty.NORMAL;

    @Enumerated(EnumType.STRING)
    private ClearResult result = ClearResult.NO_CLEAR;

    private int maxScore = -1;

    private int maxAttain = -1;

    @Enumerated(EnumType.STRING)
    private ChallengeKind challengeKind = ChallengeKind.UNDEFINED;

    private String rgoPurchased = "0,0,0";

    private String rgoPlayed = "0,0,0";

    public PlayerPvRecord(int pvId, Edition edition) {
        this.pvId = pvId;
        this.edition = edition;
    }

    public PlayerPvRecord(PlayerProfile pdId, int pvId, Edition edition, Difficulty difficulty) {
        this.pdId = pdId;
        this.pvId = pvId;
        this.edition = edition;
        this.difficulty = difficulty;
    }
}
