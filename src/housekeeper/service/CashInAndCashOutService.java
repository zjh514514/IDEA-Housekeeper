package housekeeper.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CashInAndCashOutService {

    /**
     * 增加一条收入记录
     *
     * @param time:格式yyyy-MM-dd HH:mm
     * @param site
     * @param people
     * @param money
     * @param remark
     * @param memberId
     * @param itemId
     * @param subItemId
     * @param accountId
     * @return
     */
    String add(String time, String site, String people, Double money, String remark, Integer memberId,
               Integer itemId, Integer subItemId, Integer accountId, String which);

    /**
     * 删除某一收支记录
     *
     * @param id
     * @return
     */
    String delete(Integer id, String which);

    /**
     * 修改某一收入记录信息
     *
     * @param time
     * @param site
     * @param people
     * @param money
     * @param remark
     * @param itemId
     * @param subItemId
     * @param id
     * @param accountId
     * @return
     */
    String update(String time, String site, String people, Double money, String remark, Integer itemId,
                  Integer subItemId, Integer id, Integer accountId, String which);

    /**
     * 查询某一成员下所有的收入信息
     *
     * @param memberId
     * @return
     */
    List queryCashInByMember(Integer memberId);

    /**
     * 查询某一成员下所有的支出信息
     *
     * @param memberId
     * @return
     */
    List queryCashOutByMember(Integer memberId);

    /**
     * 查询某一成员下某一父类的收入记录
     *
     * @param itemId
     * @param memberId
     * @return
     */
    List queryCashInByItem(Integer itemId, Integer memberId);

    /**
     * 查询某一成员下某一父类的支出记录
     *
     * @param itemId
     * @param memberId
     * @return
     */
    List queryCashOutByItem(Integer itemId, Integer memberId);

    /**
     * 查询某一成员下某一子类的收入记录
     *
     * @param subItemId
     * @param memberId
     * @return
     */
    List queryCashInBySubItem(Integer subItemId, Integer memberId);

    /**
     * 查询某一成员下某一子类的支出记录
     *
     * @param subItemId
     * @param memberId
     * @return
     */
    List queryCashOutBySubItem(Integer subItemId, Integer memberId);

    /**
     * 查询某一条收入记录
     *
     * @param id
     * @return
     */
    List queryCashInById(Integer id);

    /**
     * 查询某一条支出记录
     *
     * @param id
     * @return
     */
    List queryCashOutById(Integer id);

    /**
     * 查询某一成员下某账户的收入记录
     *
     * @param accountId
     * @param memberId
     * @return
     */
    List queryCashInByAccount(Integer accountId, Integer memberId);

    /**
     * 查询某一成员下某账户的支出记录
     *
     * @param accountId
     * @param memberId
     * @return
     */
    List queryCashOutByAccount(Integer accountId, Integer memberId);

    /**
     * 查询某一成员某一时间段收支记录
     *
     * @param memberId
     * @param startTime
     * @param endTime
     * @return
     */
    List queryByTime(Integer memberId, Date startTime, Date endTime, String which);

    /**
     * 查询某一成员某一时间段收支总和
     *
     * @param memberId
     * @param startTime
     * @param endTime
     * @param which
     * @return
     */
    double memberSumCash(Integer memberId, Date startTime, Date endTime, String which);

    /**
     * 获取某一年每月总收支记录
     *
     * @param memberId
     * @param year
     * @return
     */
    Map yearSum(Integer memberId, String year);
}
