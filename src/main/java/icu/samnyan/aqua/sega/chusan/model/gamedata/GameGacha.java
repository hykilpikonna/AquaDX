package icu.samnyan.aqua.sega.chusan.model.gamedata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
@Entity(name = "ChusanGameGacha")
@Table(name = "chusan_game_gacha")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameGacha implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    private int gachaId;
    private String gachaName;
    private int type;
    private int kind; // 0
    @JsonProperty("isCeiling")
    private boolean isCeiling;
    private int ceilingCnt;
    private int changeRateCnt1;
    private int changeRateCnt2;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime noticeStartDate;
    private LocalDateTime noticeEndDate;
}
