package com.bucketdev.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public MappingJacksonValue returnSomeBean() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		
		return dynamicFilter(someBean, "field2", "field3");
	}

	@GetMapping("/filtering-list")
	public MappingJacksonValue returnListOfSomeBeans() {
		List<SomeBean> list = Arrays.asList(
				new SomeBean("value1", "value2", "value3"),
				new SomeBean("value12", "value22", "value32")
		);
		
		return dynamicFilter(list, "field1", "field2");
	}
	
	private MappingJacksonValue dynamicFilter(Object bean, String... fields) {		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
		FilterProvider filterProvider = new SimpleFilterProvider()
				.addFilter("SomeBeanFilter", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(bean);
		mapping.setFilters(filterProvider);
		
		return mapping;
	}
	
}
