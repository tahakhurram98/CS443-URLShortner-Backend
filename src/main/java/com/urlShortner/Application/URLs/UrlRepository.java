package com.urlShortner.Application.URLs;

import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import com.urlShortner.Application.URLs.Url;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends CassandraRepository<Url, UUID> {
    @AllowFiltering
    public Url findByShortURL(String short_url);
    public Iterable<Url> findAllByUserID(Integer user_id);
}