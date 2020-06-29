package com.hyunsiks.spring5.config;

import com.hyunsiks.spring5.main.Client;
import com.hyunsiks.spring5.main.ClientB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCtx {

    @Bean
    public Client client() {
        Client client = new Client();
        client.setHost("Ever");
        return client;
    }

    @Bean(initMethod = "connect", destroyMethod = "close")
    public ClientB clientB() {
        ClientB client = new ClientB();
        client.setHost("EverB");
        return client;
    }
}
