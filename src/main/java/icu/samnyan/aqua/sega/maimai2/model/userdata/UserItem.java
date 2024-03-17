package icu.samnyan.aqua.sega.maimai2.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import jakarta.persistence.*;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "Maimai2UserItem")
@Table(name = "maimai2_user_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetail user;

    private int itemKind;
    private int itemId;
    private int stock;
    private boolean isValid;

    public UserItem(UserDetail user) {
        this.user = user;
    }

    public static final int KIND_NAMEPLATE = 1;
    public static final int KIND_TITLE = 2;
    public static final int KIND_ICON = 3;
    public static final int KIND_MUSIC_UNLOCK = 5;
    public static final int KIND_MUSIC_MASTER_UNLOCK = 6;
    public static final int KIND_MUSIC_REMASTER_UNLOCK = 7;
    public static final int KIND_MUSIC_STRONG_UNLOCK = 8;
    public static final int KIND_CHARACTER = 9;
    public static final int KIND_PARTNER = 10;
    public static final int KIND_FRAME = 11;
    public static final int KIND_TICKETS = 12;
}
