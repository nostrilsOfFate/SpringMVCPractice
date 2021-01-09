package practice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {

//    @GetMapping("/hello")
//    public String helloPage(HttpServletRequest request){
//    String name = request.getParameter("name");
//    String surname = request.getParameter("surname");
//    System.out.println("Hello,  "+ name + " "+ surname);
//    return "first/hello";
//    }


    //Анотация выдает ошибку, если не будет параметров в запросе @RequestParam("name") String name

    //если  required = false  - в результате там будет 0
//    @GetMapping("/hello")
//    public String helloPage(@RequestParam(value = "name", required = false) String name,
//                            @RequestParam(value = "surname", required = false) String surname){
//        System.out.println("Hello,  "+ name + " "+ surname);
//        return "first/hello";
//    }


    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model) {
        model.addAttribute("message", "Hello, " + name + " " + surname);
        return "first/hello";
    }
    @GetMapping("/goodBay")
    public String goodBayPage(){
    return "first/goodBay";
    }


    @GetMapping("/calculator")
    public String calculator(@RequestParam(value = "a", required = false) int a,
                            @RequestParam(value = "b", required = false) int b,
                            @RequestParam(value = "action", required = false) String action,
                            Model model) {
        double result;
        switch (action) {
            case "multiplication": result = a * b; break;
            case "division": result = a /(double) b; break;
            case "plus": result = a + b; break;
            case "minus": result = a - b; break;
            default: result = 0; break;
        }
        model.addAttribute("result", result);

        return "first/calculator";
    }

}
