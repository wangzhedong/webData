package com.wzd.core.config;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Slf4j
@Aspect
@Component
public class BaseMapperAop {

    /*
     * * 必须为final String类型的,注解里要使用的变量只能是静态常量类型的
     */
    private static final String EDP = "execution(* com.baomidou.mybatisplus.core.mapper.BaseMapper.insert(..))";


    @Pointcut(EDP)
    public void insertPointcut() {

    }

    @Before("insertPointcut()")
    public void doBefore(JoinPoint jp) {
        Object[] objs = jp.getArgs();
        if (objs.length != 0) {
            Object obj = objs[0];
            try {
                Field field = obj.getClass().getSuperclass().getDeclaredField("isDelete");
                field.setAccessible(true);
                TableLogic tableLogic = field.getAnnotation(TableLogic.class);
                if (tableLogic != null) {
                    field.set(obj, tableLogic.value());
                }
            } catch (NoSuchFieldException e) {
                log.error(e.getMessage());
            } catch (IllegalAccessException e) {
                log.error(e.getMessage());
            }
        }
    }
}
