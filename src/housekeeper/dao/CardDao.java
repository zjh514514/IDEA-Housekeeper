package housekeeper.dao;

import java.util.List;

import housekeeper.entities.Card;

public interface CardDao {

	/**
	 * 保存一张银行卡
	 * 
	 * @param card
	 */
	void save(Card card);

	/**
	 * 删除一张银行卡
	 * 
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * 修改一张银行卡的信息
	 * 
	 * @param card
	 */
	void update(Card card);

	/**
	 * 查询某一成员的所有银行卡
	 * 
	 * @param memberId
	 * @return
	 */
    List queryByMember(Integer memberId);

	/**
	 * 查询某一张银行卡
	 * 
	 * @param id
	 * @return
	 */
    List queryById(Integer id);
}
