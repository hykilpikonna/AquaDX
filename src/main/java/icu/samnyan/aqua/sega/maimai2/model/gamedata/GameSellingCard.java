package icu.samnyan.aqua.sega.maimai2.model.gamedata;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "Maimai2GameSellingCard")
@Table(name = "maimai2_game_selling_card")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameSellingCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    private int cardId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime noticeStartDate;
    private LocalDateTime noticeEndDate;
}
