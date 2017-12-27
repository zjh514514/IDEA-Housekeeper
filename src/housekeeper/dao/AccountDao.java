package housekeeper.dao;

import java.util.List;

import housekeeper.entities.Account;

public interface AccountDao {

    /**
     * 增加账户选项
     *
     * @param account
     */
    void save(Account account);

    /**
     * 删除账户选项
     *
     * @param accountId
     */
    void delete(Integer accountId);

    /**
     * 修改账户选项
     *
     * @param account
     */
    void update(Account account);

    /**
     * 获取所有账户选项
     */
    List getAll();

    /**
     * 查询某一名称的账户选项
     *
     * @param name
     * @return
     */
    List queryByName(String name);
}
