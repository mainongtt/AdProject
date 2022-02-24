package com.jd.search;


import com.jd.search.vo.SearchRequest;
import com.jd.search.vo.SearchResponse;

public interface ISearch {

    SearchResponse fetchAds(SearchRequest request);
}
