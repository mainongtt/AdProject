package com.jd.index.creativeunit;

import com.jd.index.IndexAware;
import com.jd.index.adunit.AdUnitObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Slf4j
@Configuration
public class CreativeUnitIndex implements IndexAware<String,CreativeUnitObject> {
    // <adId-unitId, CreativeUnitObject>
    private static Map<String, CreativeUnitObject> objectMap;
    // <adId, unitId Set>
    private static Map<Long, Set<Long>> creativeUnitMap;
    // <unitId, adId set>
    private static Map<Long, Set<Long>> unitCreativeMap;

    static {
        objectMap = new ConcurrentHashMap<>();
        creativeUnitMap = new ConcurrentHashMap<>();
        unitCreativeMap = new ConcurrentHashMap<>();
    }

    @Override
    public CreativeUnitObject get(String key) {
        return objectMap.get(key);
    }

    @Override
    public void add(String key, CreativeUnitObject value) {
        log.info("before add: {}", objectMap);
        objectMap.put(key,value);
        Set<Long> unitIds = creativeUnitMap.get(value.getAdId());
        if(CollectionUtils.isEmpty(unitIds)){
            unitIds = new ConcurrentSkipListSet<>();
            creativeUnitMap.put(value.getAdId(),unitIds);
        }
        unitIds.add(value.getUnitId());

        Set<Long> AdIds = unitCreativeMap.get(value.getUnitId());
        if(CollectionUtils.isEmpty(AdIds)){
            AdIds = new ConcurrentSkipListSet<>();
            unitCreativeMap.put(value.getUnitId(),AdIds);
        }
        AdIds.add(value.getAdId());
        log.info("after add: {}", objectMap);
    }

    @Override
    public void update(String key, CreativeUnitObject value) {
        log.error("CreativeUnitIndex not support update");
    }

    @Override
    public void delete(String key, CreativeUnitObject value) {
        log.info("before delete: {}", objectMap);

        objectMap.remove(key);

        Set<Long> unitSet = creativeUnitMap.get(value.getAdId());
        if (!CollectionUtils.isEmpty(unitSet)) {
            unitSet.remove(value.getUnitId());
        }

        Set<Long> creativeSet = unitCreativeMap.get(value.getUnitId());
        if (!CollectionUtils.isEmpty(creativeSet)) {
            creativeSet.remove(value.getAdId());
        }

        log.info("after delete: {}", objectMap);
    }

    public List<Long> selectAds(List<AdUnitObject> unitObjects) {

        if (CollectionUtils.isEmpty(unitObjects)) {
            return Collections.emptyList();
        }

        List<Long> result = new ArrayList<>();

        for (AdUnitObject unitObject : unitObjects) {

            Set<Long> adIds = unitCreativeMap.get(unitObject.getUnitId());
            if (!CollectionUtils.isEmpty(adIds)) {
                result.addAll(adIds);
            }
        }

        return result;
    }
}
