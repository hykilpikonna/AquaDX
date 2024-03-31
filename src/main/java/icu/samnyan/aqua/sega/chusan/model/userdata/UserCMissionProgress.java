package icu.samnyan.aqua.sega.chusan.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "ChusanUserCMissionProgress")
@Table(name = "chusan_user_cmission_progress", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "mission_id", "order"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCMissionProgress {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Chu3UserData user;

    @Column(name = "mission_id")
    private int missionId;

    @Column(name = "`order`")
    private int order;

    private int stage;

    private int progress;
}
