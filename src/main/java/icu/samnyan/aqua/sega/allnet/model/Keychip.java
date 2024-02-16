package icu.samnyan.aqua.sega.allnet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "Keychip")
@Table(name = "allnet_keychips", uniqueConstraints = {@UniqueConstraint(columnNames = {"keychip_id"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Keychip implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private long id;

    private String keychip_id;
}
