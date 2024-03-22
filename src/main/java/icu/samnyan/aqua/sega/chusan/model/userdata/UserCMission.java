package icu.samnyan.aqua.sega.chusan.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "ChusanUserCMission")
@Table(name = "chusan_user_cmission")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCMission {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    @Column(name = "mission_id")
    private int missionId;

    @Column(name = "point")
    private int point;
}
