package es.kr.playraonchild.appLockService;

import android.accessibilityservice.AccessibilityService;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.ArrayList;

import es.kr.playraonchild.MainActivity;
import es.kr.playraonchild.vo.AppLockVo;

public class AppLockService extends AccessibilityService implements TextToSpeech.OnInitListener {

        private static final String LOG_TAG = "AppLockService";

        @Override
        public void onServiceConnected() {
        }

        //서비스 STOP
        @Override
        public boolean stopService(Intent name) {
            return super.stopService(name);
        }

        @Override
        public void onAccessibilityEvent(AccessibilityEvent event) {

            AccessibilityNodeInfo source = event.getSource();
            if ( source != null ) {
                if( source.getPackageName() != null ){
                    final Context context = getApplicationContext();
                    // 잠금 설정된 앱 리스트 조회
                    ArrayList<AppLockVo> voList = getAppLockList();

                    String className = (String)source.getClassName();   // Class명을 취득
                    System.out.println("======  source.getPackageName()["+source.getPackageName()+"] className["+className+"]==");
                    if( !appLockCheck(voList,source.getPackageName().toString()) ){

                        Intent intentHome = new Intent(getApplicationContext(),MainActivity.class);
                        intentHome.setAction("android.intent.action.MAIN");
                        intentHome.addCategory("android.intent.category.HOME");
                        intentHome.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                                | Intent.FLAG_ACTIVITY_FORWARD_RESULT
                                | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP
                                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                        startActivity(intentHome);
                    }


                }
            }
        }

    //앱 잠금 체크
    public boolean appLockCheck(ArrayList<AppLockVo> voList, String packageName) {

        for(int i =0; i < voList.size(); i++){
            AppLockVo appLockVo = voList.get(i);
            String name = appLockVo.getPackageName();
            if( packageName.equals(name)){
                return true;
            }
        }
        return false;
    }

        //앱 잠금 리스트 조회
        public ArrayList<AppLockVo> getAppLockList() {

            ArrayList<AppLockVo> voList = new ArrayList<>();
            AppLockVo vo = new AppLockVo();
            vo.setPackageName("com.example.es.myspiner");
            voList.add(vo);

            AppLockVo vo1 = new AppLockVo();
            vo1.setPackageName("com.playraon.playraons");
            voList.add(vo1);

            AppLockVo vo2 = new AppLockVo();
            vo2.setPackageName("com.android.settings");
            voList.add(vo2);

            AppLockVo vo3 = new AppLockVo();
            vo3.setPackageName("com.android.dialer");
            voList.add(vo3);

            AppLockVo vo4 = new AppLockVo();
            vo4.setPackageName("com.google.android.apps.messaging");
            voList.add(vo4);

            AppLockVo vo5 = new AppLockVo();
            vo5.setPackageName("es.kr.playraonchild");
            voList.add(vo5);

            AppLockVo vo6 = new AppLockVo();
            vo6.setPackageName("com.google.android.apps.nexuslauncher");
            voList.add(vo6);

            AppLockVo vo7 = new AppLockVo();
            vo7.setPackageName("com.google.android.googlequicksearchbox");
            voList.add(vo7);

            AppLockVo vo8 = new AppLockVo();
            vo8.setPackageName("com.android.inputmethod.latin");
            voList.add(vo8);



            return voList;
        }

        @Override
        public void onInterrupt() {
        }

        @Override
        public void onInit(int status) {
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }

        @Override
        protected boolean onKeyEvent(KeyEvent event) {
            return false;
        }
    }
