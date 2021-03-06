package com.martino.proxies;

import java.util.List;

import javax.annotation.ManagedBean;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.martino.models.AnalyseBean;

@FeignClient(name = "martino-analyse", url = "localhost:9001")
@ManagedBean
public interface TestProxy {

	@GetMapping(value = "/test/liste")
	List<AnalyseBean> list();

	@GetMapping(value = "/test/info/{id}")
	AnalyseBean findOne(@PathVariable("id") Long long1);
}
