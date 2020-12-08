package com.microtest.exception_handle_project.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * Druid的DataResource配置类
 * 凡是被Spring管理的类，实现接口 EnvironmentAware 重写方法 setEnvironment 可以在工程启动时，
 * 获取到系统环境变量和application配置文件中的变量。还有一种方式是采用注解的方式获取 @value("${变量的key值}")
 * 获取application配置文件中的变量。这里采用第一种要方便些
 */
@Configuration
@EnableTransactionManagement
public class DruidConfig {
    /**
     * 因为Springboot内置了servlet容器，所以没有web.xml，替代方法就是将ServletRegistrationBean注册进去
     * 加入后台监控
     * 这里其实就相当于servlet的web.xml
     * @return
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> druidServlet() {
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<StatViewServlet>();
        servletRegistrationBean.setServlet(new StatViewServlet());
        //后台需要有人登录，进行配置
        servletRegistrationBean.addUrlMappings("/druid/*");
        Map<String, String> initParameters = new HashMap<String, String>(8);
        // 用户名
        initParameters.put("loginUsername", "admin");
        // 密码
        initParameters.put("loginPassword", "admin");
        // 禁用HTML页面上的“Reset All”功能
        initParameters.put("resetEnable", "false");
        // IP白名单 (没有配置或者为空，则允许所有访问)
        initParameters.put("allow", "");
        // IP黑名单
        /*initParameters.put("deny", "192.168.20.38");*/
        initParameters.put("logSlowSql", "true");
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }

    /**
     * 注册一个：DruidWebStatFilter
     * @return
     */
    @Bean
    public FilterRegistrationBean druidWebStatFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
        //可以设置也可以获取,设置一个阿里巴巴的过滤器
        filterRegistrationBean.setFilter(new WebStatFilter());
        // 添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        // 添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        // session最大数量配置, 缺省1000
        filterRegistrationBean.addInitParameter("sessionStatMaxCount", "1000");
        // 开启session统计功能
        filterRegistrationBean.addInitParameter("sessionStatEnable", "true");
        // 使得druid能够知道当前的session的用户是谁.根据需要，把其中的xxx.user修改为你user信息保存在session中的sessionName.
        // 注意：如果你session中保存的是非string类型的对象，需要重载toString方法。
        filterRegistrationBean.addInitParameter("principalSessionName", "user");
        // 能够监控单个url调用的sql列表
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        //过滤器顺序
        filterRegistrationBean.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        return filterRegistrationBean;
    }
}
