package icu.samnyan.aqua.sega.maimai2.model.userdata;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import icu.samnyan.aqua.sega.maimai2.util.IntegerListConverter;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "Maimai2UserFavorite")
@Table(name = "maimai2_user_favorite")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFavorite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetail user;

    @JsonProperty("id")
    private long favUserId;
    private int itemKind;

    @Convert(converter = IntegerListConverter.class)
    private List<Integer> itemIdList;

    public UserFavorite(UserDetail user) {
        this.user = user;
    }
}
