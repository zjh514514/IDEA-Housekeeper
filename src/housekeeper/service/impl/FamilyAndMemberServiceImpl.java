package housekeeper.service.impl;

import housekeeper.dao.FamilyDao;
import housekeeper.dao.MemberDao;
import housekeeper.entities.Family;
import housekeeper.entities.Member;
import housekeeper.entities.MemberQuery;
import housekeeper.service.FamilyAndMemberService;
import housekeeper.tools.Sha256;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class FamilyAndMemberServiceImpl implements FamilyAndMemberService {

    @Resource
    private FamilyDao familyDao;
    @Resource
    private MemberDao memberDao;

    @Resource(name = "family")
    private Family family;
    @Resource(name = "member")
    private Member member;

    @Override
    public Family familyLogin(String username, String password) {
        try {
            if (username == null)
                username = "";
            family = familyDao.queryByUsername(username);
            System.out.println(family.getPassword());
            if (family.getPassword().equals(Sha256.getSHA256StrJava(password))) {
                return family;
            } else {
                // 密码错误
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 用户名不存在
            return null;
        }
    }

    @Override
    public MemberQuery memberLogin(String username, String password) {
        try {
            if (username == null)
                username = "";
            MemberQuery member = memberDao.queryByUsername(username);
            if (member.getId().getPassword().equals(Sha256.getSHA256StrJava(password))) {
                return member;
            } else {
                //密码错误
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 用户名不存在
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
                return "SUCCESS";
            } else {
                // 用户名已被注册
                return "FAILED";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
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
                return "SUCCESS";
            } else {
                // 用户名已被注册
                return "FAILED";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }

    }

    @Override
    public String familyDelete(Integer id) {
        try {
            if (id != null && familyDao.queryById(id).size() != 0) {
                familyDao.delete(id);
                return "SUCCESS";
            } else {
                return "FAILED";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @Override
    public String memberDelete(Integer id) {
        try {
            if (id != null && memberDao.queryById(id).size() != 0) {
                memberDao.delete(id);
                return "SUCCESS";
            } else {
                return "FAILED";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
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
                return "SUCCESS";
            } else {
                return "FAILED";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
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
            if (!password.equals("") && !role.equals("") && id != null && !name.equals("") && memberDao.queryById(id).size() != 0) {
                member.setMemberId(id);
                member.setBalance(balance);
                member.setPassword(Sha256.getSHA256StrJava(password));
                member.setRole(role);
                member.setName(name);
                memberDao.update(member);
                return "SUCCESS";
            } else {
                return "FAILED";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @Override
    public List<Family> familyGet(Integer id) {
        if (id != null) {
            List<Family> families = familyDao.queryById(id);
            if (families.size() != 0) {
                return families;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List<Member> memberFamilyGet(Integer familyId) {
        if (familyId != null && familyDao.queryById(familyId).size() != 0) {
            // family.setFamilyId(familyId);
            List<Member> members = memberDao.queryByFamily(familyId);
            if (members.size() != 0) {
                return members;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List<Member> memberGet(Integer id) {
        if (id != null) {
            List<Member> members = memberDao.queryById(id);
            if (members.size() != 0) {
                return members;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
