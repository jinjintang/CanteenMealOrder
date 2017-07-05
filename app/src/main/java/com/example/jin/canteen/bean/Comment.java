package com.example.jin.canteen.bean;

/**
 * Created by jin on 2017/6/16.
 */

public class Comment  {/**
         * id : 8
         * mid : 4
         * uid : 1
         * comment : 这个菜很好吃
         * mname : 阿虎
         * avatar : http://106.14.167.106/uploads/5.jpg
         * user : 1114010606
         * reply : null
         */

        private int id;
        private int mid;
        private int uid;
        private String comment;
        private String mname;
        private String avatar;
        private String user;
        private String reply;

    public Comment(int mid, int uid, String comment) {
        this.mid = mid;
        this.uid = uid;
        this.comment = comment;
    }

    public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getMname() {
            return mname;
        }

        public void setMname(String mname) {
            this.mname = mname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String  getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }

}
