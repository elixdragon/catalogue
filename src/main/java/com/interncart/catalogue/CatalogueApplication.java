package com.interncart.catalogue;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CatalogueApplication {


    public static void main(String[] args) {
        SpringApplication.run(CatalogueApplication.class, args);

    }

    @Bean
    public ObjectNode getObjectNode(){
        return JsonNodeFactory.instance.objectNode();
    }

}
