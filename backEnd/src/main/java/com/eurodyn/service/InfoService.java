package com.eurodyn.service;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import com.eurodyn.model.Info;
import com.eurodyn.repositories.InfoRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class InfoService {

    private final InfoRepository infoRepository;

    /**
     * Returns the Version of the requested component
     *
     * @param component the component to get the version for
     * @return the Version
     */
    public Map<String, Object> getVersion(String component) {
        Map<String, Object> response = new HashMap<>();
        Info info = infoRepository.findByComponent(null);
        response.put(component, info.getVersion());
        return response;
    }
}
