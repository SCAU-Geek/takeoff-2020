package scau.xilou31.geek2020;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CrawlerController {
    @RequestMapping(value = "/showname",method = RequestMethod.GET)
    public JSONObject showName(@RequestParam String number,@RequestParam String password){
        CrawlerByOkHttp crawlerByOkHttp = new CrawlerByOkHttp(number,password);
        return crawlerByOkHttp.showName();
    }
}
