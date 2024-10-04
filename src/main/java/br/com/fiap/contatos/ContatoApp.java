package br.com.fiap.contatos;

import br.com.fiap.contatos.dao.Conexao;
import br.com.fiap.contatos.dao.ContatoDao;
import br.com.fiap.contatos.model.Contato;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class ContatoApp {

    public static void main(String[] args) {

        EntityManager em = Conexao.getEntityManager();      // consigo usar o método sem ter que instanciar o objeto

        //cadastrar(em);
        //atualizar(em);
        //excluir(em);
        //consultarContatoPorId(em);
        //listarTodosContatos(em);
        listarContatosPeloEmail(em);
    }

    public static void listarContatosPeloEmail(EntityManager em) {
        // Criar instância do DAO
        ContatoDao contatoDao = new ContatoDao(em);

        List<Contato> contatos = contatoDao.listarContatosPorEmail("fabiana@email.com.br");

        //Para cada contato na lista de contatos, será feita a listagem com as colunas com o email passado
        for(Contato contato : contatos) {
            System.out.println("------------------------------------");
            System.out.println(contato.toString());
        }

        System.out.println("Fim dos registros...");

    }

    public static void cadastrar(EntityManager em) {
        Contato contato  = new Contato();
        contato.setNome("Vinicius Figueiredo");
        contato.setEmail("vinicius.figueiredo@email.com.br");
        contato.setDataNascimento(LocalDate.of(1995,3,15));

        // Criar uma instância do Dao
        ContatoDao contatoDao = new ContatoDao(em);         // passar o em ao instanciar

        em.getTransaction().begin();
        //em.persist(contato);  agora a classe ContatoDao fará a persistência:
        contatoDao.salvar(contato);                          // chamada do método salvar
        em.getTransaction().commit();
        em.close();

    }

    public static void atualizar(EntityManager em) {
        Contato contato  = new Contato();
        contato.setId(5L);                                  // diferenciar Long de Integer, utiliza o ID para achar a coluna no banco
        contato.setNome("Ana Maria");
        contato.setEmail("ana.maria@email.com.br");
        contato.setDataNascimento(LocalDate.of(2005,4,6));

        // Criar uma instância do Dao
        ContatoDao contatoDao = new ContatoDao(em);         // passar o em ao instanciar

        em.getTransaction().begin();
        contatoDao.atualizar(contato);                      // chamada do método atualizar
        em.getTransaction().commit();
        em.close();

    }

    public static void excluir(EntityManager em) {
        Contato contato  = new Contato();
        contato.setId(4L);                                  // diferenciar Long de Integer, utiliza o ID para achar a coluna no banco

        // Criar uma instância do Dao
        ContatoDao contatoDao = new ContatoDao(em);         // passar o em ao instanciar

        em.getTransaction().begin();
        contatoDao.excluir(contato);                        // chamada do método atualizar
        em.getTransaction().commit();
        em.close();

    }

    public static void consultarContatoPorId(EntityManager em) {

        // Criar uma instância do Dao
        ContatoDao contatoDao = new ContatoDao(em);         // passar o em ao instanciar

        em.getTransaction().begin();
        contatoDao.consultarContatoPorId(5L);                        // chamada do método atualizar
        em.getTransaction().commit();
        em.close();

    }

    public static void listarTodosContatos(EntityManager em) {
        // Criar instância do DAO
        ContatoDao contatoDao = new ContatoDao(em);

        List<Contato> contatos = contatoDao.listarTodosContatos();

        for(Contato contato : contatos) {
            System.out.println("------------------------------------");
            System.out.println(contato.toString());
        }

    }

}
