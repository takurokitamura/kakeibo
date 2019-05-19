package com.example.demo.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 優先度。
 */
@Component
@ConfigurationProperties(prefix = "category")
@PropertySource(value = "classpath:code.properties", encoding = "UTF-8")
public class Category {




    /** 優先度管理用Map */
    private Map<String, String> category;

    public Map<String, String> getCategory() {
        return category;
    }

    public void setCategory(Map<String, String> category) {
        this.category = category;
    }
}
