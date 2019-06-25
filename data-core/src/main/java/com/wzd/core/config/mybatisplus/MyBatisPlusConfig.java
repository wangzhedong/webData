package com.wzd.core.config.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MapperScannerConfigurer的配置
 * 发现如果使用了JavaConfig的方式启动Spring，
 * 那么即使是ImportResource了XML文件也是无法让MapperScannerConfigurer扫描到的，
 * 必须改为同样的JavaConfig方式，@MapperScan("com.cntaiping.editor.core.mapper")
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.wzd.core.mapper")
public class MyBatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }

}
