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

@Controller
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
        JSONObject jsonObject;
        if (!json.equals("")) {
            jsonObject = JSONObject.fromObject(json);
            which = jsonObject.getString("which");
            time = jsonObject.getString("time");
            site = jsonObject.getString("site");
            people = jsonObject.getString("people");
            money = jsonObject.getDouble("money");
            remark = jsonObject.getString("remark");
            memberId = jsonObject.getInt("memberId");
            itemId = jsonObject.getInt("itemId");
            subItemId = jsonObject.getInt("subItemId");
            accountId = jsonObject.getInt("accountId");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Long date = new Long(time);
        result = cashInAndCashOutService.add(format.format(date * 1000L), site, people, money, remark, memberId, itemId, subItemId, accountId, which);
        results.put("status", result);
        getStrResponse.writer(results);
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
        if (!json.equals("")) {
            JSONObject jsonObject = JSONObject.fromObject(json);
            which = jsonObject.getString("which");
            time = jsonObject.getString("time");
            site = jsonObject.getString("site");
            people = jsonObject.getString("people");
            money = jsonObject.getDouble("money");
            remark = jsonObject.getString("remark");
            memberId = jsonObject.getInt("memberId");
            itemId = jsonObject.getInt("itemId");
            subItemId = jsonObject.getInt("subItemId");
            accountId = jsonObject.getInt("accountId");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Long date = new Long(time);
        result = cashInAndCashOutService.update(format.format(date * 1000L), site, people, money, remark, itemId, subItemId, id, accountId, which);
        results.put("status", result);
        getStrResponse.writer(results);
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
            JSONObject jsonObject = JSONObject.fromObject(json);
            which = jsonObject.getString("which");
            memberId = jsonObject.getInt("memberId");
            startTime = jsonObject.getString("startTime");
            endTime = jsonObject.getString("endTime");
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

    public void query() throws Exception {
        List list = new ArrayList();
        String json = getStrResponse.getStrResponse();
        Map results = new HashMap();
        if (!json.equals("")) {
            JSONObject jsonObject = JSONObject.fromObject(json);
            which = jsonObject.getString("which");
            memberId = jsonObject.getInt("memberId");
            startTime = jsonObject.getString("startTime");
            endTime = jsonObject.getString("endTime");
            accountId = jsonObject.getInt("accountId");
            subItemId = jsonObject.getInt("subItemId");
            itemId = jsonObject.getInt("itemId");
            id = jsonObject.getInt("id");
        }
        switch (which) {
            case "mi":
                list = cashInAndCashOutService.queryCashInByMember(memberId);
                break;
            case "mo":
                list = cashInAndCashOutService.queryCashOutByMember(memberId);
                break;
            case "ii":
                list = cashInAndCashOutService.queryCashInById(id);
                break;
            case "io":
                list = cashInAndCashOutService.queryCashOutById(id);
                break;
            case "ti":
                list = cashInAndCashOutService.queryCashInByItem(itemId, memberId);
                break;
            case "to":
                list = cashInAndCashOutService.queryCashOutByItem(itemId, memberId);
                break;
            case "si":
                list = cashInAndCashOutService.queryCashInBySubItem(subItemId, memberId);
                break;
            case "so":
                list = cashInAndCashOutService.queryCashOutBySubItem(subItemId, memberId);
                break;
            case "ai":
                list = cashInAndCashOutService.queryCashInByAccount(accountId, memberId);
                break;
            case "ao":
                list = cashInAndCashOutService.queryCashOutByAccount(accountId, memberId);
                break;
        }
        results.put("data", list);
        results.putAll(getStrResponse.setStatus(list.size()));
        System.out.println(results);
        getStrResponse.writer(results);
    }

    /**
     * 获取某一年每个月的收支总和
     *
     * @throws Exception
     */
    public void yearSum() throws Exception {
        Map results = new HashMap();
        String json = getStrResponse.getStrResponse();
        if (!json.equals("")) {
            JSONObject jsonObject = JSONObject.fromObject(json);
            memberId = jsonObject.getInt("memberId");
            year = jsonObject.getString("year");
        }
        Map map = cashInAndCashOutService.yearSum(memberId, year);
        results.put("date", map);
        results.putAll(getStrResponse.setStatus(map.size()));
        getStrResponse.writer(results);
    }

    public void familyGather() throws Exception {
        Map results = new HashMap();
        String json = getStrResponse.getStrResponse();
        if (!json.equals("")) {
            JSONObject jsonObject = JSONObject.fromObject(json);
            id = jsonObject.getInt("id");
            year = jsonObject.getString("year");
        }
        List map = cashInAndCashOutService.familyGather(id, year);
        results.put("data", map);
        results.putAll(getStrResponse.setStatus(map.size()));
        getStrResponse.writer(results);
    }
}