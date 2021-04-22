package com.urlShortner.Application.Requests;

import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import com.urlShortner.Application.Requests.Request;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends CassandraRepository<Request, UUID> {
    public Iterable<Request> findAllByUrlID(Integer UrlID);
}