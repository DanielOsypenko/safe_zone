package com.moto.safezone.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class ReservePlaceDto {
	String login;
	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDateTime dateReservation;
	Boolean reserve;
}
