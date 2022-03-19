package icu.samnyan.aqua.sega.chusan.model.gamedata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChusanMusic")
@Table(name = "chusan_music")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Music implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int musicId;

    private String name;

    private String sortName;

    private String artistName;

    private Genre genre;

    private String releaseVersion;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "music")
    @MapKey(name = "diff")
    private Map<Integer, Level> levels;

}
