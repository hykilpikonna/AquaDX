package icu.samnyan.aqua.sega.maimai2.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    public enum Mai2ItemKind {
        plate(1),
        title(2),
        icon(3),
        musicUnlock(5),
        musicMasterUnlock(6),
        musicRemasterUnlock(7),
        musicStrongUnlock(8),
        chara(9),
        partner(10),
        frame(11),
        ticket(12);

        public final int value;

        Mai2ItemKind(int value) {
            this.value = value;
        }

        public static final Map<Integer, Mai2ItemKind> ALL = Arrays.stream(Mai2ItemKind.class.getEnumConstants())
            .map(k -> Map.entry(k.value, k)).collect(HashMap::new, (m, v) -> m.put(v.getKey(), v.getValue()), Map::putAll);
    }
}
