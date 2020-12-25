package icu.samnyan.aqua.sega.maimai.model.response.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGradeStatus {
    int gradeVersion;
    int gradeLevel;
    int gradeSubLevel;
    int gradeMaxId;
}
