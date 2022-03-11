package icu.samnyan.aqua.sega.chusan.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This is for storing the other data that doesn't need to save it in a separate table
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChusanUserGeneralData")
@Table(name = "chusan_user_general_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGeneralData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private String propertyKey;

    @Column(columnDefinition = "TEXT")
    private String propertyValue;

    public UserGeneralData(UserData userData, String key) {
        this.user = userData;
        this.propertyKey = key;
        this.propertyValue = "";
    }
}
