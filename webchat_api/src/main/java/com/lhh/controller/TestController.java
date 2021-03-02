package com.lhh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lihonghao
 * @date 2021/3/2 19:35
 */
@Controller
public class TestController {
	@RequestMapping("/test")
	public String test(){
		return"test";
	}
}
