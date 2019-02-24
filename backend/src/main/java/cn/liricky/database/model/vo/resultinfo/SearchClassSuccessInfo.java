package cn.liricky.database.model.vo.resultinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SearchClassSuccessInfo {
    @JsonProperty("classid")
    Integer classid;
    @JsonProperty("classname")
    String classname;
    @JsonProperty("teachername")
    String teachername;
    @JsonProperty("credit")
    Integer credit;
    @JsonProperty("department")
    String department;

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
