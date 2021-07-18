package com.vendas.gestaovendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.vendas.gestaovendas.model"})
@EnableJpaRepositories(basePackages = {"com.vendas.gestaovendas.repository"})
@ComponentScan(basePackages = {"com.vendas.gestaovendas.Service" ,"com.vendas.gestaovendas.Controller"})
@SpringBootApplication
public class GestaoVendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoVendasApplication.class, args);
	}

}
