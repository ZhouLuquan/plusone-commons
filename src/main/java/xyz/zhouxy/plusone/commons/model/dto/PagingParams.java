package xyz.zhouxy.plusone.commons.model.dto;

import java.util.Collections;
import java.util.List;

import xyz.zhouxy.plusone.commons.model.dto.PagingAndSortingQueryParams.SortableProperty;

public class PagingParams {

    private final int size;
    private final long pageNum;
    private final long offset;
    private final List<SortableProperty> orderBy;

    PagingParams(int size, long pageNum, List<SortableProperty> orderBy) {
        this.size = size;
        this.pageNum = pageNum;
        this.offset = (pageNum - 1) * size;
        this.orderBy = orderBy;
    }

    // Getters

    public final List<SortableProperty> getOrderBy() {
        return Collections.unmodifiableList(this.orderBy);
    }

    public final int getSize() {
        return this.size;
    }

    public final long getPageNum() {
        return this.pageNum;
    }

    public final long getOffset() {
        return this.offset;
    }

    // Getters end

    @Override
    public String toString() {
        return "PageInfo [size=" + size + ", pageNum=" + pageNum + ", orderBy=" + orderBy + ", offset="
                + getOffset() + "]";
    }
}
