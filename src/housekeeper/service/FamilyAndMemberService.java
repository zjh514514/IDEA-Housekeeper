package housekeeper.service;

import java.util.Date;
import java.util.List;

import housekeeper.entities.Family;
import housekeeper.entities.Member;

public interface FamilyAndMemberService {

    /**
     * 家庭登陆
     *
     * @param username：长度六位
     * @param password
     * @return
     */
    String familyLogin(String username, String password);

    /**
     * 成员登陆
     *
     * @param username：长度三位
     * @param password
     * @return
     */
    String memberLogin(String username, String password);

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
     * 删除某一家庭
     *
     * @param id
     * @return
     */
    String familyDelete(Integer id);

    /**
     * 删除某一成员
     *
     * @param id
     * @return
     */
    String memberDelete(Integer id);

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
    List<Family> familyGet(Integer id);

    /**
     * 获取某一家庭下所有成员
     *
     * @param familyId
     * @return
     */
    List<Member> memberFamilyGet(Integer familyId);

    /**
     * 获取某一成员信息
     *
     * @param id
     * @return
     */
    List<Member> memberGet(Integer id);

    /**
     * 查询某一成员某一时间段收支总和
     *
     * @param id
     * @param startTime
     * @param endTime
     * @return
     */
    double memberSumCash(Integer id, Date startTime, Date endTime, String which);
}