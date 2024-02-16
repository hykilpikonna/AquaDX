package icu.samnyan.aqua.sega.chusan.model.gamedata;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChusanMapIcon")
@Table(name = "chusan_mapicon")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapIcon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private long id;

    private String name;
}
