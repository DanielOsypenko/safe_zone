package com.moto.safezone.model;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(of= {"login"})
@Document (collection = "users")
public class User {

	@Id
	String login;
	String name;
	String secondName;
	Integer floor;
	Integer place;
	@Singular
	Set<LocalDate> reservations;
	
	
	public boolean addReservations(Set <LocalDate> reserveDates) {
		return reservations.addAll(reserveDates);
	}
	
	public boolean removeReservations(Set <LocalDate> reserveDates) {
		return reservations.removeAll(reserveDates);
	}
	
}
