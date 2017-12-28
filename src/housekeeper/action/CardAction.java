package housekeeper.action;

import com.opensymphony.xwork2.ActionSupport;
import housekeeper.service.CardService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
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
        String json = getStrResponse.getStrResponse();
        if (!json.equals("")) {
            JSONObject jsonRequest = JSONObject.fromObject(json);
            name = jsonRequest.getString("name");
            number = jsonRequest.getString("number");
            money = jsonRequest.getDouble("money");
            remark = jsonRequest.getString("remark");
            memberId = jsonRequest.getInt("memberId");
        }
        String result = cardService.addCard(name, number, money, remark, memberId);
        Map<String, String> results = new HashMap<>();
        results.put("result", result);
        getStrResponse.writer(results);
    }

    /**
     * 修改银行卡信息
     *
     * @throws Exception
     */
    public void update() throws Exception {
        String json = getStrResponse.getStrResponse();
        if (!json.equals("")) {
            JSONObject jsonRequest = JSONObject.fromObject(json);
            name = jsonRequest.getString("name");
            number = jsonRequest.getString("number");
            money = jsonRequest.getDouble("money");
            remark = jsonRequest.getString("remark");
            id = jsonRequest.getInt("id");
        }
        String result = cardService.updateCard(name, number, money, remark, id);
        Map<String, String> results = new HashMap<>();
        results.put("result", result);
        getStrResponse.writer(results);
    }

    /**
     * 查询某一成员的银行卡
     *
     * @throws Exception
     */
    public void memberQuery() throws Exception {
        String json = getStrResponse.getStrResponse();
        if (!json.equals("")) {
            JSONObject jsonRequest = JSONObject.fromObject(json);
            memberId = jsonRequest.getInt("memberId");
        }
        getStrResponse.writer(cardService.queryByMember(memberId));
    }

    /**
     * 查询某一银行卡信息
     *
     * @throws Exception
     */
    public void idQuery() throws Exception {
        String json = getStrResponse.getStrResponse();
        if (!json.equals("")) {
            JSONObject jsonRequest = JSONObject.fromObject(json);
            id = jsonRequest.getInt("id");
        }
        getStrResponse.writer(cardService.queryById(id));
    }
}
