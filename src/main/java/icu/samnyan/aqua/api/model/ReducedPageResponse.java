package icu.samnyan.aqua.api.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Getter
@Setter
public class ReducedPageResponse<T> {
    private Collection<T> content;
    private Integer page;
    private Integer totalPages;
    private Long totalElements;

    public ReducedPageResponse(Collection<T> content, Integer page, Integer totalPages, Long totalElements) {
        this.content = content;
        this.page = page;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }
}
