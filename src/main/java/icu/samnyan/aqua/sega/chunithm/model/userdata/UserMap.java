package icu.samnyan.aqua.sega.chunithm.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChuniUserMap")
@Table(name = "chuni_user_map")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "mapId",
        "position",
        "isClear",
        "areaId",
        "routeNumber",
        "eventId",
        "rate",
        "statusCount",
        "isValid"
})
public class UserMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private int mapId;

    private int position;

    @JsonProperty("isClear")
    private boolean isClear;

    private int areaId;

    private int routeNumber;

    private int eventId;

    private int rate;

    private int statusCount;

    @JsonProperty("isValid")
    private boolean isValid;

    public UserMap(UserData userData) {
        user = userData;
    }
}
