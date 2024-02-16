package icu.samnyan.aqua.sega.maimai2.model.userdata;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "Maimai2MapEncountNpc")
@Table(name = "maimai2_user_npc_encount")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapEncountNpc implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    private int npcId;
    private int musicId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "extend_id")
    private UserExtend userExtend;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetail user;
}
