package icu.samnyan.aqua.sega.chusan.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChusanUserMapArea")
@Table(name = "chusan_user_map_area", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "map_area_id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "mapAreaId",
        "position",
        "isClear",
        "rate",
        "statusCount",
        "remainGridCount",
        "isLocked"
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
    private Chu3UserData user;

    @Column(name = "map_area_id")
    private int mapAreaId;

    private int position;

    @JsonProperty("isClear")
    private boolean isClear;

    private int rate;

    private int statusCount;

    private int remainGridCount;

    @JsonProperty("isLocked")
    private boolean isLocked;

    public UserMap(Chu3UserData userData) {
        user = userData;
    }
}
