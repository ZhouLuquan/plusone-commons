package xyz.zhouxy.plusone.commons.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.Setter;

/**
 * 分页排序查询参数
 *
 * <p>
 * 根据传入的 {@code size} 和 {@code pageNum}，
 * 提供 {@code getOffset} 方法计算 SQL 语句中 {@code offset} 的值。
 * </p>
 *
 * @author <a href="https://gitee.com/zhouxy108">ZhouXY</a>
 * @see PageDTO
 */
@Setter
public class PagingAndSortingQueryParams {

    protected String orderBy;
    protected Integer size;
    protected Long pageNum;

    private final List<String> sortableColNames;

    public PagingAndSortingQueryParams() {
        sortableColNames = Collections.emptyList();
    }

    public PagingAndSortingQueryParams(String... sortableColNames) {
        this.sortableColNames = Arrays.asList(sortableColNames);
    }

    public String getOrderBy() {
        return orderBy != null && sortableColNames.contains(orderBy) ? orderBy : null;
    }

    public int getSize() {
        return this.size != null ? this.size : 15;
    }

    public long getPageNum() {
        return this.pageNum != null ? this.pageNum : 1;
    }

    public long getOffset() {
        return (getPageNum() - 1) * getSize();
    }

}
