package com.moto.safezone.dao;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.moto.safezone.model.User;


public interface UserRepository extends MongoRepository <User,String> {

	public Stream <User> findByReservations(LocalDate reserveDate);

}
