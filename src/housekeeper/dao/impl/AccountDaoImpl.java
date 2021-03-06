package housekeeper.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import housekeeper.dao.AccountDao;
import housekeeper.entities.Account;
import housekeeper.tools.HibernateTools;

@Repository
public class AccountDaoImpl extends HibernateTools implements AccountDao {

    private String hql;

    @Override
    public void save(Account account) {
        getSession().save(account);
    }

    @Override
    public void delete(Integer accountId) {
        hql = "DELETE FROM Account a WHERE a.accountId = ?";
        getSession().createQuery(hql).setParameter(0, accountId).executeUpdate();
    }

    @Override
    public void update(Account account) {
        hql = "UPDATE Account a SET a.accountName = ? WHERE a.accountId = ?";
        getSession().createQuery(hql).setParameter(0, account.getAccountName()).setParameter(1, account.getAccountId())
                .executeUpdate();
    }

    @Override
    public List getAll() {
        hql = "FROM Account";
        return getSession().createQuery(hql).list();
    }

    @Override
    public List queryByName(String name) {
        hql = "FROM Account a WHERE a.accountName= ? ";
        return getSession().createQuery(hql).setParameter(0, name).list();
    }

    @Override
    public List queryById(Integer id) {
        hql = "FROM Account a WHERE a.id = ?";
        return getSession().createQuery(hql).setParameter(0, id).list();
    }

}
