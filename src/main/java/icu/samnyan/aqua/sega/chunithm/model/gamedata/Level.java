package icu.samnyan.aqua.sega.chunithm.model.gamedata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChuniMusicLevel")
@Table(name = "chuni_music_level")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Level implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "music_id")
    private Music music;

    private boolean enable;

    private int level;

    private int levelDecimal;

    private int diff;
}
