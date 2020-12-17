/*
 * 版权所有 © 北京晟壁科技有限公司 2008-2027。保留一切权利!
 */
package org.apache.shardingsphere.example.sharding.spring.boot.jpa.service;

import java.sql.SQLException;

/**
 * 用途：
 * 作者: lishuyi
 * 时间: 2020/11/24  17:46
 */
public interface ExampleService {

    /**
     * Initialize environment.
     *
     * @throws SQLException SQL exception
     */
    void initEnvironment() throws SQLException;

    /**
     * Clean environment.
     *
     * @throws SQLException SQL exception
     */
    void cleanEnvironment() throws SQLException;

    /**
     * Process success.
     *
     * @throws SQLException SQL exception
     */
    void processSuccess() throws SQLException;

    /**
     * Process failure.
     *
     * @throws SQLException SQL exception
     */
    void processFailure() throws SQLException;

    /**
     * Print data.
     *
     * @throws SQLException SQL exception
     */
    void printData() throws SQLException;

}
