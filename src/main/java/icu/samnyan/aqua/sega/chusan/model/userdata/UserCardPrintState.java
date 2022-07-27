package icu.samnyan.aqua.sega.chusan.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChusanUserCardPrintState")
@Table(name = "chusan_user_print_state")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCardPrintState implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("orderId")
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private boolean hasCompleted;
    private LocalDateTime limitDate;
    private int placeId;
    private int cardId;
    private int gachaId;

    public UserCardPrintState(UserData user) {
        this.user = user;
    }
}
