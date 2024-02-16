package icu.samnyan.aqua.sega.maimai.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "MaimaiUserBoss")
@Table(name = "maimai_user_boss")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBoss implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private long pandoraFlagList0;

    private long pandoraFlagList1;

    private long pandoraFlagList2;

    private long pandoraFlagList3;

    private long pandoraFlagList4;

    private long pandoraFlagList5;

    private long pandoraFlagList6;

    private long emblemFlagList;

    public UserBoss(UserData user) {
        this.user = user;
    }
}