package icu.samnyan.aqua.sega.diva.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "DivaPlayerInventory")
@Table(name = "diva_player_inventory", uniqueConstraints = {@UniqueConstraint(columnNames = {"pd_id", "value", "type"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerInventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pd_id")
    @JsonIgnore
    private PlayerProfile pdId;

    private String value;

    // Type: (1: Skin, 2: Call sign plate, 3: Call sign)
    private String type;
}
