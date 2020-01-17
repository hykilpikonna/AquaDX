package icu.samnyan.aqua.sega.diva.model.gamedata;

import icu.samnyan.aqua.sega.diva.model.common.Difficulty;
import icu.samnyan.aqua.sega.diva.model.common.FestaKind;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "DivaFesta")
@Table(name = "diva_festa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Festa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = -1;

    private boolean enable = true;

    private String name = "xxx";

    @Enumerated(EnumType.STRING)
    private FestaKind kind = FestaKind.PINK_FESTA;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty = Difficulty.UNDEFINED;

    private String pvList = "ALL";

    private String attributes = "7FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";

    private Integer addVP = 0;

    private Integer vpMultiplier = 1;

    private LocalDateTime start = LocalDateTime.of(2005, 1, 1, 0, 0);

    private LocalDateTime end = LocalDateTime.of(2005, 1, 1, 0, 0);

    private LocalDateTime createDate = LocalDateTime.of(2005, 1, 1, 0, 0);
}
