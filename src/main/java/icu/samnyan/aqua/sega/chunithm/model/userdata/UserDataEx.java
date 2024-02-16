package icu.samnyan.aqua.sega.chunithm.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChuniUserDataEx")
@Table(name = "chuni_user_data_ex")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "compatibleCmVersion",
        "medal",
        "mapIconId",
        "voiceId",
        "ext1",
        "ext2",
        "ext3",
        "ext4",
        "ext5",
        "ext6",
        "ext7",
        "ext8",
        "ext9",
        "ext10",
        "ext11",
        "ext12",
        "ext13",
        "ext14",
        "ext15",
        "ext16",
        "ext17",
        "ext18",
        "ext19",
        "ext20",
        "extStr1",
        "extStr2",
        "extStr3",
        "extStr4",
        "extStr5",
        "extLong1",
        "extLong2",
        "extLong3",
        "extLong4",
        "extLong5"
})
public class UserDataEx implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private String compatibleCmVersion;

    private int medal;

    private int mapIconId;

    private int voiceId;

    private int ext1;

    private int ext2;

    private int ext3;

    private int ext4;

    private int ext5;

    private int ext6;

    private int ext7;

    private int ext8;

    private int ext9;

    private int ext10;

    private int ext11;

    private int ext12;

    private int ext13;

    private int ext14;

    private int ext15;

    private int ext16;

    private int ext17;

    private int ext18;

    private int ext19;

    private int ext20;

    private String extStr1;

    private String extStr2;

    private String extStr3;

    private String extStr4;

    private String extStr5;

    private long extLong1;

    private long extLong2;

    private long extLong3;

    private long extLong4;

    private long extLong5;


    public UserDataEx(UserData userData) {
        user = userData;
    }
}
