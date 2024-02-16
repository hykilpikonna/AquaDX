package icu.samnyan.aqua.sega.maimai.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * For userGradeStatus, userRecentRatingList
 *
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "MaimaiUserGeneralData")
@Table(name = "maimai_user_general_data")
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