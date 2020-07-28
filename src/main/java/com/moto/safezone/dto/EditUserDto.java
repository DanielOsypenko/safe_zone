package com.moto.safezone.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class EditUserDto {
	String firstName;
	String lastName;
	
}
