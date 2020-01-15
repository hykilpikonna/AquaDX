package icu.samnyan.aqua.sega.chunithm.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChuniUserCharge")
@Table(name = "chuni_user_charge")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"chargeId", "stock", "purchaseDate", "validDate", "param1", "param2", "paramDate"})
public class UserCharge implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private int chargeId;

    private int stock;

    private LocalDateTime purchaseDate;

    private LocalDateTime validDate;

    private int param1;

    private int param2;

    private LocalDateTime paramDate;

    public UserCharge(UserData user) {
        this.user = user;
    }
}
