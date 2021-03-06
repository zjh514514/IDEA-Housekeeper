package housekeeper.service;

import housekeeper.entities.Member;
import housekeeper.entities.MemberQuery;

import java.util.List;

public interface FamilyAndMemberService<E> {

    /**
     * 家庭登陆
     *
     * @param username：长度六位
     * @param password
     * @return
     */
    E login(String username, String password, String which);

    /**
     * 家庭注册
     *
     * @param username
     * @param password
     * @param familyName
     * @return
     */
    String familySign(String username, String password, String familyName);

    /**
     * 添加一条成员信息
     *
     * @param username
     * @param password
     * @param name
     * @param role
     * @param familyId
     * @return
     */
    String memberSign(String username, String password, String name, String role, Integer familyId);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    String delete(Integer id, String which);

    /**
     * 修改某一家庭信息
     *
     * @param password
     * @param familyName
     * @param id
     * @return
     */
    String familyUpdate(String password, String familyName, Integer id);

    /**
     * 修改某一成员信息
     *
     * @param password
     * @param role
     * @param balance
     * @param id
     * @param name
     * @return
     */
    String memberUpdate(String password, String role, Double balance, Integer id, String name);

    /**
     * 获取某一家庭信息
     *
     * @param id
     * @return
     */
    List familyGet(Integer id);

    /**
     * 获取某一家庭下所有成员
     *
     * @param familyId
     * @return
     */
    List memberFamilyGet(Integer familyId);

    /**
     * 获取某一成员信息
     *
     * @param id
     * @return
     */
    Member memberGet(Integer id);

    String setBalance(Integer id, Double balance);
}
