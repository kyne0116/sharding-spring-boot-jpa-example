/*
 * 版权所有 © 北京晟壁科技有限公司 2008-2027。保留一切权利!
 */
package org.apache.shardingsphere.example.sharding.spring.boot.jpa.service;

import org.apache.shardingsphere.example.sharding.spring.boot.jpa.entity.AddressEntity;
import org.apache.shardingsphere.example.sharding.spring.boot.jpa.entity.OrderEntity;
import org.apache.shardingsphere.example.sharding.spring.boot.jpa.entity.OrderItemEntity;
import org.apache.shardingsphere.example.sharding.spring.boot.jpa.repository.AddressRepository;
import org.apache.shardingsphere.example.sharding.spring.boot.jpa.repository.OrderItemRepository;
import org.apache.shardingsphere.example.sharding.spring.boot.jpa.repository.OrderRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用途：
 * 作者: lishuyi
 * 时间: 2020/11/24  17:54
 */
@Service
@Primary
public class OrderServiceImpl implements ExampleService {

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private OrderItemRepository orderItemRepository;

    @Resource
    private AddressRepository addressRepository;

    @Override
    public void initEnvironment() throws SQLException {
        for (int i = 1; i <= 10; i++) {
            AddressEntity entity = new AddressEntity();
            entity.setAddressId((long) i);
            entity.setAddressName("address_" + String.valueOf(i));
            addressRepository.insert(entity);
        }
    }

    @Override
    public void cleanEnvironment() {
    }

    @Override
    @Transactional
    public void processSuccess() throws SQLException {
        System.out.println("-------------- Process Success Begin ---------------");
        List<Long> orderIds = insertData();
        printData();
        deleteData(orderIds);
        printData();
        System.out.println("-------------- Process Success Finish --------------");
    }

    @Override
    @Transactional
    public void processFailure() throws SQLException {
        System.out.println("-------------- Process Failure Begin ---------------");
        insertData();
        System.out.println("-------------- Process Failure Finish --------------");
        throw new RuntimeException("Exception occur for transaction test.");
    }

    private List<Long> insertData() throws SQLException {
        System.out.println("---------------------------- Insert Data ----------------------------");
        List<Long> result = new ArrayList<>(10);
        for (int i = 1; i <= 10; i++) {
            OrderEntity order = new OrderEntity();
            order.setUserId(i);
            order.setAddressId(i);
            order.setStatus("INSERT_TEST_JPA");
            orderRepository.insert(order);
            OrderItemEntity item = new OrderItemEntity();
            item.setOrderId(order.getOrderId());
            item.setUserId(i);
            item.setStatus("INSERT_TEST_JPA");
            orderItemRepository.insert(item);
            result.add(order.getOrderId());
        }
        return result;
    }

    private void deleteData(final List<Long> orderIds) throws SQLException {
        System.out.println("---------------------------- Delete Data ----------------------------");
        for (Long each : orderIds) {
            orderRepository.delete(each);
            orderItemRepository.delete(each);
        }
    }

    @Override
    public void printData() throws SQLException {
        System.out.println("---------------------------- Print Order Data -----------------------");
        for (Object each : orderRepository.selectAll()) {
            System.out.println(each);
        }
        System.out.println("---------------------------- Print OrderItem Data -------------------");
        for (Object each : orderItemRepository.selectAll()) {
            System.out.println(each);
        }
    }
}
