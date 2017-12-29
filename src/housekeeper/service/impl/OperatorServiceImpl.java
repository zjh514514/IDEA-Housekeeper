package housekeeper.service.impl;

import housekeeper.dao.*;
import housekeeper.entities.Cash;
import housekeeper.entities.CashInQuery;
import housekeeper.entities.CashOutQuery;
import housekeeper.entities.MemberQuery;
import housekeeper.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
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

    public Object getList(Object object, Object... id) {
        if (id != null) {
            return object;
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

    public List CashIn(Integer memberId, List list) {
        List cashList = new ArrayList();
        try {
            MemberQuery member = memberDao.queryById(memberId);
            for (Object aList : list) {
                CashInQuery cashInQuery = (CashInQuery) aList;
                Cash cash = new Cash();
                cash.setTime(cashInQuery.getId().getTime());
                cash.setMoney(cashInQuery.getId().getMoney());
                cash.setSite(cashInQuery.getId().getSite());
                cash.setAccount(cashInQuery.getId().getAccountName());
                cash.setItem(cashInQuery.getId().getItemName());
                cash.setSubItem(cashInQuery.getId().getSubitemName());
                cash.setRemark(cashInQuery.getId().getRemark());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                Date startTime = simpleDateFormat.parse("1601-01-01 00:00");
                Date endTime = cashInQuery.getId().getTime();
                Double cashIn = cashInDao.sumCashIn(memberId, startTime, endTime);
                cashIn = cashIn == null ? 0 : cashIn;
                Double cashOut = cashOutDao.sumCashOut(memberId, startTime, endTime);
                cashOut = cashOut == null ? 0 : cashOut;
                cash.setBalance(member.getId().getBalance() + cashIn - cashOut);
                cashList.add(cash);
            }
            cashList.sort((Comparator<Cash>) (o1, o2) -> Integer.compare(0, o1.getTime().compareTo(o2.getTime())));
            return cashList;
        } catch (Exception e) {
            e.printStackTrace();
            return cashList;
        }
    }

    public List CashOut(Integer memberId, List list) {
        List cashList = new ArrayList();
        try {
            MemberQuery member = memberDao.queryById(memberId);
            for (Object aList : list) {
                CashOutQuery cashOutQuery = (CashOutQuery) aList;
                Cash cash = new Cash();
                cash.setTime(cashOutQuery.getId().getTime());
                cash.setMoney(cashOutQuery.getId().getMoney());
                cash.setSite(cashOutQuery.getId().getSite());
                cash.setAccount(cashOutQuery.getId().getAccountName());
                cash.setItem(cashOutQuery.getId().getItemName());
                cash.setSubItem(cashOutQuery.getId().getSubitemName());
                cash.setRemark(cashOutQuery.getId().getRemark());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                Date startTime = simpleDateFormat.parse("1601-01-01 00:00");
                Date endTime = cashOutQuery.getId().getTime();
                Double cashIn = cashInDao.sumCashIn(memberId, startTime, endTime);
                cashIn = cashIn == null ? 0 : cashIn;
                Double cashOut = cashOutDao.sumCashOut(memberId, startTime, endTime);
                cashOut = cashOut == null ? 0 : cashOut;
                cash.setBalance(member.getId().getBalance() + cashIn - cashOut);
                cashList.add(cash);
            }
            cashList.sort((Comparator<Cash>) (o1, o2) -> Integer.compare(0, o1.getTime().compareTo(o2.getTime())));
            return cashList;
        } catch (Exception e) {
            e.printStackTrace();
            return cashList;
        }
    }

}
