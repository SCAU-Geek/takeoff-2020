package scau.xilou31.geek2020;

import com.alibaba.fastjson.JSONObject;
import okhttp3.FormBody;

public class Student {
    private String userCode;
    private String password;
    private String kaptcha;
    private String userCodeType;
    public Student(String userCode,String password){
        this.userCode = userCode;
        this.password = password;
        this.kaptcha = "testa";
        this.userCodeType = "account";
    }

    @Override
    public String toString() {
        JSONObject studentData = new JSONObject();
        studentData.put("userCode",userCode);
        studentData.put("password",password);
        studentData.put("kaptcha",kaptcha);
        studentData.put("userCodeType",userCodeType);
        return studentData.toJSONString();
    }
}
