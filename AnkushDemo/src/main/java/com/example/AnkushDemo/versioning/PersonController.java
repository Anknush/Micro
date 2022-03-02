package com.example.AnkushDemo.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
	@GetMapping("/v1/person")
	public PersionV1 personv1() {
		return new PersionV1("Ankush Kaundal");
	}
	@GetMapping("/v2/person")
	public PersionV2 personv2() {
		return new PersionV2(new Name("Aditya","Kaundal"));
	}
	@GetMapping(value="/person/param",params="version=1")
	public PersionV1 paramv1() {
		return new PersionV1("Ankush Kaundal");
	}
	@GetMapping(value="/person/param",params="version=2")
	public PersionV2 paramv2() {
		return new PersionV2(new Name("Aditya","Kaundal"));
	}
	@GetMapping(value="/person/header",headers="X-API-VERSION=1")
	public PersionV1 headerv1() {
		return new PersionV1("Ankush Kaundal");
	}
	@GetMapping(value="/person/header",headers="X-API-VERSION=2")
	public PersionV2 headerv2() {
		return new PersionV2(new Name("Aditya","Kaundal"));
	}
}
