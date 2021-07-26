package com.vendas.gestaovendas.Utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class BeanSwaggerConfig {
   
	private final String PACKAGE_BUILD = "com.vendas";
	private final String TITLE_API = "Api de gestão de vendas";
	private final String DESCRITION = "api de gerenciamento e vendas de produtos";
	private final String NAME_AUTOR = "Mario Junior";
	private final String VERSION = "1.0.0";
	private final String ENDERECO = "https://github.com/Mario23junior/Gestao-vendas-API";
	private final String EMAIL = "mariojunior3251@gmail.com";
	
   @Bean
   public Docket configuration() {
	   return new Docket(DocumentationType.SWAGGER_2)
				   .select()
				   .apis(RequestHandlerSelectors.basePackage(PACKAGE_BUILD))
				   .build()
				   .apiInfo(informacaoApi());
    }
   
   private ApiInfo informacaoApi() {
	   return new ApiInfoBuilder()
				   .title(TITLE_API)
				   .description(DESCRITION)
				   .version(VERSION)
				   .contact(new Contact(NAME_AUTOR, EMAIL, ENDERECO))
				   .build();
			   
   }
}
