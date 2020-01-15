package icu.samnyan.aqua.sega.diva.model.common.attend;

import icu.samnyan.aqua.sega.diva.model.Internalizable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@NoArgsConstructor
public class DispersalParameter implements Internalizable {
    private int max_pd_items = 30;
    private int max_ps_rankings = 100;
    private int max_uploadable_screenshots = 2;
    private int ss_upload_delay = 1;

    @Override
    public String toInternal() {
        List<Integer> list = new LinkedList<>();
        list.add(max_pd_items);
        list.add(1);
        list.add(max_ps_rankings);
        list.add(max_uploadable_screenshots);
        list.add(ss_upload_delay);
        list.add(1);
        list.addAll(Collections.nCopies(100, 0));
        return list.stream().limit(100).map(Object::toString).collect(Collectors.joining(","));
    }
}
