package icu.samnyan.aqua.sega.diva.model.gamedata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import icu.samnyan.aqua.sega.diva.model.common.ContestLeague;
import icu.samnyan.aqua.sega.diva.model.common.ContestNormaType;
import icu.samnyan.aqua.sega.diva.model.common.ContestStageLimit;
import icu.samnyan.aqua.sega.diva.util.DivaDateTimeUtil;
import icu.samnyan.aqua.sega.util.URIEncoder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "DivaContest")
@Table(name = "diva_contest")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    private boolean enable;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private ContestLeague league;

    private int stars;

    private int minComplexity;

    private int maxComplexity;

    private int stages;

    @Enumerated(EnumType.STRING)
    private ContestStageLimit stageLimit;

    @Enumerated(EnumType.STRING)
    private ContestNormaType normaType;

    private int bronzeBorders = 0;

    private int sliverBorders = 0;

    private int goldBorders = 0;

    public String getString() {
        List<Object> list = new LinkedList<>();
        list.add(this.id);
        list.add(DivaDateTimeUtil.format(this.startTime));
        list.add(DivaDateTimeUtil.format(this.endTime));
        list.add(URIEncoder.encode(this.name));
        list.add(URIEncoder.encode(this.description));
        list.add(this.league.getValue());
        list.add(this.stars);
        list.add(this.stages);
        list.add(this.stageLimit.getValue());
        list.add(this.normaType.getValue());
        list.add(this.bronzeBorders);
        list.add(this.sliverBorders);
        list.add(this.goldBorders);
        list.add(-1);
        list.add(-2);
        list.add(this.minComplexity);
        list.add(this.maxComplexity);
        list.add(-1);
        list.add(-2);
        list.add("7fffffffffffffffffffffffffffffff");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("7fffffffffffffffffffffffffffffff");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("7fffffffffffffffffffffffffffffff");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("7fffffffffffffffffffffffffffffff");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("7fffffffffffffffffffffffffffffff");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("7fffffffffffffffffffffffffffffff");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("7fffffffffffffffffffffffffffffff");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("7fffffffffffffffffffffffffffffff");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("7fffffffffffffffffffffffffffffff");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("7fffffffffffffffffffffffffffffff");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("7fffffffffffffffffffffffffffffff");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("7fffffffffffffffffffffffffffffff");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("7fffffffffffffffffffffffffffffff");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("7fffffffffffffffffffffffffffffff");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("7fffffffffffffffffffffffffffffff");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("7fffffffffffffffffffffffffffffff");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("7fffffffffffffffffffffffffffffff");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("7fffffffffffffffffffffffffffffff");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("7fffffffffffffffffffffffffffffff");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("-2");
        list.add("7fffffffffffffffffffffffffffffff");
        return list.stream().map(Object::toString).collect(Collectors.joining(","));
    }
}
