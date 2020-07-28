package com.moto.safezone.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class FloorToPlaceDto {
Integer floor;
Integer place;
}
