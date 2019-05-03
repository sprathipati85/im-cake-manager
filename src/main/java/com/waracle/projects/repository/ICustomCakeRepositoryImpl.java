package com.waracle.projects.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.projects.domain.Cake;
import com.waracle.projects.rest.CakeRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Repository
public class ICustomCakeRepositoryImpl implements ICustomCakeRepository {

    private static final String CAKES_JSON = "https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json";
    private static final Logger logger = LoggerFactory.getLogger(CakeRestController.class);

    @Override
    @Transactional(readOnly = true)
    public Set<Cake> getCakes() {
        StringBuffer buffer = new StringBuffer();
        Set<Cake> cakes = new HashSet<>();
        try (InputStream inputStream = new URL(CAKES_JSON).openStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            getCakesAsJsonInput(buffer, reader);
            ObjectMapper mapper = new ObjectMapper();
            cakes = mapper.readValue(buffer.toString(), new TypeReference<Set<Cake>>(){});
        } catch (MalformedURLException e) {
            logger.error("Failed with exception", e.getMessage());
        } catch (IOException e) {
            logger.error("Failed with exception", e.getMessage()
            );
        }
        return cakes;
    }


    private void getCakesAsJsonInput(StringBuffer buffer, BufferedReader reader) throws IOException {
        String line = reader.readLine();
        while (line != null) {
            buffer.append(line);
            line = reader.readLine();
        }
    }
}
