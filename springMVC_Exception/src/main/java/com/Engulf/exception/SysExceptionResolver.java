package com.Engulf.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理器对象
 */

public class SysExceptionResolver implements HandlerExceptionResolver {
    /**
     * 处理异常的业务逻辑
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //获取异常对象
        SysException exception = null;
        if(e instanceof SysException){
            exception = (SysException)e;
        }else{
            exception = new SysException("系统正在维护...");
        }
        //创建ModelAndView对象
        ModelAndView mv = new ModelAndView();
        mv.addObject("ErrorMsg",exception.getMessage());
        mv.setViewName("error");
        return mv;
    }
}
