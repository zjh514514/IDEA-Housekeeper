package housekeeper.dao;

import housekeeper.entities.Member;
import housekeeper.entities.MemberQuery;

import java.util.Date;
import java.util.List;

public interface MemberDao {

    /**
     * 增加成员
     *
     * @param member
     */
    public void save(Member member);

    /**
     * 删除某一成员
     *
     * @param id
     */
    public void delete(Integer id);

    /**
     * 修改某一成员信息
     *
     * @param member
     */
    public void update(Member member);

    /**
     * 获取所有成员信息
     *
     * @return
     */
    public List<Member> getAll();

    /**
     * 通过用户名查询某一成员
     *
     * @param username
     * @return
     */
    public MemberQuery queryByUsername(String username);

    /**
     * 查询某一成员
     *
     * @param id
     * @return
     */
    public List<Member> queryById(Integer id);

    /**
     * 查询某一家庭下的所有成员
     *
     * @param family
     * @return
     */
    public List<Member> queryByFamily(Integer familyId);

    /**
     * 计算某成员某时间段总收入
     *
     * @param id
     * @return
     */
    public double sumCashIn(Integer id, Date startTime, Date endTime);

    /**
     * 计算某成员某时间段总支出
     *
     * @param id
     * @return
     */
    public double sumCashOut(Integer id, Date startTime, Date endTime);
}
