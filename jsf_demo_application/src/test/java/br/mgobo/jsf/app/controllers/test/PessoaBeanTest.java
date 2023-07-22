package br.mgobo.jsf.app.controllers.test;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class PessoaBeanTest {

    @Mock
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JSFAPPLICATIONPU");
    @Mock
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Test
    public void naoDeveSalvarSemNome() {
        System.out.println(entityManager.toString());
    }
}
