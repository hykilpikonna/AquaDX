package icu.samnyan.aqua.sega.general.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import icu.samnyan.aqua.net.db.AquaNetUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "SegaCard")
@Table(name = "sega_card")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    // A external id
    @Column(name = "ext_id", unique = true)
    @JsonIgnore // Sensitive information
    private Long extId;

    // Access Code
    @Column(unique = true)
    private String luid;

    @Column(name = "register_time")
    private LocalDateTime registerTime;

    @Column(name = "access_time")
    private LocalDateTime accessTime;

    // Defines the AquaNet user that this card is bound to
    @ManyToOne
    @JoinColumn(name = "net_user_id")
    @JsonIgnore
    private AquaNetUser aquaUser;
}
