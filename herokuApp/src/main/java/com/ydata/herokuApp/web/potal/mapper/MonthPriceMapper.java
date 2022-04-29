package com.ydata.herokuApp.web.potal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ydata.herokuApp.web.potal.domain.MonthPriceDto;

@Mapper
public interface MonthPriceMapper {
    public List<MonthPriceDto> getAll();
}
