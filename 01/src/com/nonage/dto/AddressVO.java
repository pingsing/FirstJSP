package com.nonage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressVO {
	private String zip_num;
	private String sido;
	private String gugun;
	private String dong;
	private String zip_code;
	private String bunji;
}
