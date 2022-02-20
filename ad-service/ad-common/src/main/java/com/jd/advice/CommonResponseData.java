package com.jd.advice;

import com.jd.annotation.IngoreResponseAdvice;
import com.jd.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@SuppressWarnings("all")
public class CommonResponseData implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        if(methodParameter.getDeclaringClass().isAnnotationPresent(IngoreResponseAdvice.class)){
            return false;
        }
        if(methodParameter.getMethod().isAnnotationPresent(IngoreResponseAdvice.class)){
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        CommonResponse<Object> response = new CommonResponse<>(-1," ");
        if(o == null){
            return response;
       }else if(o instanceof CommonResponse){
            response = (CommonResponse<Object>) o;
        }else{
            response.setData(o);
        }
        return response;
    }
}
