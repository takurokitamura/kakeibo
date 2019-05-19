package com.example.demo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
     * 優先度。
     */
    @Component
    public class CodeValue {
        private Category category;

        @Autowired
        public CodeValue(Category category) {
            this.category = category;
        }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

