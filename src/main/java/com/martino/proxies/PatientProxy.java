package com.martino.proxies;

import java.util.List;

import javax.annotation.ManagedBean;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.martino.models.ClientBean;

@FeignClient(name = "martino-client", url = "localhost:9002")
@ManagedBean
public interface PatientProxy {

	@GetMapping(value = "/patient/liste")
	List<ClientBean> list();

	@GetMapping(value = "/patient/info/{id}")
	ClientBean findOne(@PathVariable("id") Long id);
}
