package icu.samnyan.aqua.sega.diva.model.userdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "DivaPlayerScreenShot")
@Table(name = "diva_player_screen_shot")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerScreenShot {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "pd_id")
    private PlayerProfile pdId;

    private int pvId;

    private String fileName;

    private String moduleList;

    private String customizeList;

    public PlayerScreenShot(PlayerProfile pdId, String fileName, int pvId, String moduleList, String customizeList) {
        this.pdId = pdId;
        this.fileName = fileName;
        this.pvId = pvId;
        this.moduleList = moduleList;
        this.customizeList = customizeList;
    }
}
