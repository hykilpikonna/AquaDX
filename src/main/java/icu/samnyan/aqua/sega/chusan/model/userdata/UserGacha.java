package icu.samnyan.aqua.sega.chusan.model.userdata;

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
@Entity(name = "ChusanUserGacha")
@Table(name = "chusan_user_gacha", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "gacha_id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGacha implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    @Column(name = "gacha_id")
    private int gachaId;

    private int totalGachaCnt;

    private int ceilingGachaCnt;

    private int dailyGachaCnt;

    private int fiveGachaCnt;

    private int elevenGachaCnt;

    private LocalDateTime dailyGachaDate;

    public UserGacha(UserData user) {
        this.user = user;
    }
}
