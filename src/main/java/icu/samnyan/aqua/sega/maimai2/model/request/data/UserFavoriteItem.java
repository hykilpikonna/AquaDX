package icu.samnyan.aqua.sega.maimai2.model.request.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFavoriteItem implements Serializable {
    private int orderId;
    private int id;
}
