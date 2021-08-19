package icu.samnyan.aqua.sega.maimai2.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "Maimai2UserCharge")
@Table(name = "maimai2_user_charge", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "charge_id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"chargeId", "stock", "purchaseDate", "validDate"})
public class UserCharge implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetail user;

    @Column(name = "charge_id")
    private int chargeId;

    private int stock;

    private String purchaseDate;

    private String validDate;

    public UserCharge(UserDetail user) {
        this.user = user;
    }
}
