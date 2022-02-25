package com.sagar.azurepr.repository;

import com.sagar.azurepr.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, String> {
    public Url findByKey(String key);
}
