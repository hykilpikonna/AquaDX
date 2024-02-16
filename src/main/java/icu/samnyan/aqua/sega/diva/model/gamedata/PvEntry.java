package icu.samnyan.aqua.sega.diva.model.gamedata;

import icu.samnyan.aqua.sega.diva.model.common.Difficulty;
import icu.samnyan.aqua.sega.diva.model.common.Edition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "DivaPvEntry")
@Table(name = "diva_pv_entry")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PvEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int pvId;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    private int version;

    @Enumerated(EnumType.STRING)
    private Edition edition;

    private LocalDateTime demoStart;

    private LocalDateTime demoEnd;

    private LocalDateTime playableStart;

    private LocalDateTime playableEnd;

    public PvEntry(int pvId, Difficulty difficulty, int version, Edition edition, LocalDateTime demoStart, LocalDateTime demoEnd, LocalDateTime playableStart, LocalDateTime playableEnd) {
        this.pvId = pvId;
        this.difficulty = difficulty;
        this.version = version;
        this.edition = edition;
        this.demoStart = demoStart;
        this.demoEnd = demoEnd;
        this.playableStart = playableStart;
        this.playableEnd = playableEnd;
    }
}
