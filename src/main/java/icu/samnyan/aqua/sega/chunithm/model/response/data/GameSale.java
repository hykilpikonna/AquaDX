package icu.samnyan.aqua.sega.chunithm.model.response.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameSale {

    private int orderId;

    private int type;

    private int id;
    // should be float number??
    private int rate;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
