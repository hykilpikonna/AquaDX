package icu.samnyan.aqua.sega.ongeki.model.gamedata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import icu.samnyan.aqua.sega.ongeki.model.common.GpProductID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "OngekiGamePoint")
@Table(name = "ongeki_game_point", uniqueConstraints = {@UniqueConstraint(columnNames = {"type"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GamePoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @Enumerated(EnumType.ORDINAL)
    private GpProductID type;

    private int cost;

    private String startDate = "2000-01-01 05:00:00.0";

    private String endDate = "2099-01-01 05:00:00.0";

    public GamePoint(GpProductID type, int cost) {
        this.type = type;
        this.cost = cost;
    }
}
