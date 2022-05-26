package net.betvictor.core.config;

import feign.Request;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.TimeUnit;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Configuration
public class FeignClientConfig {
  public static final int TEN_SECONDS = 10;

  @Bean
  public Request.Options options() {
    return new Request.Options(TEN_SECONDS, TimeUnit.SECONDS, TEN_SECONDS, TimeUnit.SECONDS, false);
  }

  @Bean
  @Scope(SCOPE_PROTOTYPE)
  Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> messageConverters) {
    return new FormEncoder(new SpringEncoder(messageConverters));
  }
}
