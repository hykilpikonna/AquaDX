package icu.samnyan.aqua.sega.chusan.model.gamedata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChusanGameCharge")
@Table(name = "chusan_game_charge")
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
