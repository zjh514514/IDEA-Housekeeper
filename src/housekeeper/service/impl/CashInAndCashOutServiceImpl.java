package housekeeper.service.impl;

import housekeeper.dao.*;
import housekeeper.entities.*;
import housekeeper.service.CashInAndCashOutService;
import housekeeper.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public String addCashIn(String time, String site, String people, Double money, String remark, Integer memberId,
                            Integer itemId, Integer subItemId, Integer accountId) {
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
    public String addCashOut(String time, String site, String people, Double money, String remark, Integer memberId,
                             Integer itemId, Integer subItemId, Integer accountId) {
        try {
            Date date;
            if (time == null) {
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
    public String delete(Integer id, String which) {
        return operatorService.delete(id, cashInDao.queryById(id).size(), which);
    }

    @Override
    public String updateCashIn(String time, String site, String people, Double money, String remark, Integer itemId,
                               Integer subItemId, Integer id, Integer accountId) {
        try {
            if (id != null) {
                if (site == null)
                    site = "";
                if (people == null)
                    people = "";
                if (money == null)
                    money = 0.0;
                if (remark == null)
                    remark = "";
                Date date;
                if (time == null) {
                    date = new Date();
                } else {
                    date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(time);
                }
                if (itemId != null && subItemId != null && accountId != null && cashInDao.queryById(id).size() != 0
                        && itemDao.queryById(itemId).size() != 0 && subItemDao.queryById(subItemId).size() != 0) {
                    item.setItemId(itemId);
                    subItem.setSubItemId(subItemId);
                    account.setAccountId(accountId);
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
                    return "SUCCESS";
                } else {
                    return "FAILED";
                }
            } else {
                return "FAILED";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @Override
    public String updateCashOut(String time, String site, String people, Double money, String remark, Integer itemId,
                                Integer subItemId, Integer id, Integer accountId) {
        try {
            if (id != null) {
                if (site == null)
                    site = "";
                if (people == null)
                    people = "";
                if (money == null)
                    money = 0.0;
                if (remark == null)
                    remark = "";
                Date date;
                if (time == null) {
                    date = new Date();
                } else {
                    date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(time);
                }
                if (itemId != null && subItemId != null && accountId != null & cashOutDao.queryById(id).size() != 0
                        && itemDao.queryById(itemId).size() != 0 && subItemDao.queryById(subItemId).size() != 0) {
                    item.setItemId(itemId);
                    subItem.setSubItemId(subItemId);
                    account.setAccountId(accountId);
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
                    return "SUCCESS";
                } else {
                    return "FAILED";
                }
            } else {
                return "FAILED";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @Override
    public List queryCashInByMember(Integer memberId) {
        if (memberId != null && memberDao.queryById(memberId).size() != 0) {
            // member.setMemberId(memberId);
            List cashIns = cashInDao.queryByMember(memberId);
            if (cashIns.size() != 0) {
                return cashIns;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List queryCashOutByMember(Integer memberId) {
        if (memberId != null && memberDao.queryById(memberId).size() != 0) {
            // member.setMemberId(memberId);
            List cashOuts = cashOutDao.queryByMember(memberId);
            if (cashOuts.size() != 0) {
                return cashOuts;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List queryCashInByItem(Integer itemId, Integer memberId) {
        if (itemId != null && memberId != null) {
            // item.setItemId(itemId);
            // member.setMemberId(memberId);
            List cashIns = cashInDao.queryByItem(itemId, memberId);
            if (cashIns.size() != 0) {
                return cashIns;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List queryCashOutByItem(Integer itemId, Integer memberId) {
        if (itemId != null && memberId != null) {
            // item.setItemId(itemId);
            // member.setMemberId(memberId);
            List cashOuts = cashOutDao.queryByItem(itemId, memberId);
            if (cashOuts.size() != 0) {
                return cashOuts;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List queryCashInBySubItem(Integer subItemId, Integer memberId) {
        if (subItemId != null && memberId != null) {
            subItem.setSubItemId(subItemId);
            member.setMemberId(memberId);
            List cashIns = cashInDao.queryBySubItem(subItemId, memberId);
            if (cashIns.size() != 0) {
                return cashIns;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List queryCashOutBySubItem(Integer subItemId, Integer memberId) {
        if (subItemId != null && memberId != null) {
            // subItem.setSubItemId(subItemId);
            // member.setMemberId(memberId);
            List cashOuts = cashOutDao.queryBySubItem(subItemId, memberId);
            if (cashOuts.size() != 0) {
                return cashOuts;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List queryCashInById(Integer id) {
        return operatorService.getList(id, cashInDao.queryById(id));
    }

    @Override
    public List queryCashOutById(Integer id) {
        return operatorService.getList(id, cashOutDao.queryById(id));
    }

    @Override
    public List queryCashInByAccount(Integer accountId, Integer memberId) {
        if (accountId != null && memberId != null) {
            List cashIns = cashInDao.queryByAccount(accountId, memberId);
            if (cashIns.size() != 0) {
                return cashIns;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List queryCashOutByAccount(Integer accountId, Integer memberId) {
        if (accountId != null && memberId != null) {
            List cashOuts = cashOutDao.queryByAccount(accountId, memberId);
            if (cashOuts.size() != 0) {
                return cashOuts;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List queryByTime(Integer memberId, Date startTime, Date endTime, String which) {
        if (memberId != null && startTime != null && endTime != null && which != null) {
            if (which.equals("i")) {
                List cashIns = cashInDao.queryByTime(memberId, startTime, endTime);
                if (cashIns.size() != 0) {
                    return cashIns;
                } else {
                    return null;
                }
            } else {
                List cashOuts = cashOutDao.queryByTime(memberId, startTime, endTime);
                if (cashOuts.size() != 0) {
                    return cashOuts;
                } else {
                    return null;
                }
            }
        } else {
            return null;
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
                System.out.println(startTime + "," + endTime);
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
                    return null;
                }
            }
            return map;
        } else {
            return null;
        }
    }

}
