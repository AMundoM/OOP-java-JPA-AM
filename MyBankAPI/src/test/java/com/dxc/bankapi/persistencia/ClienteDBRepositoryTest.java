package com.dxc.bankapi.persistencia;

import com.dxc.bankapi.modelos.clientes.Cliente;
import com.dxc.bankapi.modelos.clientes.Empresa;
import com.dxc.bankapi.modelos.clientes.Personal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClienteDBRepositoryTest {

    private IClientesRepo repo;

    @BeforeEach
    void setUp() throws Exception {
//        repo = new UsuarioInMemoryRepository();
        repo = new ClienteDBRepository();


    }

    @Test
    void getAll() throws Exception {
        List <Cliente> lc = repo.getAll();
        assertNotNull(lc);
        System.out.println("Lista Cliente:"+lc);

    }

    @Test
    void getClientById() {
    }

    @Test
    void addClient_personal() throws Exception {
        Cliente c = new Personal(null, "Juan Juanez", "jj@j.com", "Calle JJ 1", LocalDate.now(), true, false, "12345678J");
        c = repo.addClient(c);
        assertNotNull(c.getId());
    }
    @Test
    void addClient_empresa() throws Exception {
        Cliente c = new Empresa(null, "Servicios Informatico SL", "si@s.com", "Calle SI 3", LocalDate.now(), true, false, "J12345678", new String[]{"Dev", "Marketing"});
        c = repo.addClient(c);
        assertNotNull(c.getId());
    }

    @Test
    void deleteClient() {
    }

    @Test
    void updateClient() {
    }
}