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
@Entity(name = "ChuniUserItem")
@Table(name = "chuni_user_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"itemKind", "itemId", "stock", "isValid"})
public class UserItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    // Kind ,Type
    private int itemKind;

    private int itemId;

    private int stock;

    @JsonProperty("isValid")
    private boolean isValid;

    public UserItem(UserData userData) {
        user = userData;
    }
}
