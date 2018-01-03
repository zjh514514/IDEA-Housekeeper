package housekeeper.dao;

import java.util.Date;
import java.util.List;

import housekeeper.entities.CashIn;

public interface CashInDao {

    /**
     * 保存一条收入记录
     *
     * @param cashIn
     */
    void save(CashIn cashIn);

    /**
     * 删除一条收入记录
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 修改一条收入记录信息
     *
     * @param cashIn
     */
    void update(CashIn cashIn);

    /**
     * 查询某一成员的收入记录
     *
     * @param memberId
     * @return
     */
    List queryByMember(Integer memberId);

    /**
     * 查询某一成员某一父类收入记录
     *
     * @param ItemId
     * @param memberId
     * @return
     */
    List queryByItem(Integer ItemId, Integer memberId);

    /**
     * 查询某一成员某一子类收入记录
     *
     * @param subItemId
     * @param memberId
     * @return
     */
    List queryBySubItem(Integer subItemId, Integer memberId);

    /**
     * 查询某一条收入记录
     *
     * @param id
     * @return
     */
    List queryById(Integer id);

    /**
     * 查询某一成员某账户收入记录
     *
     * @param accountId
     * @param memberId
     * @return
     */
    List queryByAccount(Integer accountId, Integer memberId);

    /**
     * 查询某一成员某一时间段收入记录
     *
     * @param memberId
     * @param startTime
     * @param endTime
     * @return
     */
    List queryByTime(Integer memberId, Date startTime, Date endTime);

    /**
     * 计算某成员某时间段总收入
     *
     * @param memberId
     * @param startTime
     * @param endTime
     * @return
     */
    Double sumCashIn(Integer memberId, Date startTime, Date endTime);

    List queryByFamily(Integer familyId, Date startTime, Date endTime);
}
