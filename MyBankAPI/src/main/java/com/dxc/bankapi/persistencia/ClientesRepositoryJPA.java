package com.dxc.bankapi.persistencia;

package com.banana.persistence;


import com.banana.models.School;
import com.dxc.bankapi.modelos.clientes.Cliente;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Getter
@Setter
@Repository
public class ClientesRepositoryJPA implements ClienteDBRepositoryInf {
    @PersistenceContext // Accede al emf; emf.createEntityManager();
    private EntityManager em;
    EntityManager em;

    @Override
    @Transactional
    public Cliente add(Cliente cliente) {
//        try {
//            em.getTransaction().begin();
        em.persist(cliente);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            em.getTransaction().rollback();
//            return null;
//        }
        return cliente;
    }

    @Override
    @Transactional
    public Cliente update(Cliente escuela) {
//        try {
//            em.getTransaction().begin();
        Cliente psch = em.find(Cliente.class, cliente.getId());

        psch.setName(escuela.getName());
        //            em.getTransaction().commit();
        return psch;
//        } catch (Exception e) {
//            e.printStackTrace();
//            em.getTransaction().rollback();
//            return null;
//        }
    }

    @Override
    public Cliente getById(Long id) {
        return em.find(Cliente.class, id);
    }
}

