package icu.samnyan.aqua.sega.diva.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "DivaPlayerPvCustomize")
@Table(name = "diva_player_pv_customize", uniqueConstraints = {@UniqueConstraint(columnNames = {"pd_id", "pv_id"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerPvCustomize implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @ManyToOne
    @JoinColumn(name = "pd_id")
    private PlayerProfile pdId;

    @Column(name = "pv_id")
    private int pvId = -1;

    private String module = "-999,-999,-999";

    private String customize = "-999,-999,-999,-999,-999,-999,-999,-999,-999,-999,-999,-999";

    private String customizeFlag = "-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1";

    private int skin = -1;

    private int buttonSe = -1;

    private int slideSe = -1;

    private int chainSlideSe = -1;

    private int sliderTouchSe = -1;

    public PlayerPvCustomize(PlayerProfile pdId, int pvId) {
        this.pdId = pdId;
        this.pvId = pvId;
    }
}
