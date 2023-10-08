package com.forvy.spring.datajpa.testconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.forvy.spring.datajpa.util.ConvertSVY21;

@TestConfiguration
public class TestConfig {
  @Bean
  @ConditionalOnMissingBean
  public ConvertSVY21 convertSVY21() {
    return new ConvertSVY21();
  }
}
