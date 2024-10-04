package br.com.fiap.contatos.dao;

import br.com.fiap.contatos.model.Contato;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ContatoDao {                               // criação de uma classe DAO que faz a persistencia do objeto Contato no banco de dados

    private EntityManager em;                           // propriedade da classe ContatoDao, privada e encapsulada, não conseguimos usá-la de fora

    public ContatoDao(EntityManager em) {               // toda vez que um contatoDao for criado, pediremos para ser fornecido o entity manager
        this.em = em;                                   // o em da instância recebe o em passado no construtor
    }

    public void salvar(Contato contato) {               // qualquer parte do código que chamar esse método salvar, passará o contato e utilizará o manager para realizar a persistência no banco
        em.persist(contato);
    }

    public void atualizar(Contato contato) {            // criar a atualização de um registro já existente no banco
        em.merge(contato);
    }

    public void excluir(Contato contato) {
        Contato contatoExcluir = em.find(Contato.class, contato.getId());  // procura um objeto no banco com o Id passado
        em.remove(contatoExcluir);
    }

    public void consultarContatoPorId(Long id){
        Contato contatoConsulta = em.find(Contato.class, id);

        if (contatoConsulta == null) {
            System.out.println("Contato não encontrado!");
        } else {
            System.out.println("-----------------------------------------");
            System.out.println(contatoConsulta.toString());
            System.out.println("-----------------------------------------");
        }
    }

    public List<Contato> listarTodosContatos(){
        // SELECT * FROM tbl_contatos ORDER BY nome ASC

        // JPQL estamos no java, então chamamos a CLASSE e não o nome da TABELA
        String consulta = "SELECT c FROM Contato c ORDER BY nome ASC";

        return em.createQuery(consulta).getResultList();

    }

    public List<Contato> listarContatosPorEmail(String email){
        String consulta = "SELECT c FROM Contato c WHERE email = :email";                   //:email é o nome parâmetro

        return em.createQuery(consulta, Contato.class)
                .setParameter("email", email)
                .getResultList();
    }

}
