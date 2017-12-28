package housekeeper.service.impl;

import housekeeper.dao.*;
import housekeeper.entities.*;
import housekeeper.service.CashInAndCashOutService;
import housekeeper.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CashInAndCashOutServiceImpl implements CashInAndCashOutService {

    @Autowired
    private CashInDao cashInDao;
    @Autowired
    private CashOutDao cashOutDao;
    @Autowired
    private CashIn cashIn;
    @Autowired
    private CashOut cashOut;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private SubItemDao subItemDao;
    @Autowired
    private Account account;
    @Autowired
    private OperatorService operatorService;

    @Autowired
    private Member member;
    @Autowired
    private Item item;
    @Autowired
    private SubItem subItem;

    public String add(String time, String site, String people, Double money, String remark, Integer memberId,
                      Integer itemId, Integer subItemId, Integer accountId, String which) {
        try {
            Date date;
            if (time == null || time.equals("")) {
                date = new Date();
            } else {
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(time);
            }
            if (site == null)
                site = "";
            if (people == null)
                people = "";
            if (money == null)
                money = 0.0;
            if (remark == null)
                remark = "";
            if (memberId != null && itemId != null && subItemId != null && accountId != null
                    && memberDao.queryById(memberId).size() != 0 && itemDao.queryById(itemId).size() != 0
                    && subItemDao.queryById(subItemId).size() != 0) {
                item.setItemId(itemId);
                subItem.setSubItemId(subItemId);
                member.setMemberId(memberId);
                account.setAccountId(accountId);
                switch (which) {
                    case "i":
                        cashIn.setMoney(money);
                        cashIn.setPeople(people);
                        cashIn.setRemark(remark);
                        cashIn.setSite(site);
                        cashIn.setTime(date);
                        cashIn.setAccount(account);
                        cashIn.setItem(item);
                        cashIn.setMember(member);
                        cashIn.setSubItem(subItem);
                        cashInDao.save(cashIn);
                        break;
                    case "o":
                        cashOut.setMoney(money);
                        cashOut.setPeople(people);
                        cashOut.setRemark(remark);
                        cashOut.setSite(site);
                        cashOut.setTime(date);
                        cashOut.setAccount(account);
                        cashOut.setItem(item);
                        cashOut.setMember(member);
                        cashOut.setSubItem(subItem);
                        cashOutDao.save(cashOut);
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


    @Override
    public String delete(Integer id, String which) {
        return operatorService.delete(id, cashInDao.queryById(id).size(), which);
    }

    @Override
    public String update(String time, String site, String people, Double money, String remark, Integer itemId,
                         Integer subItemId, Integer id, Integer accountId, String which) {
        try {
            if (id != null && itemId != null && subItemId != null && accountId != null && cashInDao.queryById(id).size() != 0
                    && itemDao.queryById(itemId).size() != 0 && subItemDao.queryById(subItemId).size() != 0) {
                if (site == null)
                    site = "";
                if (people == null)
                    people = "";
                if (money == null)
                    money = 0.0;
                if (remark == null)
                    remark = "";
                Date date;
                if (time == null || time.equals("")) {
                    date = new Date();
                } else {
                    date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(time);
                }
                item.setItemId(itemId);
                subItem.setSubItemId(subItemId);
                account.setAccountId(accountId);
                switch (which) {
                    case "i":
                        cashIn.setCashInId(id);
                        cashIn.setMoney(money);
                        cashIn.setPeople(people);
                        cashIn.setRemark(remark);
                        cashIn.setSite(site);
                        cashIn.setTime(date);
                        cashIn.setAccount(account);
                        cashIn.setItem(item);
                        cashIn.setSubItem(subItem);
                        cashInDao.update(cashIn);
                    case "o":
                        cashOut.setCashOutId(id);
                        cashOut.setMoney(money);
                        cashOut.setPeople(people);
                        cashOut.setRemark(remark);
                        cashOut.setSite(site);
                        cashOut.setTime(date);
                        cashOut.setAccount(account);
                        cashOut.setItem(item);
                        cashOut.setSubItem(subItem);
                        cashOutDao.update(cashOut);
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

    @Override
    public List queryCashInByMember(Integer memberId) {
        return operatorService.getList(cashInDao.queryByMember(memberId), memberId);
    }

    @Override
    public List queryCashOutByMember(Integer memberId) {
        return operatorService.getList(cashOutDao.queryByMember(memberId), memberId);
    }


    @Override
    public List queryCashInByItem(Integer itemId, Integer memberId) {
        return operatorService.getList(cashInDao.queryByItem(itemId, memberId), itemId, memberId);
    }

    @Override
    public List queryCashOutByItem(Integer itemId, Integer memberId) {
        return operatorService.getList(cashOutDao.queryByItem(itemId, memberId), itemId, memberId);
    }

    @Override
    public List queryCashInBySubItem(Integer subItemId, Integer memberId) {
        if (subItemId != null && memberId != null) {
            return cashInDao.queryBySubItem(subItemId, memberId);
        } else {
            return new ArrayList();
        }
    }

    @Override
    public List queryCashOutBySubItem(Integer subItemId, Integer memberId) {
        if (subItemId != null && memberId != null) {
            return cashOutDao.queryBySubItem(subItemId, memberId);
        } else {
            return new ArrayList();
        }
    }

    @Override
    public List queryCashInById(Integer id) {
        return operatorService.getList(cashInDao.queryById(id), id);
    }

    @Override
    public List queryCashOutById(Integer id) {
        return operatorService.getList(cashOutDao.queryById(id), id);
    }

    @Override
    public List queryCashInByAccount(Integer accountId, Integer memberId) {
        return operatorService.getList(cashInDao.queryByAccount(accountId, memberId), accountId, memberId);
    }

    @Override
    public List queryCashOutByAccount(Integer accountId, Integer memberId) {
        return operatorService.getList(cashOutDao.queryByAccount(accountId, memberId), accountId, memberId);
    }

    @Override
    public List queryByTime(Integer memberId, Date startTime, Date endTime, String which) {
        if (memberId != null && startTime != null && endTime != null && which != null) {
            switch (which) {
                case "i":
                    return cashInDao.queryByTime(memberId, startTime, endTime);
                case "o":
                    return cashOutDao.queryByTime(memberId, startTime, endTime);
            }
            return new ArrayList();
        } else {
            return new ArrayList();
        }
    }

    @Override
    public double memberSumCash(Integer memberId, Date startTime, Date endTime, String which) {
        if (memberId != null && startTime != null && endTime != null && which != null) {
            switch (which) {
                case "i":
                    if (cashInDao.queryByTime(memberId, startTime, endTime).size() != 0) {
                        return cashInDao.sumCashIn(memberId, startTime, endTime);
                    } else {
                        return 0;
                    }
                default:
                    if (cashOutDao.queryByTime(memberId, startTime, endTime).size() != 0) {
                        return cashOutDao.sumCashOut(memberId, startTime, endTime);
                    } else {
                        return 0;
                    }
            }
        } else {
            return 0;
        }
    }

    @Override
    public Map<Object, Object> yearSum(Integer memberId, String year, String which) {
        Map<Object, Object> map = new HashMap<>();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (memberId != null && year != null) {
            for (Integer month = 1; month < 13; month++) {
                String startTime = year + "-" + month + "-01 00:00";
                String endTime = year + "-" + month + "-31 23:59";
                try {
                    switch (which) {
                        case "i":
                            if (cashInDao.queryByTime(memberId, f.parse(startTime), f.parse(endTime)).size() != 0) {
                                map.put(month.toString(), Double.toString(cashInDao.sumCashIn(memberId, f.parse(startTime), f.parse(endTime))));
                            } else {
                                map.put(month.toString(), "0");
                            }
                            break;
                        default:
                            if (cashOutDao.queryByTime(memberId, f.parse(startTime), f.parse(endTime)).size() != 0) {
                                map.put(month.toString(), Double.toString(cashOutDao.sumCashOut(memberId, f.parse(startTime), f.parse(endTime))));
                            } else {
                                map.put(month.toString(), "0");
                            }
                            break;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    return map;
                }
            }
            return map;
        } else {
            return map;
        }
    }

}
