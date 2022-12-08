package icu.samnyan.aqua.sega.maimai2.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "Maimai2UserPrintDetail")
@Table(name = "maimai2_user_print_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPrintDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetail user;
    private long orderId;
    private int printNumber;
    private String printDate;
    private String serialId;
    private int placeId;
    private String clientId;
    private String printerSerialId;
    @ManyToOne
    @JoinColumn(name = "user_card_id")
    private UserCard userCard;
    private int cardRomVersion;
    private boolean isHolograph;
    private boolean printOption1;
    private boolean printOption2;
    private boolean printOption3;
    private boolean printOption4;
    private boolean printOption5;
    private boolean printOption6;
    private boolean printOption7;
    private boolean printOption8;
    private boolean printOption9;
    private boolean printOption10;
    private String created;

    public UserPrintDetail(UserDetail user) {
        this.user = user;
    }
}
