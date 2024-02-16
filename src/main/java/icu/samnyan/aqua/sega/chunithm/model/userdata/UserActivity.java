package icu.samnyan.aqua.sega.chunithm.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChuniUserActivity")
@Table(name = "chuni_user_activity", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "kind", "activity_id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"kind", "id", "sortNumber", "param1", "param2", "param3", "param4"})
public class UserActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private int kind;

    @JsonProperty("id")
    @Column(name = "activity_id")
    private int activityId;

    private int sortNumber;

    private int param1;

    private int param2;

    private int param3;

    private int param4;

    public UserActivity(UserData userData) {
        user = userData;
    }
}
