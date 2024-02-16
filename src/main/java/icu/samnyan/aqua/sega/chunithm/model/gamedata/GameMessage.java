package icu.samnyan.aqua.sega.chunithm.model.gamedata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChuniGameMessage")
@Table(name = "chuni_game_message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    private int type;

    private String message;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
