package fileupload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyfileDTO {
	// ��� ����
	private String idx;
	private String name;		// �ۼ��� �̸�
	private String title; 		// ����
	private String cate; 		// ī�װ�
	private String ofile;		// ���� ���ϸ�
	private String sfile;		// ����� ���ϸ�
	private String postdate;	// ��� ��¥
}
