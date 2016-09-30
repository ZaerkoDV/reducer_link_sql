package com.instinctools.reducerlink.service.support;

import java.util.List;

public class PagedResult<T> {
    private List<T> list;
    private int totalPages;

    public PagedResult() {
    }

    public PagedResult(List<T> list, int totalPages) {
        this.list = list;
        this.totalPages = totalPages;
    }

    public List<T> getList() {
        return list;
    }

    public PagedResult<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public PagedResult<T> setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

}
