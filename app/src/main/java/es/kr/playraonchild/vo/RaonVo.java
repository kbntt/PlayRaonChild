package es.kr.playraonchild.vo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class RaonVo {

    private static RaonVo sRaonVo;
    private Context mContext;
    private ParentsVo mParentsVo;
    private String raonVoName;

    public static RaonVo get(Context context) {
        if (sRaonVo == null) {
            sRaonVo = new RaonVo(context);
        }
        return sRaonVo;
    }

    private RaonVo(Context context) {
        mContext = context;
        mParentsVo = ParentsVo.get(context);
    }

    public ParentsVo getParentsVo(){
        return mParentsVo;
    }

}
