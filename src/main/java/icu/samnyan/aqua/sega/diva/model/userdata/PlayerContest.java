package icu.samnyan.aqua.sega.diva.model.userdata;

import icu.samnyan.aqua.sega.diva.model.common.ContestBorder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "DivaPlayerContest")
@Table(name = "diva_player_contest", uniqueConstraints = {@UniqueConstraint(columnNames = {"pd_id", "contest_id"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerContest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "pd_id")
    private PlayerProfile pdId;

    @Column(name = "contest_id")
    private int contestId;

    private int startCount = 0;

    @Enumerated(EnumType.STRING)
    private ContestBorder resultRank = ContestBorder.NONE;

    private int bestValue = -1;

    private int flag = -1;

    private LocalDateTime lastUpdateTime = LocalDateTime.now();

    public PlayerContest(PlayerProfile pdId, int contestId) {
        this.pdId = pdId;
        this.contestId = contestId;
    }
}
