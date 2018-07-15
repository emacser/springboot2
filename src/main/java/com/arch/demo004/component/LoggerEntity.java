package com.arch.demo004.component;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "d_log")
@Data
public class LoggerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String clientId;

    @Column
    private String uri;

    @Column
    private String type;

    @Column
    private String method;

    @Column
    private String paramData;

    @Column
    private String sessionId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date addtime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date returnTime;

    @Column
    private String returnData;
}
