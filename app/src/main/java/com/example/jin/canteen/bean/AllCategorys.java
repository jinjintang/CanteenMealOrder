package com.example.jin.canteen.bean;

import java.util.List;

/**
 * Created by jin on 2017/6/28.
 */

public class AllCategorys {

    /**
     * categroy : [{"id":1,"name":"素材","avatar":"0","dish":[]},{"id":2,"name":"素菜","avatar":"0","dish":[{"id":1,"name":"西红柿","avatar":"","introduciton":"","price":"10","categroy_id":2,"cid":48,"cname":"二食堂","categroy_name":"素菜"},{"id":2,"name":"西红柿","avatar":"","introduciton":"","price":"10","categroy_id":2,"cid":48,"cname":"二食堂","categroy_name":"素菜"},{"id":3,"name":"西红柿","avatar":"","introduciton":"","price":"10","categroy_id":2,"cid":48,"cname":"二食堂","categroy_name":"素菜"}]},{"id":3,"name":"素菜","avatar":"0","dish":[]},{"id":4,"name":"荤菜","avatar":"0","dish":[]},{"id":5,"name":"面食","avatar":"0","dish":[]},{"id":6,"name":"炒菜","avatar":"0","dish":[]},{"id":7,"name":"糖类","avatar":"0","dish":[]}]
     * _links : {"self":{"href":"http://106.14.167.106/web/categroys?page=1"}}
     * _meta : {"totalCount":7,"pageCount":1,"currentPage":1,"perPage":20}
     */

    private LinksBean _links;
    private MetaBean _meta;
    private List<Categroy> categroy;

    public LinksBean get_links() {
        return _links;
    }

    public void set_links(LinksBean _links) {
        this._links = _links;
    }

    public MetaBean get_meta() {
        return _meta;
    }

    public void set_meta(MetaBean _meta) {
        this._meta = _meta;
    }

    public List<Categroy> getCategroy() {
        return categroy;
    }

    public void setCategroy(List<Categroy> categroy) {
        this.categroy = categroy;
    }

    public static class LinksBean {
        /**
         * self : {"href":"http://106.14.167.106/web/categroys?page=1"}
         */

        private SelfBean self;

        public SelfBean getSelf() {
            return self;
        }

        public void setSelf(SelfBean self) {
            this.self = self;
        }

        public static class SelfBean {
            /**
             * href : http://106.14.167.106/web/categroys?page=1
             */

            private String href;

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }
        }
    }

    public static class MetaBean {
        /**
         * totalCount : 7
         * pageCount : 1
         * currentPage : 1
         * perPage : 20
         */

        private int totalCount;
        private int pageCount;
        private int currentPage;
        private int perPage;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getPerPage() {
            return perPage;
        }

        public void setPerPage(int perPage) {
            this.perPage = perPage;
        }
    }


}
