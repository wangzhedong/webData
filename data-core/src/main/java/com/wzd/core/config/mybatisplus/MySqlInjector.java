package com.wzd.core.config.mybatisplus;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.wzd.core.config.mybatisplus.method.DeleteAll;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MySqlInjector extends DefaultSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList() {
        List<AbstractMethod> methodList = super.getMethodList();
        //增加自定义方法
        methodList.add(new DeleteAll());
        return methodList;
    }
}
