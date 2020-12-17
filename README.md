# sharding-spring-boot-jpa-example
 Sharding Sphere4+SpringBoot2+JPA2分库分表读写分离Demo

启动类为ExampleMain，一共划分为如下五个场景：

```-
sharding-databases-分库
sharding-tables-分表
sharding-databases-tables-分库分表
master-slave-主从读写分离
sharding-master-slave-分库分表主从读写分离

详见：application.properties
```

详细依赖组件如下：

```
com.simbest.boot:sharding-spring-boot-jpa-example:jar:0.1
+- org.springframework.boot:spring-boot-starter-data-jpa:jar:2.2.2.RELEASE:compile
|  +- org.springframework.boot:spring-boot-starter-aop:jar:2.2.2.RELEASE:compile
|  |  +- org.springframework:spring-aop:jar:5.2.2.RELEASE:compile
|  |  \- org.aspectj:aspectjweaver:jar:1.9.5:compile
|  +- jakarta.activation:jakarta.activation-api:jar:1.2.1:compile
|  +- jakarta.persistence:jakarta.persistence-api:jar:2.2.3:compile
|  +- jakarta.transaction:jakarta.transaction-api:jar:1.3.3:compile
|  +- org.hibernate:hibernate-core:jar:5.4.9.Final:compile
|  |  +- org.jboss.logging:jboss-logging:jar:3.3.2.Final:compile
|  |  +- org.javassist:javassist:jar:3.24.0-GA:compile
|  |  +- net.bytebuddy:byte-buddy:jar:1.10.2:compile
|  |  +- antlr:antlr:jar:2.7.7:compile
|  |  +- org.jboss:jandex:jar:2.1.1.Final:compile
|  |  +- com.fasterxml:classmate:jar:1.5.1:compile
|  |  +- org.dom4j:dom4j:jar:2.1.1:compile
|  |  +- org.hibernate.common:hibernate-commons-annotations:jar:5.1.0.Final:compile
|  |  \- org.glassfish.jaxb:jaxb-runtime:jar:2.3.1:compile
|  |     +- org.glassfish.jaxb:txw2:jar:2.3.1:compile
|  |     +- com.sun.istack:istack-commons-runtime:jar:3.0.7:compile
|  |     +- org.jvnet.staxex:stax-ex:jar:1.8:compile
|  |     \- com.sun.xml.fastinfoset:FastInfoset:jar:1.2.15:compile
|  +- org.springframework.data:spring-data-jpa:jar:2.2.3.RELEASE:compile
|  |  +- org.springframework.data:spring-data-commons:jar:2.2.3.RELEASE:compile
|  |  +- org.springframework:spring-orm:jar:5.2.2.RELEASE:compile
|  |  +- org.springframework:spring-context:jar:5.2.2.RELEASE:compile
|  |  +- org.springframework:spring-tx:jar:5.2.2.RELEASE:compile
|  |  +- org.springframework:spring-beans:jar:5.2.2.RELEASE:compile
|  |  \- org.springframework:spring-core:jar:5.2.2.RELEASE:compile
|  |     \- org.springframework:spring-jcl:jar:5.2.2.RELEASE:compile
|  \- org.springframework:spring-aspects:jar:5.2.2.RELEASE:compile
+- org.springframework.boot:spring-boot-starter-web:jar:2.2.2.RELEASE:compile
|  +- org.springframework.boot:spring-boot-starter:jar:2.2.2.RELEASE:compile
|  |  +- org.springframework.boot:spring-boot:jar:2.2.2.RELEASE:compile
|  |  +- org.springframework.boot:spring-boot-autoconfigure:jar:2.2.2.RELEASE:compile
|  |  +- org.springframework.boot:spring-boot-starter-logging:jar:2.2.2.RELEASE:compile
|  |  |  +- ch.qos.logback:logback-classic:jar:1.2.3:compile
|  |  |  |  \- ch.qos.logback:logback-core:jar:1.2.3:compile
|  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.12.1:compile
|  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.12.1:compile
|  |  |  \- org.slf4j:jul-to-slf4j:jar:1.7.29:compile
|  |  +- jakarta.annotation:jakarta.annotation-api:jar:1.3.5:compile
|  |  \- org.yaml:snakeyaml:jar:1.25:compile
|  +- org.springframework.boot:spring-boot-starter-json:jar:2.2.2.RELEASE:compile
|  |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.10.1:compile
|  |  |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.10.1:compile
|  |  |  \- com.fasterxml.jackson.core:jackson-core:jar:2.10.1:compile
|  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:jar:2.10.1:compile
|  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.10.1:compile
|  |  \- com.fasterxml.jackson.module:jackson-module-parameter-names:jar:2.10.1:compile
|  +- org.springframework.boot:spring-boot-starter-tomcat:jar:2.2.2.RELEASE:compile
|  |  +- org.apache.tomcat.embed:tomcat-embed-core:jar:9.0.29:compile
|  |  +- org.apache.tomcat.embed:tomcat-embed-el:jar:9.0.29:compile
|  |  \- org.apache.tomcat.embed:tomcat-embed-websocket:jar:9.0.29:compile
|  +- org.springframework.boot:spring-boot-starter-validation:jar:2.2.2.RELEASE:compile
|  |  +- jakarta.validation:jakarta.validation-api:jar:2.0.1:compile
|  |  \- org.hibernate.validator:hibernate-validator:jar:6.0.18.Final:compile
|  +- org.springframework:spring-web:jar:5.2.2.RELEASE:compile
|  \- org.springframework:spring-webmvc:jar:5.2.2.RELEASE:compile
|     \- org.springframework:spring-expression:jar:5.2.2.RELEASE:compile
+- org.springframework.boot:spring-boot-starter-jdbc:jar:2.2.2.RELEASE:compile
|  +- com.zaxxer:HikariCP:jar:3.4.1:compile
|  \- org.springframework:spring-jdbc:jar:5.2.2.RELEASE:compile
+- org.apache.shardingsphere:sharding-jdbc-spring-boot-starter:jar:4.1.1:compile
|  +- org.apache.shardingsphere:sharding-spring-boot-util:jar:4.1.1:compile
|  |  \- org.apache.shardingsphere:sharding-core-common:jar:4.1.1:compile
|  |     +- org.apache.shardingsphere:shardingsphere-common:jar:4.1.1:compile
|  |     |  \- org.apache.shardingsphere:shardingsphere-spi:jar:4.1.1:compile
|  |     +- org.apache.shardingsphere:shardingsphere-sql-parser-binder:jar:4.1.1:compile
|  |     +- org.apache.shardingsphere:sharding-core-api:jar:4.1.1:compile
|  |     |  \- org.apache.shardingsphere:encrypt-core-api:jar:4.1.1:compile
|  |     +- org.apache.shardingsphere:encrypt-core-common:jar:4.1.1:compile
|  |     +- org.codehaus.groovy:groovy:jar:indy:2.4.5:compile
|  |     \- commons-codec:commons-codec:jar:1.10:compile
|  +- org.apache.shardingsphere:sharding-transaction-spring:jar:4.1.1:compile
|  |  \- org.apache.shardingsphere:sharding-transaction-core:jar:4.1.1:compile
|  +- org.apache.shardingsphere:sharding-jdbc-core:jar:4.1.1:compile
|  |  +- org.apache.shardingsphere:shardingsphere-pluggable:jar:4.1.1:compile
|  |  |  +- org.apache.shardingsphere:shardingsphere-route:jar:4.1.1:compile
|  |  |  +- org.apache.shardingsphere:shardingsphere-rewrite-engine:jar:4.1.1:compile
|  |  |  +- org.apache.shardingsphere:shardingsphere-executor:jar:4.1.1:compile
|  |  |  \- org.apache.shardingsphere:shardingsphere-merge:jar:4.1.1:compile
|  |  +- org.apache.shardingsphere:shardingsphere-sql-parser-sql92:jar:4.1.1:compile
|  |  |  \- org.apache.shardingsphere:shardingsphere-sql-parser-engine:jar:4.1.1:compile
|  |  |     +- org.apache.shardingsphere:shardingsphere-sql-parser-spi:jar:4.1.1:compile
|  |  |     +- org.apache.shardingsphere:shardingsphere-sql-parser-statement:jar:4.1.1:compile
|  |  |     +- org.apache.commons:commons-collections4:jar:4.2:compile
|  |  |     \- org.antlr:antlr4-runtime:jar:4.7.2:compile
|  |  +- org.apache.shardingsphere:shardingsphere-sql-parser-mysql:jar:4.1.1:compile
|  |  +- org.apache.shardingsphere:shardingsphere-sql-parser-postgresql:jar:4.1.1:compile
|  |  +- org.apache.shardingsphere:shardingsphere-sql-parser-oracle:jar:4.1.1:compile
|  |  +- org.apache.shardingsphere:shardingsphere-sql-parser-sqlserver:jar:4.1.1:compile
|  |  +- org.apache.shardingsphere:sharding-core-route:jar:4.1.1:compile
|  |  +- org.apache.shardingsphere:master-slave-core-route:jar:4.1.1:compile
|  |  +- org.apache.shardingsphere:sharding-core-rewrite:jar:4.1.1:compile
|  |  +- org.apache.shardingsphere:encrypt-core-rewrite:jar:4.1.1:compile
|  |  +- org.apache.shardingsphere:shadow-core-rewrite:jar:4.1.1:compile
|  |  +- org.apache.shardingsphere:sharding-core-execute:jar:4.1.1:compile
|  |  +- org.apache.shardingsphere:sharding-core-merge:jar:4.1.1:compile
|  |  \- org.apache.shardingsphere:encrypt-core-merge:jar:4.1.1:compile
|  +- com.google.guava:guava:jar:18.0:compile
|  +- org.slf4j:slf4j-api:jar:1.7.7:compile
|  \- org.slf4j:jcl-over-slf4j:jar:1.7.7:compile
+- com.zaxxer:HikariCP-java7:jar:2.4.11:compile
\- mysql:mysql-connector-java:jar:5.1.42:compile
```

