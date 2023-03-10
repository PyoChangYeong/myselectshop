package com.example.myselectshop.controller;

import com.example.myselectshop.dto.LoginRequestDto;
import com.example.myselectshop.dto.SignupRequestDto;
import com.example.myselectshop.jwt.JwtUtil;
import com.example.myselectshop.service.KakaoService;
import com.example.myselectshop.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final KakaoService kakaoService;

    @GetMapping("/signup")
    public ModelAndView signupPage() {
        return new ModelAndView("signup");
    }

    //@RequestBody @Valid
//    @PostMapping("/signup")
//    public String signup(@RequestBody  SignupRequestDto signupRequestDto) {
//        userService.signup(signupRequestDto);
//        return "redirect:/api/user/login";
//    }

    @GetMapping("/login-page")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @PostMapping("/signup")
    public String signup(SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return "redirect:/api/user/login-page";
    }


//    @PostMapping("/login")
//    public String login(LoginRequestDto loginRequestDto) {
//        userService.login(loginRequestDto);
//        return "redirect:/api/shop";
//    }


    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return "success";
    }

    @GetMapping("/forbidden")
    public ModelAndView getForbidden() {
        return new ModelAndView("forbidden");
    }

    @PostMapping("/forbidden")
    public ModelAndView postForbidden() {
        return new ModelAndView("forbidden");
    }

    @GetMapping("/kakao/callback")
    public String kakaoLogin(@RequestParam String code, HttpServletResponse response)throws JsonProcessingException {

//        code : ????????? ????????? ?????? ?????? ??????
        String createToken = kakaoService.kakaoLogin(code, response);

//        Cookie ?????? ??? ?????? ??????????????? Set
        Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER, createToken.substring(7));
        cookie.setPath("/");
        response.addCookie(cookie);


        return "redirect:/api/shop";
    }





}

// ??????????????????,