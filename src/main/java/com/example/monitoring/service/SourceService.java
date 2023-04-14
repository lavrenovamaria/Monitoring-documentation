// SourceService.java
package com.example.monitoring.service;

import com.example.monitoring.model.Source;
import com.example.monitoring.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceService {

    @Autowired
    private SourceRepository sourceRepository;

    public List<Source> getAllSources() {
        return sourceRepository.findAll();
    }

    public Source addSource(Source source) {
        return sourceRepository.save(source);
    }

    public void removeSource(Long sourceId) {
        sourceRepository.deleteById(sourceId);
    }
}
