package icu.samnyan.aqua.sega.maimai2.model.userdata;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "Maimai2UserActivity")
@Table(name = "maimai2_user_activity")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"kind", "id", "sortNumber", "param1", "param2", "param3", "param4"})
public class UserAct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetail user;

    private int kind;
    
    @JsonProperty("id")
    private int activityId;
    
    private long sortNumber;
    private int param1;
    private int param2;
    private int param3;
    private int param4;

    public UserAct(UserDetail user) {
        this.user = user;
    }
}
