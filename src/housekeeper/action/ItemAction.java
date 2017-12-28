package housekeeper.action;

import com.opensymphony.xwork2.ActionSupport;
import housekeeper.service.ItemsService;
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
public class ItemAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 260340696080751436L;

    @Autowired
    private ItemsService itemsService;
    @Autowired
    private GetStrResponse getStrResponse;

    private Integer type;
    private Integer id;
    private String itemName;
    private String subItemName;
    private String which;

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSubItemName() {
        return subItemName;
    }

    public void setSubItemName(String subItemName) {
        this.subItemName = subItemName;
    }

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

    /**
     * 查询父类或子类
     *
     * @throws Exception
     */
    public void query() throws Exception {
        List list = new ArrayList();
        Map results = new HashMap();
        String json = getStrResponse.getStrResponse();
        if (!json.equals("")) {
            JSONObject jsonRequest = JSONObject.fromObject(json);
            which = jsonRequest.getString("which");
            type = jsonRequest.getInt("type");
            id = jsonRequest.getInt("itemId");
        }
        switch (which) {
            case "i":
                list = itemsService.queryItem(type);
                break;
            case "o":
                list = itemsService.querySubItem(id);
                break;
        }
        results.put("data", list);
        results.putAll(getStrResponse.setStatus(list.size()));
        getStrResponse.writer(results);

    }

    /**
     * 修改父类或子类名称
     *
     * @throws Exception
     */
    public void update() throws Exception {
        String result = "201";
        Map<String, String> results = new HashMap<>();
        String json = getStrResponse.getStrResponse();
        if (!json.equals("")) {
            JSONObject jsonRequest = JSONObject.fromObject(json);
            which = jsonRequest.getString("which");
            itemName = jsonRequest.getString("itemName");
            subItemName = jsonRequest.getString("subItemName");
            id = jsonRequest.getInt("id");
        }
        switch (which) {
            case "i":
                result = itemsService.updateItem(itemName, id);
                break;
            case "s":
                result = itemsService.updateSubItem(subItemName, id);
                break;
        }
        results.put("result", result);
        getStrResponse.writer(results);
    }

    /**
     * 增加父类或子类
     *
     * @throws Exception
     */
    public void add() throws Exception {
        String result = "201";
        Map<String, String> results = new HashMap<>();
        String json = getStrResponse.getStrResponse();
        if (!json.equals("")) {
            JSONObject jsonRequest = JSONObject.fromObject(json);
            which = jsonRequest.getString("which");
            itemName = jsonRequest.getString("itemName");
            type = jsonRequest.getInt("type");
            subItemName = jsonRequest.getString("subItemName");
            id = jsonRequest.getInt("id");
        }
        switch (which) {
            case "i":
                result = itemsService.addItems(itemName, type);
                break;
            case "s":
                result = itemsService.addSubItems(subItemName, id);
                break;
        }
        results.put("status", result);
        getStrResponse.writer(results);
    }

}
