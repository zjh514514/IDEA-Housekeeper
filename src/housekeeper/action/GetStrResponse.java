package housekeeper.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONWriter;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;

/**
 * 获取json数据
 *
 * @author zjh514514
 */
@Component
public class GetStrResponse {

    public String getStrResponse() {
        ActionContext ctx = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
        InputStream inputStream;
        StringBuilder strResponse = new StringBuilder();
        try {
            inputStream = request.getInputStream();
            String strMessage;
            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            while ((strMessage = reader.readLine()) != null) {
                strResponse.append(strMessage);
            }
            reader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strResponse.toString();
    }

    public void writer(Object results) throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json;charset=utf-8");
        JSONWriter writer = new JSONWriter(response.getWriter());
        writer.writeObject(results);
        writer.flush();
        writer.close();
    }

    public Map setStatus(Integer size) {
        Map<String, Integer> results = new HashMap<>();
        if (size > 0) {
            results.put("status", 200);
        } else {
            results.put("status", 201);
        }
        return results;
    }
}
