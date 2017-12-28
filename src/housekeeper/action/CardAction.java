package housekeeper.action;

import com.opensymphony.xwork2.ActionSupport;
import housekeeper.service.CardService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
public class CardAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = -4550468265979662129L;

    @Autowired
    private CardService cardService;
    @Autowired
    private GetStrResponse getStrResponse;

    private String name;
    private String number;
    private Double money;
    private String remark;
    private Integer memberId;
    private Integer id;
    private String which;

    public String getWhich() {
        return which;
    }

    public void setWhich(String which) {
        this.which = which;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 保存银行卡
     *
     * @throws Exception
     */
    public void save() throws Exception {
        Map<String, String> results = new HashMap<>();
        String json = getStrResponse.getStrResponse();
        if (!json.equals("")) {
            JSONObject jsonObject = JSONObject.fromObject(json);
            name = jsonObject.getString("name");
            number = jsonObject.getString("number");
            money = jsonObject.getDouble("money");
            remark = jsonObject.getString("remark");
            memberId = jsonObject.getInt("memberId");
        }
        results.put("status", cardService.addCard(name, number, money, remark, memberId));
        getStrResponse.writer(results);
    }

    /**
     * 修改银行卡信息
     *
     * @throws Exception
     */
    public void update() throws Exception {
        Map<String, String> results = new HashMap<>();
        String json = getStrResponse.getStrResponse();
        if (!json.equals("")) {
            JSONObject jsonObject = JSONObject.fromObject(json);
            name = jsonObject.getString("name");
            number = jsonObject.getString("number");
            money = jsonObject.getDouble("money");
            remark = jsonObject.getString("remark");
            id = jsonObject.getInt("id");
        }
        results.put("status", cardService.updateCard(name, number, money, remark, id));
        getStrResponse.writer(results);
    }

    /**
     * 查询
     *
     * @throws Exception
     */
    public void query() throws Exception {
        Map<String, Object> results = new HashMap<>();
        String json = getStrResponse.getStrResponse();
        if (!json.equals("")) {
            JSONObject jsonObject = JSONObject.fromObject(json);
            memberId = jsonObject.getInt("memberId");
            id = jsonObject.getInt("id");
            which = jsonObject.getString("which");
        }
        List list = new ArrayList();
        switch (which) {
            case "id":
                list = cardService.queryById(id);
                break;
            case "member":
                list = cardService.queryByMember(memberId);
                break;
        }
        results.put("data", list);
        results.putAll(getStrResponse.setStatus(list.size()));
        getStrResponse.writer(results);
    }
}
