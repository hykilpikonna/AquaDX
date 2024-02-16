package icu.samnyan.aqua.sega.chunithm.model.gamedata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChuniGameCharge")
@Table(name = "chuni_game_charge")
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

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int salePrice;

    private LocalDateTime saleStartDate;

    private LocalDateTime saleEndDate;

}
