package com.forvy.spring.datajpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.forvy.spring.datajpa.util.ConvertSVY21;

@Configuration
public class AppConfig {
  @Bean
  public ConvertSVY21 convertSVY21() {
    return new ConvertSVY21();
  }
}
