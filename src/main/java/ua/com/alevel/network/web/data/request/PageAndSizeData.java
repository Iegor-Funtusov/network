package ua.com.alevel.network.web.data.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Iehor Funtusov, created 16/12/2020 - 3:55 AM
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageAndSizeData {

    int page;
    int size;
}
