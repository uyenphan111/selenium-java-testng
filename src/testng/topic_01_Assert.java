package testng;

import org.testng.Assert;

public class topic_01_Assert {

	public static void main(String[] args) {
		//testng cung cấp 1 bộ thư viện Assert để kiểm tra, verify dữ liệu
		String fullname = "Automation testing";
		//Mong đợi 1 điều kiện trả về là đúng(true)
		Assert.assertTrue(fullname.contains("Automation"));
		
		//Mong đợi 1 điều kiện trả về là sai(false)
		Assert.assertFalse(fullname.contains("Manual"));
		
		//Mong đợi 2 điều kiện bằng nhau
		Assert.assertEquals(fullname, "Automation testing");

	}

}
