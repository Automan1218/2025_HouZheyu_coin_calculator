package com.example;

import com.example.api.CoinApi;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterRegistration;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import java.util.EnumSet;

public class coin_calculatorApplication extends Application<coin_calculatorConfiguration> {

    public static void main(final String[] args) throws Exception {
        new coin_calculatorApplication().run(args);
    }

    @Override
    public String getName() {
        return "coin_calculator";
    }

    @Override
    public void initialize(final Bootstrap<coin_calculatorConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final coin_calculatorConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new CoinApi());

        // 添加 CORS 支持
        final FilterRegistration.Dynamic cors = environment.servlets()
                .addFilter("CORS", CrossOriginFilter.class);

        // 配置 CORS
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*"); // 允许所有来源
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,POST,PUT,DELETE,HEAD");

        // 应用到所有路径
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

}
