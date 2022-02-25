package com.example.AnkushDemo.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
	@GetMapping("/filter")
	public FilterSome applyFiltering() {
		return new FilterSome("Value1","Value2","Value3");
	}
}
