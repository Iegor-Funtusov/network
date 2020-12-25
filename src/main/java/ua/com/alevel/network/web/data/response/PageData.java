package ua.com.alevel.network.web.data.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

/**
 * @author Iehor Funtusov, created 24/12/2020 - 9:57 AM
 */

@Getter
@Setter
@ToString
public class PageData<T> {

    private int currentPage;
    private int pageSize;
    private long totalElements;
    private long totalPages;
    private List<T> items;

    public PageData() {
        this.currentPage = 1;
        this.pageSize = 10;
        this.totalElements = 0;
        this.items = Collections.emptyList();
    }
}
