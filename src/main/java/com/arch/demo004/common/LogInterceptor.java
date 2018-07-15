package com.arch.demo004.common;

import com.alibaba.fastjson.JSON;
import com.arch.demo004.component.LoggerEntity;
import com.arch.demo004.jpa.LogJPA;
import com.fasterxml.jackson.core.JsonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class LogInterceptor implements HandlerInterceptor {

    @Autowired
    private LogJPA logJPA;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        LoggerEntity log = new LoggerEntity();
        log.setSessionId(request.getSession().getId());
        log.setUri(request.getRequestURI());
        String paramData = JSON.toJSONString(request.getParameterMap());
        log.setParamData(paramData);
        log.setClientId(request.getRemoteAddr()+":"+request.getRemotePort()+"/"+request.getRemoteHost());
        log.setMethod(request.getMethod());
        log.setType(request.getContentType());
        log.setAddtime(new Date());
        request.setAttribute("LOGENTITY",log);
        return  true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoggerEntity log = (LoggerEntity)request.getAttribute("LOGENTITY");
        log.setReturnData(JSON.toJSONString(request.getParameterMap()));
        log.setReturnTime(new Date());

        logJPA.save(log);
    }
}
