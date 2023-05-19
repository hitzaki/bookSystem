package com.chen.work.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("t_record")
public class Record {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer rid;

    private String rname;

    private Integer bid;

    private String bname;

    private Integer state;

    @TableField("returnTime")
    private Date returnTime;

    public Record(){}

    public Record(Integer id, Integer rid, String rname, Integer bid, String bname, Integer state, Date returnTime) {
        this.id = id;
        this.rid = rid;
        this.rname = rname;
        this.bid = bid;
        this.bname = bname;
        this.state = state;
        this.returnTime = returnTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }
}
