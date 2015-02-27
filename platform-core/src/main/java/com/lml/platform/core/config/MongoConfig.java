package com.lml.platform.core.config;

import com.mongodb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * mongo配置
 *
 * @author lanmingle
 */
@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = {"com.lml.platform.core.repository"})
@PropertySource("classpath:/config/mongo-config.properties")
public class MongoConfig extends AbstractMongoConfiguration {

    /**
     * 日记输出
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * mongo连接的数据库名称配置文件键值
     */
    private static final String MONGO_CONNECTION_DATABASE_NAME_KEY = "mongo.connection.database.name";
    /**
     * mongo连接的地址配置文件键值
     */
    private static final String MONGO_CONNECTION_HOST_KEY = "mongo.connection.host";
    /**
     * mongo连接的端口配置文件键值
     */
    private static final String MONGO_CONNECTION_PORT_KEY = "mongo.connection.port";
    /**
     * mongo连接的用户凭证用户名配置文件键值
     */
    private static final String MONGO_CONNECTION_USERNAME_KEY = "mongo.connection.username";
    /**
     * mongo连接的用户凭证密码配置文件键值
     */
    private static final String MONGO_CONNECTION_PASSWORD_KEY = "mongo.connection.password";


    /**
     * mongo可选配置连接的最小线程键值
     */
    private static final String MONGO_OPTIONS_CONNECTIONS_PER_HOST_KEY = "mongo.options.connections.per.host";
    /**
     * mongo可选配置连接的最大线程键值
     */
    private static final String MONGO_OPTIONS_THREADS_ALLOWED_TO_BLOCK_FOR_CONNECTION_MULTIPLIER_KEY = "mongo.options.threads.allowed.to.block.for.connection.multiplier";
    /**
     * mongo可选配置线程等待链接可用的最长时间键值
     */
    private static final String MONGO_OPTIONS_MAX_WAIT_TIME_KEY = "mongo.options.max.wait.time";
    /**
     * mongo可选配置建立链接的超时时间键值
     */
    private static final String MONGO_OPTIONS_CONNECT_TIMEOUT_KEY = "mongo.options.connect.timeout";
    /**
     * mongo可选配置执行io操作的超时时间键值
     */
    private static final String MONGO_OPTIONS_SOCKET_TIMEOUT_KEY = "mongo.options.socket.timeout";
    /**
     * mongo可选配置为防火墙设置的，保证socket存活键值
     */
    private static final String MONGO_OPTIONS_SOCKET_KEEP_ALIVE_KEY = "mongo.options.socket.keep.alive";
    /**
     * mongo可选配置总是使用Mbeans键值
     */
    private static final String MONGO_OPTIONS_ALWAYS_USE_M_BEANS_KEY = "mongo.options.always.use.m.beans";

    /**
     * mongo可选配置核心线程数键值
     */
    private static final String MONGO_OPTIONS_HEARTBEAT_THREAD_COUNT_KEY = "mongo.options.heartbeat.thread.count";


    /**
     * mongo可选配置连接的最小线程,默认值:15
     */
    private static final int MONGO_OPTIONS_CONNECTIONS_PER_HOST_DEFAULT_VALUE = 15;
    /**
     * mongo可选配置连接的最大线程,默认值:50
     */
    private static final int MONGO_OPTIONS_THREADS_ALLOWED_TO_BLOCK_FOR_CONNECTION_MULTIPLIER_DEFAULT_VALUE = 50;

    /**
     * mongo可选配置线程等待链接可用的最长时间,默认值:3分钟=1000*60*3
     */
    private static final int MONGO_OPTIONS_MAX_WAIT_TIME_DEFAULT_VALUE = 1000 * 60 * 3;
    /**
     * mongo可选配置建立链接的超时时间,默认值:30秒=1000*30
     */
    private static final int MONGO_OPTIONS_CONNECT_TIMEOUT_DEFAULT_VALUE = 1000 * 30;

    /**
     * mongo可选配置执行io操作的超时时间,默认值:0 ,代表不超时
     */
    private static final int MONGO_OPTIONS_SOCKET_TIMEOUT_DEFAULT_VALUE = 0;

    /**
     * mongo可选配置为防火墙设置的，保证socket存活,默认值是:true
     */
    private static final boolean MONGO_OPTIONS_SOCKET_KEEP_ALIVE_DEFAULT_VALUE = true;

    /**
     * mongo可选配置总是使用Mbeans ,默认值是:false
     */
    private static final boolean MONGO_OPTIONS_ALWAYS_USE_M_BEANS_DEFAULT_VALUE = false;

    /**
     * mongo可选配置核心线程数,默认值:15
     */
    private static final int MONGO_OPTIONS_HEARTBEAT_THREAD_COUNT_DEFAULT_VALUE = 15;

    /**
     * spring环境
     */
    @Autowired
    private Environment env;

    /**
     * 返回数据库连接的名称。
     *
     * @return 不能为 {@literal null}.
     */
    @Override
    protected String getDatabaseName() {
        String databaseName = env.getRequiredProperty(MONGO_CONNECTION_DATABASE_NAME_KEY); //获取配置文件中的连接数据库名称
        logger.debug("support connection database name is :{}", databaseName);
        return databaseName;
    }

