package javaTester;

public class topic_06_String {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String url = "http://the-internet.herokuapp.com/basic_auth";
		String username = "admin";
		String password = "admin";
		
		System.out.println(url);
		
		//Dùng hàm của string để tách chuỗi
		String[] arrayUrl = url.split("//");
		
		//Sau khi tách ra thì trả vể mảng
		// 1 - http: -> index = 0
		// 2 - the-internet.herokuapp.com/basic_auth -> index = 1
		
		// nối lại + giá trị mới vào
		
		url = arrayUrl[0] + "//" + username + ":" + password + "@" + arrayUrl[1];
		
		System.out.println(url);
	}

}
