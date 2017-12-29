package housekeeper.dao;

import housekeeper.entities.Member;
import housekeeper.entities.MemberQuery;

import java.util.List;

public interface MemberDao {

    /**
     * 增加成员
     *
     * @param member
     */
    void save(Member member);

    /**
     * 删除某一成员
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 修改某一成员信息
     *
     * @param member
     */
    void update(Member member);

    /**
     * 获取所有成员信息
     *
     * @return
     */
    List getAll();

    /**
     * 通过用户名查询某一成员
     *
     * @param username
     * @return
     */
    MemberQuery queryByUsername(String username);

    /**
     * 查询某一成员
     *
     * @param id
     * @return
     */
    MemberQuery queryById(Integer id);

    /**
     * 查询某一家庭下的所有成员
     *
     * @param familyId
     * @return
     */
    List queryByFamily(Integer familyId);

}
