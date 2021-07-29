package com.sistema.delivery.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistema.delivery.domian.PagamentoComBoleto;
import com.sistema.delivery.domian.PagamentoComCartao;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

	// metodo para criar tributo no json
	@Bean
	public Jackson2ObjectMapperBuilder objetoMapperBuild(){
		Jackson2ObjectMapperBuilder build = new Jackson2ObjectMapperBuilder () {
			public void configure(ObjectMapper obj){
				obj.registerSubtypes(PagamentoComCartao.class);
				obj.registerSubtypes(PagamentoComBoleto.class);
				super.configure(obj);
			}
		};
		return build;
	}

	@Bean
   	public ModelMapper modelMapper() {
      ModelMapper modelMapper = new ModelMapper();
      return modelMapper;
   }
}
