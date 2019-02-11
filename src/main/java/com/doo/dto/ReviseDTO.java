package com.doo.dto;

import com.doo.pojo.Student;

import java.util.Date;

public class ReviseDTO extends Student {

    private Byte needRevise;
    private Byte revised;
    private Date createTime;

    public Byte getNeedRevise() {
        return needRevise;
    }

    public void setNeedRevise(Byte needRevise) {
        this.needRevise = needRevise;
    }

    public Byte getRevised() {
        return revised;
    }

    public void setRevised(Byte revised) {
        this.revised = revised;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
