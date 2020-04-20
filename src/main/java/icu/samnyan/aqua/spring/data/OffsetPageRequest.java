package icu.samnyan.aqua.spring.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * Somehow spring boot jpa doesn't provide a class to use offset to to the pagination
 * @author samnyan (privateamusement@protonmail.com)
 */
public class OffsetPageRequest implements Pageable, Serializable {

    private static final long serialVersionUID = 1L;
    private final int offset;
    private final int limit;
    private final Sort sort;

    /**
     * Creates a new {@link OffsetPageRequest} with sort parameters applied.
     *
     * @param offset offset of the request index, must not be negative.
     * @param limit the size of the page to be returned, must be greater than 0.
     * @param sort must not be {@literal null}, use {@link Sort#unsorted()} instead.
     */
    public OffsetPageRequest(int offset, int limit, Sort sort) {

        if (offset < 0) {
            throw new IllegalArgumentException("Offset must not be less than zero!");
        }

        if (limit < 1) {
            throw new IllegalArgumentException("Limit must not be less than one!");
        }

        Assert.notNull(sort, "Sort must not be null!");

        this.offset = offset;
        this.limit = limit;
        this.sort = sort;
    }

    public static OffsetPageRequest of(int page, int size) {
        return of(page, size, Sort.unsorted());
    }

    public static OffsetPageRequest of(int page, int size, Sort sort) {
        return new OffsetPageRequest(page, size, sort);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#getPageNumber()
     */
    @Override
    public int getPageNumber() {
        return offset / limit;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#getPageSize()
     */
    @Override
    public int getPageSize() {
        return limit;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#getOffset()
     */
    @Override
    public long getOffset() {
        return offset;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#getSort()
     */
    @Override
    public Sort getSort() {
        return sort;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#next()
     */
    @Override
    public Pageable next() {
        return new OffsetPageRequest(Math.toIntExact(getOffset() + getPageSize()), getPageSize(), getSort());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#previousOrFirst()
     */
    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? new OffsetPageRequest(Math.toIntExact(getOffset() - getPageSize()), getPageSize(), getSort()) : this;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#first()
     */
    @Override
    public Pageable first() {
        return new OffsetPageRequest(0, getPageSize(), getSort());
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;

        result = prime * result + offset;
        result = prime * result + limit;

        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(@Nullable Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        OffsetPageRequest other = (OffsetPageRequest) obj;
        return this.offset == other.offset && this.limit == other.limit;
    }

}
