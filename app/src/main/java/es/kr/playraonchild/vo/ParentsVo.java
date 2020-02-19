package es.kr.playraonchild.vo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParentsVo {

    private static ParentsVo sParentsVo;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private RaonVo mRaonVo;
    private String uuid              = ""; //PK
    private String phoneNumber       = ""; //연락처
    private String password          = ""; //회원 패스워드
    private String email             = ""; //회원 이메일
    private String username          = ""; //회원 실명
    private String nickname          = ""; //회원 닉네임
    private String emailCert         = ""; //이메일 인증을 받았는지 여부
    private Date   registerDatetime  = null; //회원 등록일
    private Date   lastloginDatetime = null; //최종 로그인 시간
    private String lastloginIp       = ""; //최종 로그인 IP
    private String adminmemo         = ""; //관리자용 메모
    private Date   createDatetime    = null; //생성일
    private String createUser        = ""; //생성자
    private Date   updateDatetime    = null; //수정일
    private String updateUser        = ""; //수정자
    private List<ChildVo> mListChildVo   = new ArrayList<>();
    private String childPhoneNumber     = ""; //자녀


    public static ParentsVo get(Context context) {
        if (sParentsVo == null) {
            sParentsVo = new ParentsVo(context);
        }
        return sParentsVo;
    }

    private ParentsVo(Context context) {
        mContext = context.getApplicationContext();
        //mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
    }

    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getEmailCert() {
        return emailCert;
    }
    public void setEmailCert(String emailCert) {
        this.emailCert = emailCert;
    }
    public Date getRegisterDatetime() {
        return registerDatetime;
    }
    public void setRegisterDatetime(Date registerDatetime) {
        this.registerDatetime = registerDatetime;
    }
    public Date getLastloginDatetime() {
        return lastloginDatetime;
    }
    public void setLastloginDatetime(Date lastloginDatetime) {
        this.lastloginDatetime = lastloginDatetime;
    }
    public String getLastloginIp() {
        return lastloginIp;
    }
    public void setLastloginIp(String lastloginIp) {
        this.lastloginIp = lastloginIp;
    }
    public String getAdminmemo() {
        return adminmemo;
    }
    public void setAdminmemo(String adminmemo) {
        this.adminmemo = adminmemo;
    }
    public Date getCreateDatetime() {
        return createDatetime;
    }
    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }
    public String getCreateUser() {
        return createUser;
    }
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    public Date getUpdateDatetime() {
        return updateDatetime;
    }
    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
    public String getUpdateUser() {
        return updateUser;
    }
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    public List<ChildVo> getListChildVo() {
        return mListChildVo;
    }
    public void setChildVo(ChildVo mChildVo) {
        mListChildVo.add(mChildVo);
    }
    public String getChildPhoneNumber() {
        return childPhoneNumber;
    }
    public void setChildPhoneNumber(String childPhoneNumber) {
        this.childPhoneNumber = childPhoneNumber;
    }
}
