package com.dxc.bankapi.config;

import com.dxc.bankapi.modelos.clientes.Cliente;
import com.dxc.bankapi.persistence.*;
import com.dxc.bankapi.persistencia.ClienteDBRepositoryInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class ReposConfig {

    @Autowired
    Environment env;

    @Value("${db.conn}")
    String dbUrl;

    @Value("${max.conn}")
    Integer maxConn;

    @PersistenceContext
    EntityManager em;

    @Bean
    @Profile("default")
    public ClienteDBRepositoryInf getClienteRepository() {
        System.out.println("maxConn:" + maxConn);

        String dbUrlEnv = env.getProperty("db.conn", String.class);
        System.out.println("dbUrlEnv:" + dbUrlEnv);

        Clientepository repo = new ClienteRepository();
        repo.setUrlConn(dbUrl);
        return repo;
    }

    @Bean
    @Profile("dev")
    public ClienteRepositoryInf getClienteRepositoryJPA() {
        System.out.println("maxConn JDBC:" + maxConn);

        String dbUrlEnv = env.getProperty("db.conn", String.class);
        System.out.println("dbUrlEnv:" + dbUrlEnv);

        ClienteRepositoryJPA repo = new ClienteRepositoryJPA();
        repo.setEm(em);
        repo.setUrlConn(dbUrl);
        return repo;
    }

    @Bean
    @Profile("dev")
    public ClienteRepositoryInf getclienteRepositoryJPA() {
        ClienteRepositoryJPA repo = new ClienteRepositoryJPA();
        repo.setEm(em);
        return repo;
    }
}
