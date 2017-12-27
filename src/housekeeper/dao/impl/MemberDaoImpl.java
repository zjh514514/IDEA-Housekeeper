package housekeeper.dao.impl;

import housekeeper.dao.MemberDao;
import housekeeper.entities.Member;
import housekeeper.entities.MemberQuery;
import housekeeper.tools.HibernateTools;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDaoImpl extends HibernateTools implements MemberDao {

    private String hql;

    @Override
    public void save(Member member) {
        getSession().save(member);
    }

    @Override
    public void delete(Integer id) {
        hql = "DELETE FROM Member m WHERE m.memberId = ?";
        getSession().createQuery(hql).setParameter(0, id).executeUpdate();
    }

    @Override
    public void update(Member member) {
        hql = "UPDATE Member m SET m.password = ? , m.balance = ? , m.name = ? , m.role = ? WHERE m.memberId = ?";
        getSession().createQuery(hql).setParameter(0, member.getPassword()).setParameter(1, member.getBalance())
                .setParameter(2, member.getName()).setParameter(3, member.getRole())
                .setParameter(4, member.getMemberId()).executeUpdate();
    }

    @Override
    public List getAll() {
        hql = "FROM MemberQuery";
        return getSession().createQuery(hql).list();
    }

    @Override
    public MemberQuery queryByUsername(String username) {
        hql = "FROM MemberQuery m WHERE m.id.username = ?";
        return (MemberQuery) getSession().createQuery(hql).setParameter(0, username).uniqueResult();
    }

    @Override
    public List queryById(Integer id) {
        hql = "FROM MemberQuery m WHERE m.id.memberId = ?";
        return getSession().createQuery(hql).setParameter(0, id).list();
    }

    @Override
    public List queryByFamily(Integer familyId) {
        hql = "FROM MemberQuery m WHERE m.id.familyId = ?";
        return getSession().createQuery(hql).setParameter(0, familyId).list();
    }

}
