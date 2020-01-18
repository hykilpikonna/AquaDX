package icu.samnyan.aqua.sega.general.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ServerPropertyEntry")
@Table(name = "property")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String propertyKey;

    private String propertyValue;

    public PropertyEntry(String propertyKey, String propertyValue) {
        this.propertyKey = propertyKey;
        this.propertyValue = propertyValue;
    }
    public PropertyEntry(String propertyKey) {
        this.propertyKey = propertyKey;
    }
}
