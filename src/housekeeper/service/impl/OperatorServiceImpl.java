package housekeeper.service.impl;

import housekeeper.dao.*;
import housekeeper.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    private ItemDao itemDao;
    @Autowired
    private SubItemDao subItemDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private FamilyDao familyDao;
    @Autowired
    private CashInDao cashInDao;
    @Autowired
    private CashOutDao cashOutDao;
    @Autowired
    private CardDao cardDao;
    @Autowired
    private AccountDao accountDao;

    public List getList(List list, Integer... id) {
        if (id != null && list.size() != 0) {
            return list;
        } else {
            return new ArrayList();
        }
    }

    public String delete(Integer id, Integer size, String method) {
        try {
            if (id != null && size != 0) {
                switch (method) {
                    case "a":
                        accountDao.delete(id);
                        break;
                    case "i":
                        itemDao.delete(id);
                        break;
                    case "s":
                        subItemDao.delete(id);
                        break;
                    case "m":
                        memberDao.delete(id);
                        break;
                    case "f":
                        familyDao.delete(id);
                        break;
                    case "c":
                        cardDao.delete(id);
                        break;
                    case "ci":
                        cashInDao.delete(id);
                        break;
                    case "co":
                        cashOutDao.delete(id);
                        break;
                }
                return "200";
            } else {
                return "201";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "202";
        }
    }
}
