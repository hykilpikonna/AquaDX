package icu.samnyan.aqua.sega.general.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
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

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // A external id
    @Column(name = "ext_id", unique = true)
    private Long extId;

    // Access Code
    @Column(unique = true)
    private String luid;

    @Column(name = "register_time")
    private LocalDateTime registerTime;

    @Column(name = "access_time")
    private LocalDateTime accessTime;

}
