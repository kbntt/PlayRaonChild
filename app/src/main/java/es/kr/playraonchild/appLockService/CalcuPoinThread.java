package es.kr.playraonchild.appLockService;


import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

public class CalcuPoinThread extends Thread {
    private static final String TAG = "CalcuPoinThread";
    private boolean runThread = true;
    private String appName = "";
    private int myPoint = 0;
    private int pointTime = 1;// 1포인트 삭감할 초

    Context context;
    //private DBOpenHelper mDbOpenHelper;

    public CalcuPoinThread(Context context) {
        System.out.println("CalcuPoinThread 생성자 : ");
        setContext(context);
//        mDbOpenHelper = new DBOpenHelper(getContext());
//
//        try{
//            mDbOpenHelper.open();
//        }catch (java.sql.SQLException e){
//
//        }

    }
    public void run() {
        int second = 0;
        System.out.println("run["+runThread+"]: "+myPoint);
        while (runThread) {
            second++;
            myPoint = calculationPoint( myPoint, second);
            //point 계산이후 second을 0으로 초기화
            if( second == pointTime ){ second = 0; }
            System.out.println("계산된 myPoint : "+myPoint);
            try {
                // 스레드에게 수행시킬 동작들 구현
                Thread.sleep(1000); // 1초간 Thread를 잠재운다
                isRunningProcess(context, "packageName");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println("경과된 시간 : "+Integer.toString(second));
        }
    }

    public void stopThread() {
        runThread = false;
    }
    public void runThread(){
        runThread = true;
    }

    public boolean getRunThread(){
        return this.runThread ;
    }
    public void setAppName(String appName){
        this.appName = appName;
    }

    public String getAppName(){
        return this.appName ;
    }
    public void setContext(Context context){
        System.out.println("========== setContext ==========");
        this.context = context;
    }
    public Context getContext(){
        System.out.println("========== getContext ==========");
        return this.context ;
    }
    /*
     * Created by kbntt on 2018-02-24.
     * 포인트 세팅
     */
    public void setMyPoint() {
        System.out.println("========== getMyPoint context["+context+"]==========");
        //this.myPoint = mDbOpenHelper.selectMyPoint();
        System.out.println("========== myPoint["+myPoint+"] ==========");
    }
    /*
     * Created by kbntt on 2018-02-24.
     * 포인트 조회
     */
    public int getMyPoint() {
        System.out.println("========== getMyPoint ==========");
        return this.myPoint;
    }
    public void insertMyPoint(int myPoint) {
        System.out.println("========== insertMyPoint myPoint["+myPoint+"]==========");

//        mDbOpenHelper.deleteMyPoint();
//        mDbOpenHelper.insertMyPoint(myPoint);

    }
    /*
     * Created by kbntt on 2018-02-24.
     * 포인트 계산 : 초를 분으로
     */
    private int calculationPoint( int myPoint, int second) {
        System.out.println("========== calculationPoint ==========");
        int returnPoint;
        if( second == pointTime){
            returnPoint = myPoint - 1;
        }else{
            returnPoint = myPoint;
        }
        return returnPoint;
    }


    // Process가 실행중인지 여부 확인.
    public boolean isRunningProcess(Context context1, String packageName) {
        System.out.println("========== isRunningProcess ==========");
        boolean isRunning = false;

        ActivityManager actMng = (ActivityManager)context1.getSystemService(context1.ACTIVITY_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> list = actMng.getRunningAppProcesses();



        int i =0;
        for(ActivityManager.RunningAppProcessInfo rap : list)
        {
            System.out.println("======= rap.processName =====>"+rap.processName);
            System.out.println("======= rap.pid =====>"+rap.pid);


            //android.os.Process.sendSignal(list.get(i).pid, android.os.Process.SIGNAL_KILL);
            //actMng.killBackgroundProcesses(list.get(i).processName);
        }

        return isRunning;
    }
}