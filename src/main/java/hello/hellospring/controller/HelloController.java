package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model)
    {
        model.addAttribute("data", "hello!!");
        return "hello"; //이 값이 template의 이름임....
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model)
    {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //response body에 return 값을 직접 넣어줄때
    public String helloString(@RequestParam("name") String name)
    {
        return "hello " + name; //SgringHttpMessageConverter
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name)
    {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //return이 객체이면 json 방식으로 만들어서 반환, MappingJackson2HttpMessageConverter
    }

    static class Hello
    {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
