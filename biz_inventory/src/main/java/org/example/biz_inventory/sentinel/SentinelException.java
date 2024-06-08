package org.example.biz_inventory.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.common.R;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SentinelException implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        R r = null;
        //根据限流降级的策略规则，不同的异常返回不同的提示语，
        if (e instanceof FlowException) {
            r = R.fail(500, "接口限流了");
        }else if (e instanceof DegradeException){
            r = R.fail(500, "服务降级了");
        }else if (e instanceof ParamFlowException){
            r = R.fail(500, "热点参数限流了");
        }else if (e instanceof SystemBlockException){
            r = R.fail(500, "触发系统保护规则了");
        }else if(e instanceof AuthorityException){
            r = R.fail(500, "授权规则不通过");
        }
        httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(httpServletResponse.getWriter(),r);
    }
}
