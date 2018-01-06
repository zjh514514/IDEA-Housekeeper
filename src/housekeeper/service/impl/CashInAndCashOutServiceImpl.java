package housekeeper.service.impl;

import housekeeper.dao.*;
import housekeeper.entities.*;
import housekeeper.service.CashInAndCashOutService;
import housekeeper.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
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
                    && memberDao.queryById(memberId) != null && itemDao.queryById(itemId).size() != 0
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
        if (which.equals("ci"))
            return operatorService.delete(id, cashInDao.queryById(id).size(), which);
        else
            return operatorService.delete(id, cashOutDao.queryById(id).size(), which);
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
        List list = (List) operatorService.getList(cashInDao.queryByMember(memberId), memberId);
        return operatorService.CashIn(memberId, list);
    }

    @Override
    public List queryCashOutByMember(Integer memberId) {
        List list = (List) operatorService.getList(cashOutDao.queryByMember(memberId), memberId);
        return operatorService.CashOut(memberId, list);
    }

    @Override
    public List queryCashInByItem(Integer itemId, Integer memberId) {
        List list = (List) operatorService.getList(cashInDao.queryByItem(itemId, memberId), itemId, memberId);
        return operatorService.CashIn(memberId, list);
    }

    @Override
    public List queryCashOutByItem(Integer itemId, Integer memberId) {
        List list = (List) operatorService.getList(cashOutDao.queryByItem(itemId, memberId), itemId, memberId);
        return operatorService.CashOut(memberId, list);
    }

    @Override
    public List queryCashInBySubItem(Integer subItemId, Integer memberId) {
        List list = (List) operatorService.getList(cashInDao.queryBySubItem(subItemId, memberId), subItemId, memberId);
        return operatorService.CashIn(memberId, list);
    }

    @Override
    public List queryCashOutBySubItem(Integer subItemId, Integer memberId) {
        List list = (List) operatorService.getList(cashOutDao.queryBySubItem(subItemId, memberId), subItemId, memberId);
        return operatorService.CashOut(memberId, list);
    }

    @Override
    public List queryCashInById(Integer id) {
        return (List) operatorService.getList(cashInDao.queryById(id), id);
    }

    @Override
    public List queryCashOutById(Integer id) {
        return (List) operatorService.getList(cashOutDao.queryById(id), id);
    }

    @Override
    public List queryCashInByAccount(Integer accountId, Integer memberId) {
        List list = (List) operatorService.getList(cashInDao.queryByAccount(accountId, memberId), accountId, memberId);
        return operatorService.CashIn(memberId, list);
    }

    @Override
    public List queryCashOutByAccount(Integer accountId, Integer memberId) {
        List list = (List) operatorService.getList(cashOutDao.queryByAccount(accountId, memberId), accountId, memberId);
        return operatorService.CashOut(memberId, list);
    }

    @Override
    public List queryByTime(Integer memberId, Date startTime, Date endTime, String which) {
        List list = new ArrayList();
        switch (which) {
            case "i":
                list = (List) operatorService.getList(cashInDao.queryByTime(memberId, startTime, endTime), memberId, startTime, endTime);
                list.sort((Comparator<CashInQuery>) (o1, o2) -> Integer.compare(0, o1.getId().getTime().compareTo(o2.getId().getTime())));
                break;
            case "o":
                list = (List) operatorService.getList(cashOutDao.queryByTime(memberId, startTime, endTime), memberId, startTime, endTime);
                list.sort((Comparator<CashOutQuery>) (o1, o2) -> Integer.compare(0, o1.getId().getTime().compareTo(o2.getId().getTime())));
                break;
        }
        return list;
    }

    @Override
    public double memberSumCash(Integer memberId, Date startTime, Date endTime, String which) {
        if (memberId != null && startTime != null && endTime != null && which != null) {
            switch (which) {
                case "i":
                    if (cashInDao.queryByTime(memberId, startTime, endTime).size() != 0) {
                        return cashInDao.sumCashIn(memberId, startTime, endTime);
                    }
                    break;
                case "o":
                    if (cashOutDao.queryByTime(memberId, startTime, endTime).size() != 0) {
                        return cashOutDao.sumCashOut(memberId, startTime, endTime);
                    }
                    break;
            }
        }
        return 0;
    }

    @Override
    public Map yearSum(Integer memberId, String year) {
        Map map1 = new HashMap();
        Map map2 = new HashMap();
        Map map3 = new HashMap();
        Map map4 = new HashMap();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (memberId != null && year != null) {
            for (Integer month = 1; month < 13; month++) {
                Double sum = 0.0;
                String startTime = year + "-" + month + "-01 00:00";
                String endTime = year + "-" + month + "-31 23:59";
                try {
                    Double money = cashInDao.sumCashIn(memberId, f.parse(startTime), f.parse(endTime));
                    Double moneyIn = money == null ? 0 : money;
                    sum += moneyIn;
                    map1.put(month.toString(), moneyIn);
                    map2.put("i", map1);
                    Double money2 = cashOutDao.sumCashOut(memberId, f.parse(startTime), f.parse(endTime));
                    Double moneyOut = money2 == null ? 0 : money2;
                    sum -= moneyOut;
                    map3.put(month.toString(), moneyOut);
                    map2.put("o", map3);
                    map4.put(month.toString(), sum);
                    map2.put("sum", map4);
                } catch (Exception e) {
                    e.printStackTrace();
                    return map2;
                }
            }
        }
        return map2;
    }

    public List familyGather(Integer id, String year) {
        List list = new ArrayList();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (id != null && year != null) {
            List members = memberDao.queryByFamily(id);
            for (Object member1 : members) {
                Map map = new HashMap();
                MemberQuery memberQuery = (MemberQuery) member1;
                for (Integer month = 1; month < 13; month++) {
                    Double sum = 0.0;
                    String startTime = year + "-" + month + "-01 00:00";
                    String endTime = year + "-" + month + "-31 23:59";
                    try {
                        Double money = cashInDao.sumCashIn(memberQuery.getId().getMemberId(), f.parse(startTime), f.parse(endTime));
                        Double moneyIn = money == null ? 0 : money;
                        sum += moneyIn;
                        Double money2 = cashOutDao.sumCashOut(memberQuery.getId().getMemberId(), f.parse(startTime), f.parse(endTime));
                        Double moneyOut = money2 == null ? 0 : money2;
                        sum -= moneyOut;
                        map.put(month.toString(), sum);
                        map.put("name", memberQuery.getId().getName());
                        map.put("id", memberQuery.getId().getMemberId());
                    } catch (Exception e) {
                        e.printStackTrace();
                        return list;
                    }
                }
                list.add(map);
            }
        }
        return list;
    }

    public List analyse(Integer id, String year) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List money2 = new ArrayList();
        if (id != null && year != null) {
            List members = memberDao.queryByFamily(id);
            Double sum2 = 0.0;
            List list = new ArrayList();
            for (Object member1 : members) {
                MemberQuery memberQuery = (MemberQuery) member1;
                Double sum = 0.0;
                for (Integer month = 1; month < 13; month++) {
                    String startTime = year + "-" + month + "-01 00:00";
                    String endTime = year + "-" + month + "-31 23:59";
                    try {
                        Double money = cashOutDao.sumCashOut(memberQuery.getId().getMemberId(), f.parse(startTime), f.parse(endTime));
                        Double moneyOut = money == null ? 0 : money;
                        sum += moneyOut;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                list.add(sum);
                sum2 += sum;
            }
            for (Integer size = 0; size < members.size(); size++) {
                Map map = new HashMap();
                MemberQuery memberQuery = (MemberQuery) members.get(size);
                DecimalFormat df = new DecimalFormat("#0.00");
                Double money = (Double) list.get(size);
                String rate = df.format(money * 100 / sum2);
                map.put("rate", rate);
                map.put("name", memberQuery.getId().getName());
                money2.add(map);
            }
        }
        return money2;
    }

    @Override
    public List familyCashIn(Integer id) {
        List cash = new ArrayList();
        if (id != null) {
            List members = memberDao.queryByFamily(id);
            for (Object object : members) {
                MemberQuery memberQuery = (MemberQuery) object;
                List list = cashInDao.queryByMember(memberQuery.getId().getMemberId());
                for (Object aList : list) {
                    CashInQuery cashInQuery = (CashInQuery) aList;
                    Cash cash1 = new Cash();
                    cash1.setId(cashInQuery.getId().getCashinId());
                    cash1.setTime(cashInQuery.getId().getTime());
                    cash1.setSubItemId(cashInQuery.getId().getSubitemId());
                    cash1.setSubItem(cashInQuery.getId().getSubitemName());
                    cash1.setItemId(cashInQuery.getId().getItemId());
                    cash1.setItem(cashInQuery.getId().getItemName());
                    cash1.setMoney(cashInQuery.getId().getMoney());
                    cash1.setSite(cashInQuery.getId().getSite());
                    cash1.setAccount(cashInQuery.getId().getAccountName());
                    cash1.setRemark(cashInQuery.getId().getRemark());
                    MemberQuery memberQuery1 = memberDao.queryById(cashInQuery.getId().getMemberId());
                    cash1.setMember(memberQuery1.getId().getName());
                    cash.add(cash1);
                }
            }
            cash.sort((Comparator<Cash>) (o1, o2) -> Integer.compare(0, o1.getTime().compareTo(o2.getTime())));
        }
        return cash;
    }

    @Override
    public List familyCashOut(Integer id) {
        List cash = new ArrayList();
        if (id != null) {
            List members = memberDao.queryByFamily(id);
            for (Object object : members) {
                MemberQuery memberQuery = (MemberQuery) object;
                List list = cashOutDao.queryByMember(memberQuery.getId().getMemberId());
                for (Object aList : list) {
                    CashOutQuery cashOutQuery = (CashOutQuery) aList;
                    Cash cash1 = new Cash();
                    cash1.setId(cashOutQuery.getId().getCashoutId());
                    cash1.setTime(cashOutQuery.getId().getTime());
                    cash1.setSubItemId(cashOutQuery.getId().getSubitemId());
                    cash1.setSubItem(cashOutQuery.getId().getSubitemName());
                    cash1.setItemId(cashOutQuery.getId().getItemId());
                    cash1.setItem(cashOutQuery.getId().getItemName());
                    cash1.setMoney(cashOutQuery.getId().getMoney());
                    cash1.setSite(cashOutQuery.getId().getSite());
                    cash1.setAccount(cashOutQuery.getId().getAccountName());
                    cash1.setRemark(cashOutQuery.getId().getRemark());
                    MemberQuery memberQuery1 = memberDao.queryById(cashOutQuery.getId().getMemberId());
                    cash1.setMember(memberQuery1.getId().getName());
                    cash.add(cash1);
                }
            }
            cash.sort((Comparator<Cash>) (o1, o2) -> Integer.compare(0, o1.getTime().compareTo(o2.getTime())));
        }
        return cash;
    }
}
