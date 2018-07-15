package com.arch.demo004.component;

import com.arch.demo004.common.UserValidate;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "T_USER")
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "T_USER_USRNAME")
    @Size(min = 4,max = 100)
    private String userName;

    @Column(name = "T_USER_PASSWORD")
    @Size(min = 4,max = 100)
    private String passWord;

    @Temporal(TemporalType.TIMESTAMP)
    private Date addTime = new Date();

    @Column
    @UserValidate(value = "1,2,3", message = "值不在预设的范围内!")
    private String isenable;
}
