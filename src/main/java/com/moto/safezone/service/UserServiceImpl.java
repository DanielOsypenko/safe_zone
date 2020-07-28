package com.moto.safezone.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moto.safezone.dao.UserRepository;
import com.moto.safezone.dto.EditUserDto;
import com.moto.safezone.dto.FloorToPlaceDto;
import com.moto.safezone.dto.NewUserDto;
import com.moto.safezone.dto.UserDto;
import com.moto.safezone.exceptions.UserNotFoundException;
import com.moto.safezone.model.User;

@Service
public class UserServiceImpl implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository repository;

	@Override
	public UserDto registerUser(Integer floor, Integer place) {
		NewUserDto newUserDto = principalToNewUserDto();
		logger.info("registration request for place: " + place + "received from user: " + newUserDto);
		User user = repository.findById(newUserDto.getEmail()).orElseGet(() -> {
			User newUser = User.builder().login(newUserDto.getEmail()).name(newUserDto.getFirstName())
					.secondName(newUserDto.getLastName()).floor(floor).place(place)
					.reservations(new HashSet<LocalDate>()).build();
			logger.info("New user registered: " + newUser.toString());
			return repository.save(newUser);
		});
		return convertToUserDto(user);
	}

	@Override
	public UserDto editUser(EditUserDto editUserDto) {
		String id = principalToNewUserDto().getEmail();
		User user = repository.findById(id).orElseThrow(()->new UserNotFoundException(id));
		if (editUserDto.getFirstName() != null) {
			user.setName(editUserDto.getFirstName());
		}
		if (editUserDto.getLastName() != null) {
			user.setSecondName(editUserDto.getLastName());
		}
		return convertToUserDto(repository.save(user));
	}
	
	
	@Override
	public UserDto editUserPlace(FloorToPlaceDto floorToPlaceDto) {
		String id = principalToNewUserDto().getEmail();
		User user = repository.findById(id).orElseThrow(()->new UserNotFoundException(id));
		if (floorToPlaceDto.getFloor() != null) {
			user.setFloor(floorToPlaceDto.getFloor());
		}
		if (floorToPlaceDto.getPlace() != null) {
			user.setPlace(floorToPlaceDto.getPlace());
		}
		return convertToUserDto(repository.save(user));
	}

	@Override
	public UserDto findUser(String id) {
		User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		return convertToUserDto(user);
	}
	
	public Iterable<UserDto> findUsersByReservations(String dateReserve) {
		LocalDate dateReserveLDate = LocalDate.parse(dateReserve);
		return repository.findByReservations(dateReserveLDate).map(user -> convertToUserDto(user))
				.collect(Collectors.toList());
	}

	@Override
	public UserDto removeUser() {
		String id = principalToNewUserDto().getEmail();
		User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		repository.deleteById(id);
		return convertToUserDto(user);
	}

	@Override
	public boolean reserve(List<String> reserveDates) {
		String id = principalToNewUserDto().getEmail();
		User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		
		Set<LocalDate> reserveDatesLocalDates = reserveDates.stream().map(dateReserve -> LocalDate.parse(dateReserve))
				.collect(Collectors.toSet());
	
		boolean isReserved = user.addReservations(reserveDatesLocalDates);
		repository.save(user);
		return isReserved;
	}

	public boolean removeReservations(List<String> reserveDates) {
		String id = principalToNewUserDto().getEmail();
		User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		Set<LocalDate> reserveDatesLocalDates = reserveDates.stream().map(dateReserve -> LocalDate.parse(dateReserve))
				.collect(Collectors.toSet());
		boolean isRemoved = user.removeReservations(reserveDatesLocalDates);
		repository.save(user);
		return isRemoved;
	}

	@Override
	public Iterable<FloorToPlaceDto> findAllReservationsByDate(String dateReserve) {
		LocalDate dateReserveLDate = LocalDate.parse(dateReserve);
		return repository.findByReservations(dateReserveLDate)
				.map(userX -> FloorToPlaceDto.builder().floor(userX.getFloor()).place(userX.getPlace()).build())
				.collect(Collectors.toList());
	}

	public UserDto convertToUserDto(User user) {
		return UserDto.builder().login(user.getLogin()).floor(user.getFloor()).place(user.getPlace())
				.reservation(user.getReservations()).build();
	}

	public NewUserDto principalToNewUserDto() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode actualObj = mapper.readTree(mapper.writeValueAsString(authentication.getPrincipal()));
			JsonNode attributesJSON = actualObj.get("authorities").get(0).get("attributes");
			String givenNameString = attributesJSON.get("given_name").toString().replaceAll("\"", "");
			String familyNameString = attributesJSON.get("family_name").toString().replaceAll("\"", "");
			String emailString = attributesJSON.get("email").toString().replaceAll("\"", "");

			return NewUserDto.builder().email(emailString).firstName(givenNameString).lastName(familyNameString)
					.build();

		} catch (JsonProcessingException e) {
			System.out.println("Can't parse principal");
		}
		return null;
	}

	

}
