package com.urlShortner.Application.Users;

import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import com.urlShortner.Application.Users.User;
import org.springframework.stereotype.Repository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Repository
//@RepositoryRestResource(path = "user")
public interface UserRepository extends CassandraRepository<User, UUID> {
    @AllowFiltering
    public User findByEmail(String email);
}