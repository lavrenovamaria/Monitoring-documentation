package com.example.monitoring.service;

import com.example.monitoring.model.Source;
import com.example.monitoring.model.User;
import com.example.monitoring.parser.UniversalParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitoringService {

    @Autowired
    private SourceService sourceService;

    @Autowired
    private UserService userService;

    @Autowired
    private InterestSearch interestSearch;

    public void monitorSources() {
        List<Source> sources = sourceService.getAllSources();
        List<User> users = userService.getAllUsers();

        for (Source source : sources) {
            String content = UniversalParser.getContent(source.getUrl());
            for (User user : users) {
                List<String> matchingEvents = interestSearch.findMatchingEvents(content, user.getInterests());
                if (!matchingEvents.isEmpty()) {
                    // Send notification to user with matching events
                }
            }
        }
    }
}
