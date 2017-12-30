package housekeeper.service.impl;

import housekeeper.dao.FamilyDao;
import housekeeper.dao.MemberDao;
import housekeeper.entities.Family;
import housekeeper.entities.Member;
import housekeeper.entities.MemberQuery;
import housekeeper.service.FamilyAndMemberService;
import housekeeper.service.OperatorService;
import housekeeper.tools.Sha256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FamilyAndMemberServiceImpl<E> implements FamilyAndMemberService<E> {

    @Autowired
    private FamilyDao familyDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private OperatorService operatorService;

    @Autowired
    private Family family;
    @Autowired
    private Member member;
    @Autowired
    private MemberQuery memberQuery;

    @Override
    public E login(String username, String password, String which) {
        try {
            if (username == null)
                username = "";
            if (which.equals("f")) {
                family = familyDao.queryByUsername(username);
                if (family.getPassword().equals(Sha256.getSHA256StrJava(password))) {
                    return (E) family;
                } else {
                    return null;
                }
            } else {
                memberQuery = memberDao.queryByUsername(username);
                if (memberQuery.getId().getPassword().equals(Sha256.getSHA256StrJava(password))) {
                    return (E) memberQuery;
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String familySign(String username, String password, String familyName) {
        try {
            if (username == null)
                username = "";
            if (password == null)
                password = "";
            if (familyName == null)
                familyName = "";
            if (!username.equals("") && !password.equals("")
                    && !familyName.equals("") & familyDao.queryByUsername(username) == null) {
                family.setUsername(username);
                family.setPassword(Sha256.getSHA256StrJava(password));
                family.setFamilyName(familyName);
                familyDao.save(family);
                return "200";
            } else {
                return "201";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "202";
        }

    }

    @Override
    public String memberSign(String username, String password, String name, String role, Integer familyId) {
        try {
            if (username == null)
                username = "";
            if (password == null)
                password = "";
            if (name == null)
                name = "";
            if (role == null)
                role = "";
            if (familyId <= 0)
                familyId = -1;
            if (!username.equals("") && !password.equals("") && !name.equals("") && !role.equals("") && familyId != -1
                    && memberDao.queryByUsername(username) == null) {
                family.setFamilyId(familyId);
                member.setUsername(username);
                member.setPassword(Sha256.getSHA256StrJava(password));
                member.setName(name);
                member.setRole(role);
                member.setBalance(0.0);
                member.setFamily(family);
                memberDao.save(member);
                return "200";
            } else {
                // 用户名已被注册
                return "201";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "202";
        }

    }

    @Override
    public String delete(Integer id, String which) {
        return operatorService.delete(id, 1, which);
    }

    @Override
    public String familyUpdate(String password, String familyName, Integer id) {
        try {
            if (password == null)
                password = "";
            if (familyName == null)
                familyName = "";
            if (!password.equals("") && !familyName.equals("") && id != null && familyDao.queryById(id).size() != 0) {
                family.setFamilyName(familyName);
                family.setPassword(Sha256.getSHA256StrJava(password));
                family.setFamilyId(id);
                familyDao.update(family);
                return "200";
            } else {
                return "201";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "202";
        }
    }

    @Override
    public String memberUpdate(String password, String role, Double balance, Integer id, String name) {
        try {
            if (password == null)
                password = "";
            if (role == null)
                role = "";
            if (name == null)
                name = "";
            if (balance == null)
                balance = 0.0;
            if (!password.equals("") && !role.equals("") && id != null && !name.equals("") && memberDao.queryById(id) != null) {
                member.setMemberId(id);
                member.setBalance(balance);
                member.setPassword(Sha256.getSHA256StrJava(password));
                member.setRole(role);
                member.setName(name);
                memberDao.update(member);
                return "200";
            } else {
                return "201";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "202";
        }
    }

    @Override
    public List familyGet(Integer id) {
        return (List) operatorService.getList(familyDao.queryById(id), id);
    }

    @Override
    public Member memberGet(Integer id) {
        return (Member) operatorService.getList(memberDao.queryById(id), id);
    }

    @Override
    public List memberFamilyGet(Integer familyId) {
        if (familyId != null && familyDao.queryById(familyId).size() != 0) {
            return memberDao.queryByFamily(familyId);
        } else {
            return new ArrayList();
        }
    }
}
