package com.yoyo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author zyl
 * @Date 2022/9/9 -11:48
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@MapperScan("com.yoyo.mapper")
public class YoYoBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(YoYoBlogApplication.class, args);
    }
}
