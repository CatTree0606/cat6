package com.CatTree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class CatTreeApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(CatTreeApplication.class, args);
        System.out.println("项目启动成功 !" );
    }
}
