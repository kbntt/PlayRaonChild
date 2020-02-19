package es.kr.playraonchild;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import es.kr.playraonchild.com.CallbackWithJSONArray;
import es.kr.playraonchild.com.CommonUtil;
import es.kr.playraonchild.com.Constant;
import es.kr.playraonchild.com.NetworkTask;
import es.kr.playraonchild.vo.PlayRaonVo;

import static android.widget.Toast.*;

public class MainFragment extends Fragment {

    private static final Intent sSettingsIntent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);

    private static final String TAG = "PlayRaon MainFragment";
    private static final String ATTRIBUTE_REGIST = "regist";
    private EditText mParentPhoneNumberEditText;
    private EditText mPasswordEditText;
    private Button mRegistButton;
    private Button mAvailableButton;
    private Button mNoAvailableButton;

    private String childPhoneNumber = "";
    private PlayRaonVo mPlayRaonVo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPlayRaonVo = PlayRaonVo.get(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        mParentPhoneNumberEditText = v.findViewById(R.id.edTxtParentPhoneNumber);
        mPasswordEditText = v.findViewById(R.id.edTxtPassword);
        mRegistButton = v.findViewById(R.id.btnRegist);
        mAvailableButton = v.findViewById(R.id.btnAvailable);
        mNoAvailableButton = v.findViewById(R.id.btnNoAvailable);

        Toast.makeText(getContext(),"이메일을 확인해 주세요.1111", LENGTH_LONG).show();

        PlayRaonVo.get(getContext()).getRaonVo().getParentsVo();

        //////////////////////////////////////////////////////////////////////////
        Bundle extra = this.getArguments();
        if(extra != null) {
            extra = getArguments();
            childPhoneNumber = extra.getString("phoneNumber");
            System.out.println("childPhoneNumber["+childPhoneNumber+"]");

        }
        //////////////////////////////////////////////////////////////////////////

        mRegistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String parentPhoneNumber = mParentPhoneNumberEditText.getText().toString();
                    boolean checkPhoneNumber = CommonUtil.isVaildCell(parentPhoneNumber);
                    if(!checkPhoneNumber){
                        Toast.makeText(getContext(),"전화번호를 확인해 주세요.", LENGTH_LONG).show();
                        return;
                    }

                    String password = mPasswordEditText.getText().toString();
                    boolean checkPassword = CommonUtil.isisVaildPassword(password);
                    if(!checkPassword){
                        Toast.makeText(getContext(),"비밀번호를 확인해 주세요.", LENGTH_LONG).show();
                        return;
                    }
                    Log.d(TAG,"=== checkPhoneNumber["+checkPhoneNumber+"]  checkPassword["+checkPassword+"] ============");
                    HashMap<String, String> param = new HashMap();
                    param.put("attribute", ATTRIBUTE_REGIST);
                    param.put("parentPhoneNumber", parentPhoneNumber);
                    param.put("password", password);
                    param.put("childPhoneNumber", childPhoneNumber);

                    //PlayRaonVo.get(getContext()).getRaonVo().getParentsVo();
                    PlayRaonVo.get(getContext()).setAttribute(ATTRIBUTE_REGIST);
                    PlayRaonVo.get(getContext()).getRaonVo().getParentsVo().setPhoneNumber(parentPhoneNumber);
                    PlayRaonVo.get(getContext()).getRaonVo().getParentsVo().setPassword(password);
                    PlayRaonVo.get(getContext()).getRaonVo().getParentsVo().setChildPhoneNumber(childPhoneNumber);

                    networkConnect();
                }catch (Exception e){
                    Log.d(TAG,e.toString());
                }
            }
        });

        mAvailableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(getContext(),"setOnClickListener", LENGTH_LONG).show();
                    startActivity(sSettingsIntent);
                }catch (Exception e){
                    Log.d(TAG,e.toString());
                }
            }
        });

        return v;
    }

    private void networkConnect() {

        System.out.println("==== networkConnect ");

        try {

            String url = CommonUtil.getServerUrl(getContext())+"comm/join";
            ContentValues values = new ContentValues();
            values.put ("attribute",PlayRaonVo.get(getContext()).getAttribute());
            values.put ("parentPhoneNumber", PlayRaonVo.get(getContext()).getRaonVo().getParentsVo().getPhoneNumber() );
            values.put ("password", PlayRaonVo.get(getContext()).getRaonVo().getParentsVo().getPassword());
            values.put ("childPhoneNumber",PlayRaonVo.get(getContext()).getRaonVo().getParentsVo().getChildPhoneNumber());

            // AsyncTask를 통해 HttpURLConnection 수행.
            NetworkTask networkTask = new NetworkTask(url, values,new CallbackWithJSONArray() {
                @Override
                public void callback(JSONArray jsonArray, String type) {
                    networkCallback(jsonArray,type);
                }
            });
            networkTask.execute();
        }catch (Exception e){
            Log.d(TAG,e.toString());
        }

    }

    private void networkCallback(JSONArray jsonArray, String type) {
        System.out.println("==== networkCallback type["+type+"]");
        try {
            if( type.equals(ATTRIBUTE_REGIST)){
                setReceiveMag(jsonArray);
            }else if( type.equals("parents_login_check")){

                //setParentsLoginReceiveMag(jsonArray);
            }
        } catch (Exception e) {
            System.out.println("Exception:"+e.getMessage());
            // TODO: handle exception
        }

    }

    private void setReceiveMag(JSONArray jsonArray) {

        System.out.println("==== setReceiveMag ====jsonArray.length():"+jsonArray.length());
        try {
            for(int i = 0; i < jsonArray.length(); i++){
                // Array 에서 하나의 JSONObject 를 추출
                JSONObject dataJsonObject = jsonArray.getJSONObject(i);
                // 추출한 Object 에서 필요한 데이터를 표시할 방법을 정해서 화면에 표시
                // 필자는 RecyclerView 로 데이터를 표시 함
                String code = dataJsonObject.getString("code");
                String msg = dataJsonObject.getString("msg");
                System.out.println("code:"+code+"/msg:"+msg);

                if(Constant.CODE_100.equals(code)){
                    PlayRaonVo.get(getContext()).setLogin(true);
                    mAvailableButton.setVisibility(View.VISIBLE); // 화면에 보이게 한다.
                    mNoAvailableButton.setVisibility(View.VISIBLE); // 화면에 안보이게 한다.

                }else{
                    PlayRaonVo.get(getContext()).setLogin(false);
                    mAvailableButton.setVisibility(View.INVISIBLE); // 화면에 안보이게 한다.
                    mNoAvailableButton.setVisibility(View.INVISIBLE); // 화면에 안보이게 한다.
                    Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
                }

            }
        } catch (Exception e) {
            System.out.println("Exception:"+e.getMessage());
            // TODO: handle exception
        }
    }


}
