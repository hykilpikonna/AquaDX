package icu.samnyan.aqua.sega.chusan.model.userdata;

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
@Entity(name = "ChusanUserItem")
@Table(name = "chusan_user_item", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "item_id", "item_kind"})})
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
    @Column(name = "item_kind")
    private int itemKind;

    @Column(name = "item_id")
    private int itemId;

    private int stock = 1;

    @JsonProperty("isValid")
    private boolean isValid = true;

    public UserItem(UserData userData) {
        user = userData;
    }
}
