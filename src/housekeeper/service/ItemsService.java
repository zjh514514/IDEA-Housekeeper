package housekeeper.service;

import java.util.List;

public interface ItemsService {

    /**
     * 增加一条父类信息
     *
     * @return
     */
    String addItems(String itemName, Integer type);

    /**
     * 增加一条子类信息
     *
     * @return 0
     */
    String addSubItems(String subItemName, Integer itemId);

    /**
     * 删除一条分类
     *
     * @param id
     * @return
     */
    String delete(Integer id, String which);

    /**
     * 修改一条父类信息
     *
     * @return
     */
    String updateItem(String itemName, Integer id);

    /**
     * 修改一条子类信息
     *
     * @return
     */
    String updateSubItem(String subItemName, Integer id);

    /**
     * 查询收入或支出的父类
     *
     * @param type
     * @return
     */
    List queryItem(Integer type);

    /**
     * 查询某一父类下的所有子类
     *
     * @param itemId
     * @return
     */
    List querySubItem(Integer itemId);
}
