package icu.samnyan.aqua.sega.diva.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import icu.samnyan.aqua.sega.diva.model.common.StartMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "DivaGameSession")
@Table(name = "diva_game_session")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    private int acceptId;

    @OneToOne
    @JoinColumn(name = "pd_id", unique = true)
    private PlayerProfile pdId;

    @Enumerated(EnumType.STRING)
    private StartMode startMode;

    private LocalDateTime startTime;

    private LocalDateTime lastUpdateTime;

    private Integer stageIndex;

    private Integer lastPvId;

    private Integer levelNumber;

    private Integer levelExp;

    private Integer oldLevelNumber;

    private Integer oldLevelExp;

    private Integer vp;

    public GameSession(int acceptId, PlayerProfile pdId, StartMode startMode, LocalDateTime startTime, LocalDateTime lastUpdateTime, Integer stageIndex, Integer lastPvId, Integer levelNumber, Integer levelExp, Integer oldLevelNumber, Integer oldLevelExp, Integer vp) {
        this.acceptId = acceptId;
        this.pdId = pdId;
        this.startMode = startMode;
        this.startTime = startTime;
        this.lastUpdateTime = lastUpdateTime;
        this.stageIndex = stageIndex;
        this.lastPvId = lastPvId;
        this.levelNumber = levelNumber;
        this.levelExp = levelExp;
        this.oldLevelNumber = oldLevelNumber;
        this.oldLevelExp = oldLevelExp;
        this.vp = vp;
    }
}
