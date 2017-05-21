/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.dangdang.ddframe.rdb.sharding.example.jdbc;

import com.dangdang.ddframe.rdb.sharding.example.jdbc.service.OrderService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// CHECKSTYLE:OFF
@Service
@Transactional
public class Main {
    public static void main(final String[] args) {
    	 Logger logger = LoggerFactory.getLogger(Main.class);
    	 Logger logger2 = LoggerFactory.getLogger("abc");
//    	 MDC.put(key, val);
    	 logger.error("GGGGG");
        // CHECKSTYLE:ON
//    	GroovyClassLoader
//    	System.setProperty("groovy.target.directory", "E:\\grvclass\\");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/mybatisContext.xml");
        OrderService orderService = applicationContext.getBean(OrderService.class);
//        orderService.clear();
        orderService.fooService();
        orderService.selectSingle(0L);
       
//        orderService.selectSingle(10L);
//        orderService.selectSingle(100L);
    
        //[order_id: , user_id: 10, status: UPDATED, order_id: , user_id: 11, status: UPDATED]
//        orderService.clear();
//        try {
//            orderService.fooServiceWithFailure();
//        } catch (final IllegalArgumentException e) {
//            System.out.println("roll back");
//        }
//        //[]
//        orderService.select();
    }
}
