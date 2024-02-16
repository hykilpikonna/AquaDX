package icu.samnyan.aqua.sega.diva.model.userdata;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "DivaPlayerModule")
@Table(name = "diva_player_module", uniqueConstraints = {@UniqueConstraint(columnNames = {"pd_id", "module_id"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerModule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "pd_id")
    private PlayerProfile pdId;

    @Column(name = "module_id")
    private int moduleId;

    public PlayerModule(PlayerProfile profile, int moduleId) {
        this.pdId = profile;
        this.moduleId = moduleId;
    }
}
