package housekeeper.action;

import com.opensymphony.xwork2.ActionSupport;
import housekeeper.service.CashInAndCashOutService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("cashInAndCashOutAction")
@Scope("prototype")
public class CashInAndCashOutAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = -7498673228509628884L;

    @Autowired
    private CashInAndCashOutService cashInAndCashOutService;
    @Autowired
    private GetStrResponse getStrResponse;

    private String time;
    private String site;
    private String people;
    private Double money;
    private String remark;
    private Integer memberId;
    private Integer itemId;
    private Integer subItemId;
    private Integer id;
    private Integer accountId;
    private String which;
    private String startTime;
    private String endTime;
    private String year;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getSubItemId() {
        return subItemId;
    }

    public void setSubItemId(Integer subItemId) {
        this.subItemId = subItemId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getWhich() {
        return which;
    }

    public void setWhich(String which) {
        this.which = which;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    /**
     * 增加一条收支记录
     *
     * @throws Exception
     */
    public void save() throws Exception {
        String result;
        Map<String, String> results = new HashMap<>();
        String json = getStrResponse.getStrResponse();
        JSONObject jsonRequest;
        if (!json.equals("")) {
            jsonRequest = JSONObject.fromObject(json);
            which = jsonRequest.getString("which");
            time = jsonRequest.getString("time");
            site = jsonRequest.getString("site");
            people = jsonRequest.getString("people");
            money = jsonRequest.getDouble("money");
            remark = jsonRequest.getString("remark");
            memberId = jsonRequest.getInt("memberId");
            itemId = jsonRequest.getInt("itemId");
            subItemId = jsonRequest.getInt("subItemId");
            accountId = jsonRequest.getInt("accountId");
            System.out.println(time);
        }
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		Long date = new Long(time);
        if (which.equals("i")) {
            result = cashInAndCashOutService.addCashIn(time, site, people, money, remark,
                    memberId, itemId, subItemId, accountId);
        } else {
            result = cashInAndCashOutService.addCashOut(time, site, people, money, remark,
                    memberId, itemId, subItemId, accountId);
        }
        results.put("result", result);
        getStrResponse.writer(results);
    }

    /**
     * 查询某一成员收支记录
     *
     * @throws Exception
     */
    public void memberQuery() throws Exception {
        String json = getStrResponse.getStrResponse();
        JSONObject jsonRequest;
        if (!json.equals("")) {
            jsonRequest = JSONObject.fromObject(json);
            which = jsonRequest.getString("which");
            memberId = jsonRequest.getInt("memberId");
        }
        if (which.equals("i")) {
            List cashIns = cashInAndCashOutService.queryCashInByMember(memberId);
            getStrResponse.writer(cashIns);
        } else {
            List cashOuts = cashInAndCashOutService.queryCashOutByMember(memberId);
            getStrResponse.writer(cashOuts);
        }
    }

    /**
     * 查询某一条收支记录
     *
     * @throws Exception
     */
    public void idQuery() throws Exception {
        String json = getStrResponse.getStrResponse();
        if (!json.equals("")) {
            JSONObject jsonRequest = JSONObject.fromObject(json);
            which = jsonRequest.getString("which");
            id = jsonRequest.getInt("id");
        }
        if (which.equals("i")) {
            List cashIns = cashInAndCashOutService.queryCashInById(id);
            getStrResponse.writer(cashIns);
        } else {
            List cashOuts = cashInAndCashOutService.queryCashOutById(id);
            getStrResponse.writer(cashOuts);
        }
    }

    /**
     * 查询某一成员某一父类收支记录
     *
     * @throws Exception
     */
    public void itemQuery() throws Exception {
        String json = getStrResponse.getStrResponse();
        if (!json.equals("")) {
            JSONObject jsonRequest = JSONObject.fromObject(json);
            which = jsonRequest.getString("which");
            itemId = jsonRequest.getInt("itemId");
            memberId = jsonRequest.getInt("memberId");
        }
        if (which.equals("i")) {
            List cashIns = cashInAndCashOutService.queryCashInByItem(itemId, memberId);
            getStrResponse.writer(cashIns);
        } else {
            List cashOuts = cashInAndCashOutService.queryCashOutByItem(itemId, memberId);
            getStrResponse.writer(cashOuts);
        }
    }

    /**
     * 查询某一成员某一子类收支记录
     *
     * @throws Exception
     */
    public void subItemQuery() throws Exception {
        String json = getStrResponse.getStrResponse();
        if (!json.equals("")) {
            JSONObject jsonRequest = JSONObject.fromObject(json);
            which = jsonRequest.getString("which");
            subItemId = jsonRequest.getInt("subItemId");
            memberId = jsonRequest.getInt("memberId");
        }
        if (which.equals("i")) {
            List cashIns = cashInAndCashOutService.queryCashInBySubItem(subItemId, memberId);
            getStrResponse.writer(cashIns);
        } else {
            List cashOuts = cashInAndCashOutService.queryCashOutBySubItem(subItemId, memberId);
            getStrResponse.writer(cashOuts);
        }
    }

    /**
     * 修改一条收入记录
     *
     * @throws Exception
     */
    public void update() throws Exception {
        String result;
        Map<String, String> results = new HashMap<>();
        String json = getStrResponse.getStrResponse();
        JSONObject jsonRequest;
        if (!json.equals("")) {
            jsonRequest = JSONObject.fromObject(json);
            which = jsonRequest.getString("which");
            time = jsonRequest.getString("time");
            site = jsonRequest.getString("site");
            people = jsonRequest.getString("people");
            money = jsonRequest.getDouble("money");
            remark = jsonRequest.getString("remark");
            memberId = jsonRequest.getInt("memberId");
            itemId = jsonRequest.getInt("itemId");
            subItemId = jsonRequest.getInt("subItemId");
            accountId = jsonRequest.getInt("accountId");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Long date = new Long(time);
        if (which.equals("i")) {
            result = cashInAndCashOutService.updateCashIn(format.format(date * 1000L), site, people, money, remark,
                    itemId, subItemId, id, accountId);
        } else {
            result = cashInAndCashOutService.updateCashOut(format.format(date * 1000L), site, people, money, remark,
                    itemId, subItemId, id, accountId);
        }
        results.put("result", result);
        getStrResponse.writer(results);
    }

    /**
     * 查询某一成员下某一账户收支记录
     *
     * @throws Exception
     */
    public void accountQuery() throws Exception {
        String json = getStrResponse.getStrResponse();
        if (!json.equals("")) {
            JSONObject jsonRequest = JSONObject.fromObject(json);
            which = jsonRequest.getString("which");
            accountId = jsonRequest.getInt("accountId");
            memberId = jsonRequest.getInt("memberId");
        }
        if (which.equals("i")) {
            getStrResponse.writer(cashInAndCashOutService.queryCashInByAccount(accountId, memberId));
        } else {
            getStrResponse.writer(cashInAndCashOutService.queryCashOutByAccount(accountId, memberId));
        }
    }

    /**
     * 获取某一成员某一时间段收支记录并计算总金额
     *
     * @throws Exception
     */
    public void timeQuery() throws Exception {
        String json = getStrResponse.getStrResponse();
        List<Object> results = new ArrayList<>();
        if (!json.equals("")) {
            JSONObject jsonRequest = JSONObject.fromObject(json);
            which = jsonRequest.getString("which");
            memberId = jsonRequest.getInt("memberId");
            startTime = jsonRequest.getString("startTime");
            endTime = jsonRequest.getString("endTime");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Long date = new Long(startTime);
        Long date2 = new Long(endTime);
        System.out.println(date + "," + date2);
        List lists = cashInAndCashOutService.queryByTime(memberId, format.parse(format.format(date * 1000L)), format.parse(format.format(date2 * 1000L)), which);
        double sum = cashInAndCashOutService.memberSumCash(memberId, format.parse(format.format(date * 1000L)), format.parse(format.format(date2 * 1000L)), which);
        Map<String, Double> map = new HashMap<>();
        map.put("sum", sum);
        results.add(lists);
        results.add(map);
        getStrResponse.writer(results);
    }

    /**
     * 获取某一年每个月的收支总和
     *
     * @throws Exception
     */
    public void yearSum() throws Exception {
        String json = getStrResponse.getStrResponse();
        if (!json.equals("")) {
            JSONObject jsonRequest = JSONObject.fromObject(json);
            which = jsonRequest.getString("which");
            memberId = jsonRequest.getInt("memberId");
            year = jsonRequest.getString("year");
        }
        getStrResponse.writer(cashInAndCashOutService.yearSum(memberId, year, which));
    }
}
