
package ro.utcluj.student.twiss.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ro.utcluj.student.twiss.configuration.interceptors.SecurityInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(
            final InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityInterceptor())//
                .addPathPatterns("/**")//
                .excludePathPatterns("/login-page", "/login","/error", "/logout");
    }


}