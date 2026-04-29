package com.exampleJournalApplication.cache;

import com.exampleJournalApplication.entity.ConfigJournalAppEntity;
import com.exampleJournalApplication.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class AppCache {
    public enum keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String,String> appCache ;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        log.info("Found {} records in DB", all.size());
        for (ConfigJournalAppEntity configJournalAppEntity:all){
            appCache.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
        }

    }
}
