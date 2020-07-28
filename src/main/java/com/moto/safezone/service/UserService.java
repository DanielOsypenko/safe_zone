package com.moto.safezone.service;

import com.moto.safezone.dto.EditUserDto;
import com.moto.safezone.dto.FloorToPlaceDto;
import com.moto.safezone.dto.NewUserDto;
import com.moto.safezone.dto.UserDto;
import java.util.List;

public interface UserService {

	UserDto registerUser(Integer floor, Integer place);
	
	UserDto findUser(String id);
	
	Iterable<UserDto> findUsersByReservations(String dateReserve);
	
	UserDto removeUser();
	
	UserDto editUser(EditUserDto editUserDto);
	
	UserDto editUserPlace(FloorToPlaceDto floorToPlaceDto);
	
	boolean reserve(List <String> reserveDates);
	
	boolean removeReservations(List <String> reserveDates);
	
	Iterable <FloorToPlaceDto> findAllReservationsByDate(String dateReserve);
	
	NewUserDto principalToNewUserDto();
	
}
