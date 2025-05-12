package com.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Pagination {
    private Long totalElement;
    private Integer size;
    private Integer totalPage;
}
