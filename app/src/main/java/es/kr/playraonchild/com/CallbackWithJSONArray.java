package es.kr.playraonchild.com;

import org.json.JSONArray;

import java.io.Serializable;

public interface CallbackWithJSONArray extends Serializable {
    public void callback(JSONArray jsonArray, String type);
}
