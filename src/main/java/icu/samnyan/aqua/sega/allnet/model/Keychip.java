package icu.samnyan.aqua.sega.allnet.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
