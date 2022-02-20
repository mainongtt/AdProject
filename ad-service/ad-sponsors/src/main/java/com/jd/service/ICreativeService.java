package com.jd.service;


import com.jd.exception.AdException;
import com.jd.vo.CreativeRequest;
import com.jd.vo.CreativeResponse;

public interface ICreativeService {

    CreativeResponse createCreative(CreativeRequest request) throws AdException;
}
