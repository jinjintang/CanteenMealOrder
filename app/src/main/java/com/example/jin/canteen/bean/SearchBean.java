package com.example.jin.canteen.bean;

/**
 * Created by jin on 2017/6/30.
 */

public class SearchBean {
    private int search;
    private String keyword;

    public SearchBean(int search, String keyword) {
        this.search = search;
        this.keyword = keyword;
    }

    public int getSearch() {
        return search;
    }

    public void setSearch(int search) {
        this.search = search;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
