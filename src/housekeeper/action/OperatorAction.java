package housekeeper.action;

import housekeeper.service.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
public class OperatorAction {

    @Autowired
    private GetStrResponse getStrResponse;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ItemsService itemsService;
    @Autowired
    private CardService cardService;
    @Autowired
    private CashInAndCashOutService cashInAndCashOutService;
    @Autowired
    private FamilyAndMemberService familyAndMemberService;

    private Integer id;
    private String which;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWhich() {
        return which;
    }

    public void setWhich(String which) {
        this.which = which;
    }

    public void delete() throws Exception {
        String result = "202";
        Map<String, String> results = new HashMap<>();
        String json = getStrResponse.getStrResponse();
        JSONObject jsonRequest;
        if (!json.equals("")) {
            jsonRequest = JSONObject.fromObject(json);
            which = jsonRequest.getString("which");
            id = jsonRequest.getInt("id");
        }
        switch (which) {
            case "a":
                result = accountService.delete(id);
                break;
            case "i":
                result = itemsService.delete(id, which);
                break;
            case "s":
                result = itemsService.delete(id, which);
                break;
            case "m":
                result = familyAndMemberService.delete(id, which);
                break;
            case "f":
                result = familyAndMemberService.delete(id, which);
                break;
            case "c":
                result = cardService.delete(id);
                break;
            case "ci":
                result = cashInAndCashOutService.delete(id, which);
                break;
            case "co":
                result = cashInAndCashOutService.delete(id, which);
                break;
        }
        results.put("status", result);
        getStrResponse.writer(results);
    }
}
