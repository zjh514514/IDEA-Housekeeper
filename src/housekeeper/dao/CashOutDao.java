package housekeeper.dao;

import java.util.Date;
import java.util.List;

import housekeeper.entities.CashOut;

public interface CashOutDao {

    /**
     * 保存一条支出记录
     *
     * @param cashOut
     */
    void save(CashOut cashOut);

    /**
     * 删除一条支出记录
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 修改一条支出记录信息
     *
     * @param cashOut
     */
    void update(CashOut cashOut);

    /**
     * 查询某一成员支出记录
     *
     * @param memberId
     * @return
     */
    List queryByMember(Integer memberId);

    /**
     * 查询某一成员某一父类支出记录
     *
     * @param ItemId
     * @param memberId
     * @return
     */
    List queryByItem(Integer ItemId, Integer memberId);

    /**
     * 查询某一成员某一子类支出记录
     *
     * @param subItemId
     * @param memberId
     * @return
     */
    List queryBySubItem(Integer subItemId, Integer memberId);

    /**
     * 查询一条支出记录
     *
     * @param id
     * @return
     */
    List queryById(Integer id);

    /**
     * 查询某一成员某账户支出记录
     *
     * @param accountId
     * @param memberId
     * @return
     */
    List queryByAccount(Integer accountId, Integer memberId);

    /**
     * 查询某成员某时间段支出记录
     *
     * @param memberId
     * @param startTime
     * @param endTime
     * @return
     */
    List queryByTime(Integer memberId, Date startTime, Date endTime);

    /**
     * 计算某成员某时间段总支出
     *
     * @return
     */
    Double sumCashOut(Integer memberId, Date startTime, Date endTime);

    List queryByFamily(Integer familyId, Date startTime, Date endTime);
}
