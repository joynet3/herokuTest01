package com.ydata.herokuApp.web.potal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydata.herokuApp.web.potal.domain.MonthPriceDto;
import com.ydata.herokuApp.web.potal.mapper.MonthPriceMapper;
import com.ydata.herokuApp.web.potal.service.MonthPriceService;


@Service("monthPriceService")
public class MonthPriceServiceImpl implements MonthPriceService {
	
	@Autowired MonthPriceMapper monthPriceMapper;

	@Override
	public List<MonthPriceDto> getAll() {
		return monthPriceMapper.getAll();
	}
		

}