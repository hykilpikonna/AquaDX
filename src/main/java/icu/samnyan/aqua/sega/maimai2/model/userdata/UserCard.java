package icu.samnyan.aqua.sega.maimai2.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "Maimai2UserCard")
@Table(name = "maimai2_user_card")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetail user;

    public int cardId;
    public int cardTypeId;
    public int charaId;
    public int mapId;
    public String startDate;
    public String endDate;

    public UserCard(UserDetail user) {
        this.user = user;
    }
}
