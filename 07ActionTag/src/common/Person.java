package common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//���� : �ڹٺ� Ŭ����
//���� : DTO Ŭ����

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
	private String name;
	private int age;
}
