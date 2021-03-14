package com.wtz.demo.data;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class DataEntity {
    private Integer id;

    private String name;

    private Integer age;

    private Timestamp birth;

    private Date todayDate;

    private Time todayTime;

    private Boolean sex;

    public DataEntity(Integer id,
                      String name,
                      Timestamp birth,
                      Time todayTime,
                      Date todayDate,
                      Boolean sex) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.todayTime = todayTime;
        this.todayDate = todayDate;
        this.sex = sex;
    }

    public DataEntity(
            Integer id,
            String name,
            Integer age,
            Timestamp birth,
            Date todayDate,
            Time todayTime,
            Boolean sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birth = birth;
        this.todayDate = todayDate;
        this.todayTime = todayTime;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Timestamp getBirth() {
        return birth;
    }

    public void setBirth(Timestamp birth) {
        this.birth = birth;
    }

    public Date getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(Date todayDate) {
        this.todayDate = todayDate;
    }

    public Time getTodayTime() {
        return todayTime;
    }

    public void setTodayTime(Time todayTime) {
        this.todayTime = todayTime;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("name", name)
                .append("birth", birth)
                .append("todayDate", todayDate)
                .append("todayTime", todayTime)
                .toString();
    }
}
