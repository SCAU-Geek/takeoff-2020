package scau.xilou31.geek2020;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CrawlerByOkHttp {

    private static String userNameUrl = "https://jwxt.scau.edu.cn/secService/assert.json?resourceCode=resourceCode&apiCode=framework.sign.controller.SignController.asserts&t=";
    private static String loginUrl = "https://jwxt.scau.edu.cn/secService/login";
    private String userCode;
    private String password;

    public CrawlerByOkHttp(String userCode,String password){
        this.userCode = userCode;
        this.password = password;
    }

    public static final MediaType JSONTYPE = MediaType.parse("application/json;charset=utf-8");

    public static String toCrawlUserName(String token, OkHttpClient okHttpClient) {
        Headers headers = new Headers.Builder()
                .add("Accept", "application/json, text/plain, */*")
                .add("app", "PCWEB")
                .add("TOKEN", token)
                .build();
        Date date = new Date();
        Request request = new Request.Builder()
                .url(userNameUrl + date.getTime())
                .get()
                .headers(headers)
                .build();
        try {
            Response response = okHttpClient.newCall(request)
                    .execute();
            JSONObject responseData = JSON.parseObject(response.body().string());
            JSONObject data = responseData.getJSONObject("data");
            JSONObject userInfo = data.getJSONObject("userInfo");
            return userInfo.getString("userName");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String loginAndReturnToken(Student student, OkHttpClient okHttpClient) {
        Headers headers = new Headers.Builder()
                .add("Accept", "application/json, text/plain, */*")
                .add("app", "PCWEB")
                .add("KAPTCHA-KEY-GENERATOR-REDIS", "securityKaptchaRedisServiceAdapter")
                .build();
        RequestBody requestBody = RequestBody.create(student.toString(), JSONTYPE);
        Request request = new Request.Builder()
                .url(loginUrl)
                .headers(headers)
                .post(requestBody)
                .build();
        try {
            Response response = okHttpClient.newCall(request)
                    .execute();
            JSONObject responseData = JSON.parseObject(response.body().string());
            JSONObject data = responseData.getJSONObject("data");
            return data.getString("token");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject showName() {
        ConcurrentHashMap<String, List<Cookie>> cookieStore = new ConcurrentHashMap<>();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {//这里可以做cookie传递，保存等操作
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {//可以做保存cookies操作
                        cookieStore.put(url.host(), cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {//加载新的cookies
                        List<Cookie> cookies = cookieStore.get(url.host());
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .build();
        Student student = new Student(userCode, password);
        String token = loginAndReturnToken(student, okHttpClient);
        String userName = toCrawlUserName(token,okHttpClient);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number",userCode);
        jsonObject.put("password",password);
        jsonObject.put("name",userName);
        return jsonObject;
    }
}
