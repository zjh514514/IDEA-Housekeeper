package housekeeper.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import housekeeper.dao.ItemDao;
import housekeeper.entities.Item;
import housekeeper.tools.HibernateTools;

@Repository
public class ItemDaoImpl extends HibernateTools implements ItemDao {

	private String hql;

	@Override
	public List getAll() {
		hql = "FROM Item";
		return getSession().createQuery(hql).list();
	}

	@Override
	public void save(Item item) {
		getSession().save(item);
	}

	@Override
	public void delete(Integer id) {
		hql = "DELETE FROM Item i WHERE i.itemId= ? ";
		getSession().createQuery(hql).setParameter(0, id).executeUpdate();
	}

	@Override
	public void update(Integer id, String name) {
		hql = "UPDATE Item i SET i.itemName = ? WHERE i.itemId = ?";
		getSession().createQuery(hql).setParameter(0, name).setParameter(1, id).executeUpdate();
	}

	@Override
	public List queryByType(Integer type) {
		hql = "FROM Item i WHERE i.type= ?";
		return getSession().createQuery(hql).setParameter(0, type).list();
	}

	@Override
	public List queryByName(String name) {
		hql = "FROM Item i WHERE i.itemName= ?";
		return getSession().createQuery(hql).setParameter(0, name).list();
	}

	@Override
	public List queryById(Integer id) {
		hql = "FROM Item i WHERE i.itemId= ?";
		return getSession().createQuery(hql).setParameter(0, id).list();
	}

}
