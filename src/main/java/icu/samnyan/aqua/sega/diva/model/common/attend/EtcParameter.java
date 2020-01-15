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
public class EtcParameter implements Internalizable {
    private Boolean module_shop_close = false;
    private Boolean card_reissue_close = true;
    private Boolean card_renewal_close = true;
    private Boolean reset_passwd_close = true;
    private Boolean change_passwd_close = false;
    private Boolean change_name_close = false;
    private Boolean encore_mode_close = true;
    private Boolean third_stg_mode_close = false;
    private Integer slow_down_threshold = 0;
    private Boolean log_write_flag = false;
    private Boolean daily_quest_close = true;
    private Boolean weekly_quest_close = true;
    private Boolean special_quest_close = true;
    private Boolean nppg_close = false;

    @Override
    public String toInternal() {
        List<Object> list = new LinkedList<>();
        list.add(module_shop_close);
        list.add(card_reissue_close);
        list.add(card_renewal_close);
        list.add(reset_passwd_close);
        list.add(change_passwd_close);
        list.add(change_name_close);
        list.add(encore_mode_close);
        list.add(third_stg_mode_close);
        list.add(slow_down_threshold);
        list.add(log_write_flag);
        list.add(daily_quest_close);
        list.add(weekly_quest_close);
        list.add(special_quest_close);
        list.add(nppg_close);
        list.addAll(Collections.nCopies(100, 0));
        return list.stream().limit(100).map(x -> {
            if (x instanceof Boolean) {
                return (Boolean) x ? "1" : "0";
            }
            if (x instanceof Integer) {
                return String.valueOf(x);
            }
            return "0";
        }).collect(Collectors.joining(","));
    }
}
