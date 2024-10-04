package br.com.fiap.contatos.dao;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Conexao {

    /* Sempre que criamos uma propriedade ou método estático, estamos dizendo que essa propriedade é da classe,
      assim, qualquer objeto criado a partir dessa classe compartilhará o mesmo atributo/método que é estático,
      e o final garante que o valor dessa propriedade não seja alterado. */

    public static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("contatos");

    public static EntityManager getEntityManager() {            // como o método é estático, não precisamos estanciar um objeto do tipo conexão para consumir esse método.
        return EMF.createEntityManager();
    }

}

