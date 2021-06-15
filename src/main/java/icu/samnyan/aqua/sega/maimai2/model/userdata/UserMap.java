package icu.samnyan.aqua.sega.maimai2.model.userdata;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "Maimai2UserMap")
@Table(name = "maimai2_user_map")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"mapId", "distance", "isLock", "isClear", "isComplete"})
public class UserMap implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetail user;
    
    private int mapId;
    private int distance;
    @JsonProperty("isLock")
    private boolean isLock;
    @JsonProperty("isClear")
    private boolean isClear;
    @JsonProperty("isComplete")
    private boolean isComplete;

    public UserMap(UserDetail user) {
        this.user = user;
    }
}
