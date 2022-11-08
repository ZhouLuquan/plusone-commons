package xyz.zhouxy.plusone.util;

import java.util.List;

import lombok.Setter;
import lombok.ToString;

/**
 * 返回分页查询的结果
 *
 * @param <T> 内容列表的元素类型
 *
 * @author <a href="https://gitee.com/zhouxy108">ZhouXY</a>
 * @see PagingAndSortingQueryParams
 */
@ToString
@Setter
public class PageDTO<T> {

    private Long total;
    private List<T> content;

    private PageDTO(List<T> content, Long total) {
        this.content = content;
        this.total = total;
    }

    public static <T> PageDTO<T> of(List<T> content, Long total) {
        return new PageDTO<>(content, total);
    }

    public Long getTotal() {
        return total;
    }

    public List<T> getContent() {
        return content;
    }

}
