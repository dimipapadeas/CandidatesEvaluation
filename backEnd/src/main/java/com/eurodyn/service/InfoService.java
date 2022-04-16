package com.eurodyn.service;

import com.eurodyn.model.Info;
import com.eurodyn.repositories.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

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
        Info info = infoRepository.findByComponent(component);
        response.put(component, info.getVersion());
        return response;
    }
}
