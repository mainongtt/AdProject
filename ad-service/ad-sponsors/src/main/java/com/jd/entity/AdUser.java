package com.jd.entity;

import com.jd.constant.CommonStatus;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdUser {
    private Long id;
    private String userName;
    private String token;
    //用户状态
    private Integer userStatus;
    private Date createTime;
    private Date updateTime;

    public AdUser(Long id) {
        this.id = id;
    }

    public AdUser(String userName) {
        this.userName = userName;
    }

    public AdUser(String userName, String token) {
        this.userName = userName;
        this.token = token;
        this.userStatus = CommonStatus.VALID.getStatus();
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }
}
