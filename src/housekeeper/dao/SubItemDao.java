package housekeeper.dao;

import java.util.List;

import housekeeper.entities.SubItem;

public interface SubItemDao {

	/**
	 * 获取所有子类
	 * 
	 * @return
	 */
	List getAll();

	/**
	 * 增加一条子类信息
	 * 
	 * @param subItem
	 */
	void save(SubItem subItem);

	/**
	 * 删除一条子类信息
	 * 
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * 修改一条子类信息
	 * 
	 * @param id
	 * @param name
	 */
	void update(Integer id, String name);

	/**
	 * 查询某一父类下所有子类
	 * 
	 * @param itemId
	 * @return
	 */
	List queryByItem(Integer itemId);

	/**
	 * 通过子类名查询子类
	 * 
	 * @param name
	 * @return
	 */
	List queryByName(String name);

	/**
	 * 查询一条子类信息
	 * 
	 * @param id
	 * @return
	 */
	List queryById(Integer id);
}
