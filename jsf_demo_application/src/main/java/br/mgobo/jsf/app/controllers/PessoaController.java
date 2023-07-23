package br.mgobo.jsf.app.controllers;

import br.mgobo.jsf.app.controllers.impl.CrudImpl;
import br.mgobo.jsf.app.domain.Endereco;
import br.mgobo.jsf.app.domain.Endereco_;
import br.mgobo.jsf.app.domain.Pessoa;
import br.mgobo.jsf.app.domain.Pessoa_;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.transaction.UserTransaction;
import java.util.ArrayList;
import java.util.List;


public class PessoaController implements CrudImpl<Pessoa> {

    protected EntityManager entityManager;
    protected UserTransaction userTransaction;

    @Override
    public Pessoa merge(Pessoa pessoa) throws Exception {
        try {
            userTransaction.begin();
            pessoa = this.entityManager.merge(pessoa);
            userTransaction.commit();
            return pessoa;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Pessoa findById(Long id) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Pessoa> cq = cb.createQuery(Pessoa.class);
        Root<Pessoa> from = cq.from(Pessoa.class);
        from.join(Pessoa_.endereco, JoinType.INNER);
        return this.entityManager.createQuery(cq.select(from).where(cb.equal(from.get(Pessoa_.id), id))).getSingleResult();
    }

    @Override
    public List<Pessoa> findAll() {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Pessoa> cq = cb.createQuery(Pessoa.class);
        Root<Pessoa> from = cq.from(Pessoa.class);
        from.join(Pessoa_.endereco, JoinType.INNER);
        return this.entityManager.createQuery(cq.select(from).orderBy(cb.asc(from.get(Pessoa_.nome)))).getResultList();
    }

    @Override
    public Pessoa delete(Pessoa pessoa) throws Exception {
        this.userTransaction.begin();
        Pessoa p = this.entityManager.merge(pessoa);
        this.entityManager.remove(p);
        this.userTransaction.commit();
        return pessoa;
    }

    public List<Pessoa> buscarPorFiltros(Pessoa pessoa, Endereco endereco) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Pessoa> cq = cb.createQuery(Pessoa.class);
        Root<Pessoa> from = cq.from(Pessoa.class);
        Join<Pessoa, Endereco> enderecoJoin = from.join(Pessoa_.endereco, JoinType.INNER);

        Predicate predicate = null;
        List<Predicate> predicates = this.construirPredicate(cb, from, enderecoJoin, pessoa, endereco);
        if (!predicates.isEmpty()) {
            if (predicates.size() > 1) {
                predicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
            } else {
                predicate = predicates.get(0);
            }
        }
        List<Pessoa> pessoaCollection = new ArrayList<>();
        if (predicate != null) {
            pessoaCollection.addAll(this.entityManager.createQuery(cq.select(from).where(predicate).orderBy(cb.asc(from.get(Pessoa_.nome)))).getResultList());
        } else {
            pessoaCollection.addAll(this.entityManager.createQuery(cq.select(from).orderBy(cb.asc(from.get(Pessoa_.nome)))).getResultList());
        }
        return pessoaCollection;
    }

    private List<Predicate> construirPredicate(CriteriaBuilder cb, Root from, Join<Pessoa, Endereco> enderecoJoin, Pessoa pessoa, Endereco endereco) {
        List<Predicate> predicates = new ArrayList<>();
        if (pessoa != null) {
            if (pessoa.getNome() != null && !pessoa.getNome().equals(""))
                predicates.add(cb.like(cb.upper(from.get(Pessoa_.nome)), pessoa.getNome().toUpperCase() + "%"));
            if (pessoa.getSexo() != null && !pessoa.getSexo().equals(""))
                predicates.add(cb.equal(from.get(Pessoa_.sexo), pessoa.getSexo()));
        }
        if (endereco != null) {
            if (endereco.getLogradouro() != null && !endereco.getLogradouro().equals(""))
                predicates.add(cb.like(cb.upper(enderecoJoin.get(Endereco_.logradouro)), "%" + endereco.getLogradouro().toUpperCase() + "%"));
            if (endereco.getNro() != null)
                predicates.add(cb.equal(enderecoJoin.get(Endereco_.nro), endereco.getNro()));
            if (endereco.getEstado() != null && !endereco.getEstado().equals(""))
                predicates.add(cb.equal(cb.upper(enderecoJoin.get(Endereco_.estado)), endereco.getEstado().toUpperCase()));
            if (endereco.getCidade() != null && !endereco.getCidade().equals(""))
                predicates.add(cb.like(cb.upper(enderecoJoin.get(Endereco_.cidade)), "%" + endereco.getCidade().toUpperCase() + "%"));
        }
        return predicates;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void setUserTransaction(UserTransaction userTransaction) {
        this.userTransaction = userTransaction;
    }
}
