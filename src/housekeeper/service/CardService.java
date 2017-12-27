package housekeeper.service;

import java.util.List;

public interface CardService {

	/**
	 * 增加一张银行卡
	 * 
	 * @param name
	 * @param number
	 * @param money
	 * @param remark
	 * @param memberId
	 * @return
	 */
	String addCard(String name, String number, Double money, String remark, Integer memberId);

	/**
	 * 删除一张银行卡
	 * 
	 * @param id
	 * @return
	 */
	String deleteCard(Integer id);

	/**
	 * 修改某一银行卡信息
	 * 
	 * @param name
	 * @param number
	 * @param money
	 * @param remark
	 * @param id
	 * @return
	 */
	String updateCard(String name, String number, Double money, String remark, Integer id);

	/**
	 * 查询某一成员下所有的银行卡
	 * 
	 * @param memberId
	 * @return
	 */
	List queryByMember(Integer memberId);

	/**
	 * 查询某一银行卡信息
	 * 
	 * @param id
	 * @return
	 */
	List queryById(Integer id);
}
