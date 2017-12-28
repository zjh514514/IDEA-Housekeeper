package housekeeper.action;

import com.opensymphony.xwork2.ActionSupport;
import housekeeper.service.AccountService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
public class AccountAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = -7911753295581288255L;

    @Autowired
    private AccountService accountService;
    @Autowired
    private GetStrResponse getStrResponse;

    private Integer id;
    private String accountName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * 账户选项添加
     *
     * @throws Exception
     */
    public void add() throws Exception {
        Map<String, String> results = new HashMap<>();
        String json = getStrResponse.getStrResponse();
        if (!json.equals("")) {
            JSONObject jsonRequest = JSONObject.fromObject(json);
            accountName = jsonRequest.getString("accountName");
        }
        String result = accountService.addAccount(accountName);
        results.put("status", result);
        getStrResponse.writer(results);
    }

    /**
     * 账户选项修改
     *
     * @throws Exception
     */
    public void update() throws Exception {
        Map<String, String> results = new HashMap<>();
        String json = getStrResponse.getStrResponse();
        if (!json.equals("")) {
            JSONObject jsonRequest = JSONObject.fromObject(json);
            id = jsonRequest.getInt("id");
            accountName = jsonRequest.getString("accountName");
        }
        String result = accountService.updateAccount(id, accountName);
        results.put("status", result);
        getStrResponse.writer(results);
    }

    /**
     * 账户选项查询
     *
     * @throws Exception
     */
    public void query() throws Exception {
        Map<String, Object> results = new HashMap<>();
        List accounts = accountService.query();
        results.put("data", accounts);
        results.putAll(getStrResponse.setStatus(accounts.size()));
        getStrResponse.writer(results);
    }
}