    /**
     * 返回mongo实例连接对象.
     *
     * @return mongo实例连接对象
     * @throws Exception 连接mongo失败.
     */
    @Override
    @Bean
    public Mongo mongo() throws Exception {
        MongoClientOptions.Builder builder = MongoClientOptions.builder(); //mongo客户端可选配置构建器

        Integer connectionsPerHost = env.getProperty(MONGO_OPTIONS_CONNECTIONS_PER_HOST_KEY, Integer.class, MONGO_OPTIONS_CONNECTIONS_PER_HOST_DEFAULT_VALUE);
        Integer threadsAllowedToBlockForConnectionMultiplier = env.getProperty(MONGO_OPTIONS_THREADS_ALLOWED_TO_BLOCK_FOR_CONNECTION_MULTIPLIER_KEY, Integer.class, MONGO_OPTIONS_THREADS_ALLOWED_TO_BLOCK_FOR_CONNECTION_MULTIPLIER_DEFAULT_VALUE);
        Integer maxWaitTime = env.getProperty(MONGO_OPTIONS_MAX_WAIT_TIME_KEY, Integer.class, MONGO_OPTIONS_MAX_WAIT_TIME_DEFAULT_VALUE);
        Integer connectTimeout = env.getProperty(MONGO_OPTIONS_CONNECT_TIMEOUT_KEY, Integer.class, MONGO_OPTIONS_CONNECT_TIMEOUT_DEFAULT_VALUE);
        Integer socketTimeout = env.getProperty(MONGO_OPTIONS_SOCKET_TIMEOUT_KEY, Integer.class, MONGO_OPTIONS_SOCKET_TIMEOUT_DEFAULT_VALUE);
        Boolean socketKeepAlive = env.getProperty(MONGO_OPTIONS_SOCKET_KEEP_ALIVE_KEY, Boolean.class, MONGO_OPTIONS_SOCKET_KEEP_ALIVE_DEFAULT_VALUE);
        WriteConcern writeConcern = WriteConcern.REPLICAS_SAFE; //数据库的写操作分几个安全级别,为最好级别
        Boolean alwaysUseMBeans = env.getProperty(MONGO_OPTIONS_ALWAYS_USE_M_BEANS_KEY, Boolean.class, MONGO_OPTIONS_ALWAYS_USE_M_BEANS_DEFAULT_VALUE);
        Integer heartbeatThreadCount = env.getProperty(MONGO_OPTIONS_HEARTBEAT_THREAD_COUNT_KEY, Integer.class, MONGO_OPTIONS_HEARTBEAT_THREAD_COUNT_DEFAULT_VALUE);

        builder.connectionsPerHost(connectionsPerHost);//单个主机连接到mongo实例允许的最大连接数。这其实相当于c3p0的maxPoolSize参数，mongo是内建连接池的，功能跟c3p0等差不多，超过了就会新建链接，默认值是10，大并发的话较小。
        builder.threadsAllowedToBlockForConnectionMultiplier(threadsAllowedToBlockForConnectionMultiplier);//这个参数是跟connectionsPerHost配套的，当连接数超过connectionsPerHost的时候，需要建立新的连接，连接请求会被阻塞，这个参数就代表允许阻塞请求的最大值，超过这个值之后的请求都会报错。默认值5。
        builder.maxWaitTime(maxWaitTime);//线程等待链接可用的最长时间，ms单位，默认120,000，两分钟。
        builder.connectTimeout(connectTimeout);//建立链接的超时时间。默认为10,000，10s（2.9.0）
        builder.socketTimeout(socketTimeout);//执行io操作的超时时间，默认为0，代表不超时。
        builder.socketKeepAlive(socketKeepAlive);//为防火墙设置的，保证socket存活。默认false。
        builder.writeConcern(writeConcern);//数据库的写操作分几个安全级别
        builder.alwaysUseMBeans(alwaysUseMBeans);//总是使用Mbeans
        builder.heartbeatThreadCount(heartbeatThreadCount);//核心线程数

        //设置一些可选的配置
        MongoClientOptions options = builder.build(); //构建mongo可选配置对象
        String host = env.getRequiredProperty(MONGO_CONNECTION_HOST_KEY);//获取配置文件中的连接地址
        Integer port = env.getRequiredProperty(MONGO_CONNECTION_PORT_KEY, Integer.class); //获取配置文件中的连接端口
        logger.debug("support connection host is :{}", host);
        logger.debug("support connection port is :{}", port);
        ServerAddress serverAddress = new ServerAddress(host, port);//构建mongo服务地址对象.使用默认的连接地址和端口
        Mongo mongo = new MongoClient(serverAddress, options); //构建mongo客户端
        return mongo;
    }

    /**
     * 返回连接mongo数据库用户凭证
     *
     * @return 连接mongo数据库用户凭证
     */
    @Override
    protected UserCredentials getUserCredentials() {
        String username = env.getRequiredProperty(MONGO_CONNECTION_USERNAME_KEY);//获取配置文件中的连接用户名
        String password = env.getRequiredProperty(MONGO_CONNECTION_PASSWORD_KEY);//获取配置文件中的连接密码
        logger.debug("support connection username is :{}", username);
        logger.debug("support connection password is :{}", password);
        UserCredentials userCredentials = new UserCredentials(username, password); //构建一个mongo的用户凭证
        return userCredentials;
    }

}
