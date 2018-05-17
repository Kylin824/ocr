package json;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.HashMap;
import java.util.Map;

public class JsonAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private Map<String,Object> jsonData;

    public String getOcrJson(){
        jsonData = new HashMap<String,Object>();
        ActionContext act = ActionContext.getContext();
        String id = act.get("id").toString();
        String username = act.get("username").toString();
        String date = act.get("date").toString();
        String text = act.get("text").toString();
        jsonData.put("id", id);
        jsonData.put("username", username);
        jsonData.put("date", date);
        jsonData.put("text", text);
        return Action.SUCCESS;
    }

    public String testJson(){
        jsonData = new HashMap<String,Object>();
        ActionContext act = ActionContext.getContext();
        String id = "123";
        String username = "cindy";
        String date = "1237889182734";
        String text = "在我们的Action中，该如何去抉择呢？我们遵循的原则是：如果ActionContext能够实现我们的功能";
        jsonData.put("id", id);
        jsonData.put("username", username);
        jsonData.put("date", date);
        jsonData.put("text", text);
        return Action.SUCCESS;
    }

    public Map<String,Object> getJsonData() {
        return jsonData;
    }

    public void setJsonData(Map<String,Object> jsonData) {
        this.jsonData = jsonData;
    }

/*    public String getOcrJson()
    {
        jsonData = new HashMap<String,Object>();
        jsonData.put();
    }*/
}
