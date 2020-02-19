package es.kr.playraonchild.vo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class PlayRaonVo {

    private static PlayRaonVo sPlayRaonVo;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private RaonVo mRaonVo;

    private boolean mLogin;
    private String mAttribute;

    public static PlayRaonVo get(Context context) {
        if (sPlayRaonVo == null) {
            sPlayRaonVo = new PlayRaonVo(context);
        }
        return sPlayRaonVo;
    }

    private PlayRaonVo(Context context) {
        mContext = context.getApplicationContext();
        mRaonVo = RaonVo.get(context);
        //mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
    }

    public RaonVo getRaonVo(){
        return mRaonVo;
    }

    public void setLogin(boolean login){
        mLogin = login;
    }

    public boolean getLogin(){
        return mLogin;
    }

    public void setAttribute(String attribute){
        mAttribute = attribute;
    }

    public String getAttribute(){
        return mAttribute;
    }

}
