package icu.samnyan.aqua.sega.maimai2.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This is for storing some data only use in aqua
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "Maimai2UserGeneralData")
@Table(name = "maimai2_user_general_data")
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
    private UserDetail user;

    private String propertyKey;

    @Column(columnDefinition = "TEXT")
    private String propertyValue;

    public UserGeneralData(UserDetail userData, String key) {
        this.user = userData;
        this.propertyKey = key;
        this.propertyValue = "";
    }
}
