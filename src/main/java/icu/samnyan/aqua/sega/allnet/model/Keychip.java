package icu.samnyan.aqua.sega.allnet.model;

import javax.persistence.*;

@Entity(name = "Keychip")
@Table(name = "allnet_keychips", uniqueConstraints = {@UniqueConstraint(columnNames = {"keychip_id"})})

public class Keychip {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String keychip_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
