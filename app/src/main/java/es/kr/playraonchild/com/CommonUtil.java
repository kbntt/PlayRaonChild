package es.kr.playraonchild.com;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class CommonUtil {

    public static String getServerUrl(Context context) throws Exception {
        String serverUrl = "http://172.30.1.28:8081/";//http://www.essolution.co.kr/";
        return serverUrl;
    }

    /**
     *
     * XXXX 형식의 휴대폰번호 앞, 중간, 뒤 문자열 3개 입력 받아 유효한 휴대폰번호형식인지 검사
     *  ex)
     * isVaildCell("01012345678");
     * => true
     * @return  유효한 휴대폰번호 형식인지 여부 (True/False)
     * @throws Exception
     */
    public static boolean isVaildCell(String phoneNum) throws Exception {
        if( phoneNum.length() != 11){
            return false;
        }

        String[] check = {"010", "011", "016", "017", "018", "019"}; //유효한 휴대폰 첫자리 번호 데이터
        String cell1 = phoneNum.substring(0, 3);
        String cell2 = phoneNum.substring(3, 7);
        String cell3 = phoneNum.substring(7, 11);
        String temp = cell1 + cell2 + cell3;
        System.out.println("=== cell1["+cell1+"] cell2["+cell2+"] cell3["+cell3+"] ");

        for(int i=0; i < temp.length(); i++){
            if (temp.charAt(i) < '0' || temp.charAt(i) > '9') {
                return false;
            }
        }   //숫자가 아닌 값이 들어왔는지를 확인

        for(int i = 0; i < check.length; i++){
            if(cell1.equals(check[i])) {
                break;
            }
            if(i == check.length - 1) {
                return false;
            }
        }   // 휴대폰 첫자리 번호입력의 유효성 체크

        if(cell2.charAt(0) == '0') {
            return false;
        }

        if(cell2.length() != 3 && cell2.length() !=4) {
            return false;
        }
        if(cell3.length() != 4) {
            return false;
        }

        return true;
    }


    /**
     *
     * => true
     *
     * @param
     * @return  유효한 비밀번호 형식인지 여부 (True/False)
     * @throws Exception
     */
    public static boolean isisVaildPassword(String Password) throws Exception {
        if( Password.length() < 8){
            return false;
        }
        return true;
    }

}
