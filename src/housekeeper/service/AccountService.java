package housekeeper.service;

import java.util.List;

public interface AccountService {

	/**
	 * 增加账户选项
	 * 
	 * @param name
	 * @return
	 */
	String addAccount(String name);

	/**
	 * 删除账户选项
	 * 
	 * @param id
	 * @return
	 */
	String delete(Integer id);

	/**
	 * 修改账户选项
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	String updateAccount(Integer id, String name);

	/**
	 * 获取所有账户选项
	 * 
	 * @return
	 */
	List query();

}
