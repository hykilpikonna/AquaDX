package icu.samnyan.aqua.sega.diva.model.common.collection;

import icu.samnyan.aqua.sega.diva.model.Internalizable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@NoArgsConstructor
public class ClearTally implements Internalizable {

    private ClearSet easy = new ClearSet();
    private ClearSet normal = new ClearSet();
    private ClearSet hard = new ClearSet();
    private ClearSet extreme = new ClearSet();
    private ClearSet extraExtreme = new ClearSet();

    @Override
    public String toInternal() {
        List<Integer> list = new LinkedList<>();
        list.add(easy.getClear());
        list.add(easy.getGreat());
        list.add(easy.getExcellent());
        list.add(easy.getPerfect());
        list.add(normal.getClear());
        list.add(normal.getGreat());
        list.add(normal.getExcellent());
        list.add(normal.getPerfect());
        list.add(hard.getClear());
        list.add(hard.getGreat());
        list.add(hard.getExcellent());
        list.add(hard.getPerfect());
        list.add(extreme.getClear());
        list.add(extreme.getGreat());
        list.add(extreme.getExcellent());
        list.add(extreme.getPerfect());
        list.add(extraExtreme.getClear());
        list.add(extraExtreme.getGreat());
        list.add(extraExtreme.getExcellent());
        list.add(extraExtreme.getPerfect());
        return list.stream().map(Object::toString).collect(Collectors.joining(","));
    }
}
