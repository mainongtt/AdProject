package com.jd.api.config.Test;

import com.jd.api.Test.TestControllerApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhang kun
 * @Classname TestController
 * @Description TODO
 * @Date 2022/1/10 21:11
 */
@RestController
@RequestMapping("/test")
public class TestController implements TestControllerApi {

    @GetMapping("/a")
    public String test() {
        return "ok";
    }
}
