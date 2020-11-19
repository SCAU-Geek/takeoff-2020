package Showname;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.util.HashMap;
import java.util.Map;

/**
 * @author czs
 * @description 通过爬虫爬取教务系统的信息，得到登录账号的信息
 * @date 2020/11/20
 */
public class GET_NAME{

    /*
    public static void main(String[] args)  throws Exception {
        GET_NAME get_name = new GET_NAME();
        get_name.login("201925510115","15218562790");
    }

     */


    public String login(String userCode,String password) throws Exception {
        //设置数据,将登录信息转换为json格式
        Map<String,String> login_data = new HashMap<>();
        login_data.put("userCode",userCode);
        login_data.put("password",password);
        login_data.put("kaptcha","testa");
        login_data.put("userCodeType","account");

        //装换为json格式
        Gson gson = new Gson();
        String datas = gson.toJson(login_data);

        //通过url和header来对网站进行模拟登录
        Connection.Response response1 = Jsoup.connect("https://jwxt.scau.edu.cn/secService/login")
                .ignoreContentType(true)  //忽略类型验证
                .header("Accept","application/json, text/plain, */*") //设置响应头
                .header("app","PCWEB")
                .header("KAPTCHA-KEY-GENERATOR-REDIS","securityKaptchaRedisServiceAdapter")
                .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                        "(KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36")
                .header("Content-Type","application/json")
                .requestBody(datas)
                .method(Connection.Method.POST)
                .execute();

        //提取登录后的token
        String rs_body = response1.body();
        JsonElement rs_json = new JsonParser().parse(rs_body);
        String data = String.valueOf(rs_json.getAsJsonObject().get("data"));

        JsonElement token_json = new JsonParser().parse(data);
        String token = String.valueOf(token_json.getAsJsonObject().get("token"));
        //提取后的token有引号，去除掉
        String token1 = token.substring(1,token.length()-1);

        //获得时间戳
        long currentTime=System.currentTimeMillis();

        //通过url和header来对网站进行模拟登录
        Connection.Response response2 = Jsoup.connect("https://jwxt.scau.edu.cn/secService/assert.json?resourceCode=resourceCode&apiCode=framework.sign.controller.SignController.asserts&t="+currentTime)
                .ignoreContentType(true)  //忽略类型验证
                .header("Accept","application/json, text/plain, */*") //设置响应头
                .header("app","PCWEB")
                .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                        "(KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36")
                .header("TOKEN",token1)
                .method(Connection.Method.GET)
                .cookies(response1.cookies())
                .execute();

        //从返回信息json中提取名字
        String rs2_body = response2.body();
        JsonElement rs2_json = new JsonParser().parse(rs2_body);
        String data2 = String.valueOf(rs2_json.getAsJsonObject().get("data"));
        //System.out.println(data2);
        JsonElement userInfo_json = new JsonParser().parse(data2);
        String userInfo = String.valueOf(userInfo_json.getAsJsonObject().get("userInfo"));
        //System.out.println(userInfo);
        JsonElement userName_json = new JsonParser().parse(userInfo);
        String userName = String.valueOf(userName_json.getAsJsonObject().get("userName"));
        String userName1 = userName.substring(1,userName.length()-1);

        //将信息转化为json/
        Map<String,String> info = new HashMap<>();
        info.put("number",userCode);
        info.put("password",password);
        info.put("name",userName1);

        String info1 = gson.toJson(info);

        return info1;
    }
}