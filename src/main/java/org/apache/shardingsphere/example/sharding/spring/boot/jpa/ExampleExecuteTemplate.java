/*
 * 版权所有 © 北京晟壁科技有限公司 2008-2027。保留一切权利!
 */
package org.apache.shardingsphere.example.sharding.spring.boot.jpa;

import org.apache.shardingsphere.example.sharding.spring.boot.jpa.service.ExampleService;

import java.sql.SQLException;

/**
 * 用途：
 * 作者: lishuyi
 * 时间: 2020/11/24  17:46
 */
public final class ExampleExecuteTemplate {

    public static void run(final ExampleService exampleService) throws SQLException {
        try {
            exampleService.initEnvironment();
            exampleService.processSuccess();
        } finally {
            exampleService.cleanEnvironment();
        }
    }
}
