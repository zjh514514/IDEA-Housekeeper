package housekeeper.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import housekeeper.dao.CashOutDao;
import housekeeper.entities.CashOut;
import housekeeper.tools.HibernateTools;

@Repository
public class CashOutDaoImpl extends HibernateTools implements CashOutDao {

    private String hql;

    @Override
    public void save(CashOut cashOut) {
        getSession().save(cashOut);
    }

    @Override
    public void delete(Integer id) {
        hql = "DELETE FROM CashOut c WHERE c.cashOutId = ?";
        getSession().createQuery(hql).setParameter(0, id).executeUpdate();
    }

    @Override
    public void update(CashOut cashOut) {
        hql = "UPDATE CashOut c SET c.time = ? , c.site = ? , c.people = ? , c.money = ? , c.remark = ? , c.item = ? , c.subItem = ? , c.account = ? WHERE c.cashOutId = ?";
        getSession().createQuery(hql).setParameter(0, cashOut.getTime()).setParameter(1, cashOut.getSite())
                .setParameter(2, cashOut.getPeople()).setParameter(3, cashOut.getMoney())
                .setParameter(4, cashOut.getRemark()).setParameter(5, cashOut.getItem())
                .setParameter(6, cashOut.getSubItem()).setParameter(7, cashOut.getAccount())
                .setParameter(8, cashOut.getCashOutId()).executeUpdate();
    }

    @Override
    public List queryByMember(Integer memberId) {
        hql = "FROM CashOutQuery c WHERE c.id.memberId = ?";
        return getSession().createQuery(hql).setParameter(0, memberId).list();
    }

    @Override
    public List queryByItem(Integer itemId, Integer memberId) {
        hql = "FROM CashOutQuery c WHERE c.id.itemId = ? AND c.id.memberId = ? ";
        return getSession().createQuery(hql).setParameter(0, itemId).setParameter(1, memberId).list();
    }

    @Override
    public List queryBySubItem(Integer subItemId, Integer memberId) {
        hql = "FROM CashOutQuery c WHERE c.id.subitemId = ? AND c.id.memberId = ? ";
        return getSession().createQuery(hql).setParameter(0, subItemId).setParameter(1, memberId).list();
    }

    @Override
    public List queryById(Integer id) {
        hql = "FROM CashOutQuery c WHERE c.id.cashoutId = ?";
        return getSession().createQuery(hql).setParameter(0, id).list();
    }

    @Override
    public List queryByAccount(Integer accountId, Integer memberId) {
        hql = "FROM CashOutQuery c WHERE c.id.accountId = ? AND c.id.memberId = ?";
        return getSession().createQuery(hql).setParameter(0, accountId).setParameter(1, memberId).list();
    }

    @Override
    public List queryByTime(Integer memberId, Date startTime, Date endTime) {
        hql = "FROM CashOutQuery c WHERE c.id.memberId = ? AND c.id.time BETWEEN ? AND ?";
        return getSession().createQuery(hql).setParameter(0, memberId).setParameter(1, startTime).setParameter(2, endTime).list();
    }

    @Override
    public Double sumCashOut(Integer memberId, Date startTime, Date endTime) {
        hql = "SELECT SUM(c.id.money) FROM CashOutQuery c WHERE c.id.memberId = ? AND c.id.time BETWEEN ? AND ?";
        return (Double) getSession().createQuery(hql).setParameter(0, memberId).setParameter(1, startTime).setParameter(2, endTime).getSingleResult();
    }

    @Override
    public List queryByFamily(Integer familyId, Date startTime, Date endTime) {
        hql = "SELECT * FROM CashOutQuery WHERE TIME BETWEEN ? AND ? AND MEMBER_ID IN ( SELECT MEMBER_ID FROM MEMBER WHERE FAMILY_ID IN ( SELECT FAMILY_ID FROM FAMILY WHERE FAMILY_ID = ? ) )";
        return getSession().createSQLQuery(hql).setParameter(0, startTime).setParameter(1, endTime).setParameter(2, familyId).list();
    }
}
