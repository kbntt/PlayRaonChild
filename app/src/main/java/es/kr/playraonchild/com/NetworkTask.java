package es.kr.playraonchild.com;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import androidx.core.content.ContextCompat;

public class NetworkTask extends AsyncTask<Void, Void, String> {

    static final String TAG = "NetworkTask";
    private String url;
    private ContentValues values;
    private CallbackWithJSONArray callbackWithJSONArray;

    public NetworkTask(String url, ContentValues values, CallbackWithJSONArray callback) {
        System.out.println("==== NetworkTask url["+url+"] values["+values+"]");
        this.url = url;
        this.values = values;
        this.callbackWithJSONArray = callback;
    }

    @Override
    protected String doInBackground(Void... params) {
        System.out.println("=== doInBackground 1  ===");
        String result; // 요청 결과를 저장할 변수.


        RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
        result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.
        System.out.println("=== result  ===>"+result);
        return result;
    }

    @Override
    protected void onPostExecute(String response) {
        System.out.println("=== onPostExecute 1 response["+response+"]===");
        super.onPostExecute(response);

        ///////////////////////////////////////////////////////////////////////////////////
        //

        System.out.println("=== onPostExecute 2 ===");
        StringBuffer sb = new StringBuffer();
        /* */
        try {
            JSONObject wrapObject = new JSONObject(response);

            JSONArray jsonArray = new JSONArray(wrapObject.getString("result"));
            String type = wrapObject.getString("type");

            callbackWithJSONArray.callback(jsonArray,type);


        } catch (Exception e) {
            System.out.println("Exception:"+e.getMessage());
            // TODO: handle exception
        }

    }
}
