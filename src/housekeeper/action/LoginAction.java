package housekeeper.action;

import com.opensymphony.xwork2.ActionSupport;
import housekeeper.service.FamilyAndMemberService;
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
public class LoginAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = -743176005568741483L;

    @Autowired
    private FamilyAndMemberService familyAndMemberService;
    @Autowired
    private GetStrResponse getStrResponse;

    private String username;
    private String password;
    private String name;
    private String role;
    private String familyName;
    private Double balance;
    private String which;
    private Integer id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getWhich() {
        return which;
    }

    public void setWhich(String which) {
        this.which = which;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 登陆
     *
     * @throws Exception
     */
    public void login() throws Exception {
        Map<String, Object> results = new HashMap<>();
        String json = getStrResponse.getStrResponse();
        if (!json.equals("")) {
            JSONObject jsonObject = JSONObject.fromObject(json);
            which = jsonObject.getString("which");
            username = jsonObject.getString("username");
            password = jsonObject.getString("password");
        }
        Object object = familyAndMemberService.login(username, password, which);
        results.put("data", object == null ? "" : object);
        if (results.get("data") != "") {
            results.put("status", 200);
        } else {
            results.put("status", 201);
        }
        getStrResponse.writer(results);
    }

    /**
     * 添加
     *
     * @throws Exception
     */
    public void sign() throws Exception {
        String result = "201";
        Map<String, String> results = new HashMap<>();
        String json = getStrResponse.getStrResponse();
        if (!json.equals("")) {
            JSONObject jsonObject = JSONObject.fromObject(json);
            which = jsonObject.getString("which");
            username = jsonObject.getString("username");
            password = jsonObject.getString("password");
            name = jsonObject.getString("name");
            role = jsonObject.getString("role");
            id = jsonObject.getInt("id");
            familyName = jsonObject.getString("familyName");
        }
        switch (which) {
            case "m":
                result = familyAndMemberService.memberSign(username, password, name, role, id);
                break;
            case "f":
                result = familyAndMemberService.familySign(username, password, familyName);
                break;
        }
        results.put("status", result);
        getStrResponse.writer(results);
    }

    /**
     * 修改信息
     *
     * @throws Exception
     */
    public void update() throws Exception {
        String result = "201";
        Map<String, String> results = new HashMap<>();
        String json = getStrResponse.getStrResponse();
        if (!json.equals("")) {
            JSONObject jsonObject = JSONObject.fromObject(json);
            which = jsonObject.getString("which");
            password = jsonObject.getString("password");
            role = jsonObject.getString("role");
            balance = jsonObject.getDouble("balance");
            id = jsonObject.getInt("id");
            name = jsonObject.getString("name");
            familyName = jsonObject.getString("familyName");
        }
        switch (which) {
            case "m":
                result = familyAndMemberService.memberUpdate(password, role, balance, id, name);
                break;
            case "f":
                result = familyAndMemberService.familyUpdate(password, familyName, id);
                break;
        }
        results.put("result", result);
        getStrResponse.writer(results);
    }

    public void query() throws Exception {
        List list = new ArrayList();
        Map<String, Object> results = new HashMap<>();
        String json = getStrResponse.getStrResponse();
        if (!json.equals("")) {
            JSONObject jsonObject = JSONObject.fromObject(json);
            which = jsonObject.getString("which");
            id = jsonObject.getInt("id");
        }
        switch (which) {
            case "m":
                list = (List) familyAndMemberService.memberGet(id);
                break;
            case "f":
                list = familyAndMemberService.familyGet(id);
                break;
            case "fm":
                list = familyAndMemberService.memberFamilyGet(id);
                break;
        }
        results.put("data", list);
        results.putAll(getStrResponse.setStatus(list.size()));
        getStrResponse.writer(results);
    }
}
