package com.example.frist.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */

public class Photos {

    /**
     * desc : 2017年5月22日，北京。当日在连续一天的降雨之后，京城天空明净，在颐和园远望西山，山峦层叠，云雾缭绕，将古老的皇家园林装扮得美轮美奂，宛如水墨画，不少市民直叹“美”。
     * pvnum :
     * createdate : 2017-05-23 11:43:05
     * scover : http://img3.cache.netease.com/photo/0096/2017-05-23/s_CL4APN2F54GI0096.jpg
     * setname : 北京雨后云雾缭绕西山 皇家园林宛如水墨图
     * cover : http://img3.cache.netease.com/photo/0096/2017-05-23/CL4APN2F54GI0096.jpg
     * pics : ["http://img3.cache.netease.com/photo/0096/2017-05-23/CL4APN2F54GI0096.jpg","http://img3.cache.netease.com/photo/0096/2017-05-23/CL4APN2G54GI0096.jpg","http://img3.cache.netease.com/photo/0096/2017-05-23/CL4APN2H54GI0096.jpg"]
     * clientcover1 : http://img3.cache.netease.com/photo/0096/2017-05-23/CL4APN2F54GI0096.jpg
     * replynum : 560
     * topicname :
     * setid : 123085
     * seturl : http://help.3g.163.com/photoview/54GI0096/123085.html
     * datetime : 2017-05-23 11:43:09
     * clientcover :
     * imgsum : 4
     * tcover : http://img3.cache.netease.com/photo/0096/2017-05-23/t_CL4APN2F54GI0096.jpg
     */

    private String desc;
    private String pvnum;
    private String createdate;
    private String scover;
    private String setname;
    private String cover;
    private String clientcover1;
    private String replynum;
    private String topicname;
    private String setid;
    private String seturl;
    private String datetime;
    private String clientcover;
    private String imgsum;
    private String tcover;
    private List<String> pics;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPvnum() {
        return pvnum;
    }

    public void setPvnum(String pvnum) {
        this.pvnum = pvnum;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getScover() {
        return scover;
    }

    public void setScover(String scover) {
        this.scover = scover;
    }

    public String getSetname() {
        return setname;
    }

    public void setSetname(String setname) {
        this.setname = setname;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getClientcover1() {
        return clientcover1;
    }

    public void setClientcover1(String clientcover1) {
        this.clientcover1 = clientcover1;
    }

    public String getReplynum() {
        return replynum;
    }

    public void setReplynum(String replynum) {
        this.replynum = replynum;
    }

    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }

    public String getSetid() {
        return setid;
    }

    public void setSetid(String setid) {
        this.setid = setid;
    }

    public String getSeturl() {
        return seturl;
    }

    public void setSeturl(String seturl) {
        this.seturl = seturl;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getClientcover() {
        return clientcover;
    }

    public void setClientcover(String clientcover) {
        this.clientcover = clientcover;
    }

    public String getImgsum() {
        return imgsum;
    }

    public void setImgsum(String imgsum) {
        this.imgsum = imgsum;
    }

    public String getTcover() {
        return tcover;
    }

    public void setTcover(String tcover) {
        this.tcover = tcover;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }
}
