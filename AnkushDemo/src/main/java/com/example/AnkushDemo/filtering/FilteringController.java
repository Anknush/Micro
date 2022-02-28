package com.example.AnkushDemo.filtering;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.util.*;
@RestController
public class FilteringController {
	@GetMapping("/filter")
	public MappingJacksonValue applyFiltering() {
		FilterSome filtersome=new FilterSome("Value1","Value2","Value3");
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("name1","name2");
		FilterProvider filters=new SimpleFilterProvider().addFilter("SomeFilter", filter);
		MappingJacksonValue mapping=new MappingJacksonValue(filtersome);
		mapping.setFilters(filters);
		return mapping;
	}
	@GetMapping("/filter-list")
	public MappingJacksonValue fiterList(){
	List<FilterSome> list=Arrays.asList(new FilterSome("Vaue1","Value2","Value3"),
				new FilterSome("Value12","Value22","Value23"));
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("name2","name3");
		FilterProvider filters=new SimpleFilterProvider().addFilter("SomeFilter", filter);
		MappingJacksonValue mapping=new MappingJacksonValue(list);
		mapping.setFilters(filters);
		return mapping;
	}
}
