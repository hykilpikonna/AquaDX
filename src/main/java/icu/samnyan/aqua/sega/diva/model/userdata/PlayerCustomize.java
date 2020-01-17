package icu.samnyan.aqua.sega.diva.model.userdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "DivaPlayerCustomize")
@Table(name = "diva_player_customize", uniqueConstraints = {@UniqueConstraint(columnNames = {"pd_id", "customize_id"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerCustomize implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "pd_id")
    private PlayerProfile pdId;

    @Column(name = "customize_id")
    private int customizeId;

    public PlayerCustomize(PlayerProfile profile, int customizeId) {
        this.pdId = profile;
        this.customizeId = customizeId;
    }
}
