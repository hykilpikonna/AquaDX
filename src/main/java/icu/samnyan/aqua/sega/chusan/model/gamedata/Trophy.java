package icu.samnyan.aqua.sega.chusan.model.gamedata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChusanTrophy")
@Table(name = "chusan_trophy")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trophy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private long id;

    private String name;
}
