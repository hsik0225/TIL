package com.hyunsiks.spring5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    // Model 파라미터는 컨트롤러의 처리 결과를 뷰에 전달한다
    @GetMapping("/hello")
    public String hello(Model model, @RequestParam(value = "name", required = false) String name) {

        // "greeting" 이라는 모델 속성에 값을 설정한다
        model.addAttribute("greeting", "안녕하세요, " + name);

        // 컨트롤러의 처리 결과를 보여줄 뷰 이름으로 "hello" 를 사용한다
        return "hello";
    }
}
