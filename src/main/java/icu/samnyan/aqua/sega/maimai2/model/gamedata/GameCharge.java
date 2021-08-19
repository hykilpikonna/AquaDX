package icu.samnyan.aqua.sega.maimai2.model.gamedata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "Maimai2GameCharge")
@Table(name = "maimai2_game_charge")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameCharge implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    private int orderId;

    @Column(unique = true)
    private int chargeId;

    private int price;

    private String startDate;

    private String endDate;

}
