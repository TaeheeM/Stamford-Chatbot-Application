import java.util.Arrays;

public class Admin {
	private User[] users;
	private int userCount;

	public Admin() {
		users = new User[10];
		userCount = 0;
		initializeUsers();
	}

	private void initializeUsers() {
        addUser("Taehee", "student", "2002250001", "", 
            "LAN101, Programming II, ENG101, IT240, ITE102" +
            "\nITE321, SOC101 , ART101, ITE104 , GEO102" +
            "\nMIS101, SOC101, STA101, ITE104, ITE231" +
            "\nITE475, ITE231, ITE343, MKT222, ENG101" +
            "\nEAP101, FND101, PSY202, SOC101, GEO102");
        addUser("Su", "student", "2306090004", "", 
            "LAN101, Programming II, PSY102, ITE240, ITE102" +
            "\nATH101, SOC101 , ART101, ITE104 , LIT300" +
            "\nECO101, STA102, STA101, ITE104, ITE231" +
            "\nITE477, ITE231, MAT102, MKT222, ENG101" +
            "\nITE451, ITE351, PSY202, SOC101, GEO102");
        addUser("Sai", "student", "2302280007", "", 
            "ITE355, Programming II, MKT345, IT240, ITE102" +
            "\nITE220, SOC101 , ART101, ITE104 , LIT300" +
            "\nITE365, STA102, STA101, LAN101, ITE231" +
            "\nITE240, MUS101, MAT102, MKT222, ENG101" +
            "\nSOC229, ITE351, POL104, SOC101, GEO102");

		addUser("AJ.Nay", "professor", "Nay", "",
			"ITE355, Programming II, , IT240, ITE102" + 
			"\nITE220, ITE254 , ITE728, ITE104 , ITE321"+
			"\nITE365, ITE470, , , ITE231" + 
			"\nITE240, , ITE098, , ITE334"+
			"\nITE324, , ITE332, ITE233, ");

		addUser("AJ.Lanka", "professor", "Lanka", "",
			"ITE231, Programming II, ITE362, , ITE102" + 
			"\nITE220,  ,, ITE104 , ITE368"+
			"\nITE365, , ITE104, , ITE231" + 
			"\n, ITE441, ITE420, ITE340, ITE343"+
			"\nITE224, ITE351, , ITE120, ");

		addUser("AJ.Wendy", "professor", "Wendy", "",
		"ITE231, Programming II, ITE362, , ITE102" + 
		"\nITE213,  ,, ITE345 , ITE368"	+
		"\nITE365, , ITE104, , ITE342" + 
		"\n, ITE441, , ITE340, ITE343"+ 
		"\nITE224, ITE351, , ITE120, ");

		addUser("AJ.Firouz", "professor", "Firouz", "",
		"ITE231, Programming II, ITE362, , ITE102" + 
		"\nITE213,  ,, ITE345 , ITE368"+
		"\nITE365, , ITE104, , ITE342" + 
		"\n, ITE441, , ITE340, ITE343"+ 
		"\nITE224, ITE351, , ITE120, ");
		
        addUser("guest1", "guest", "", "", "");
    }

	private void addUser(String name, String role, String id, String password, String schedule) {
		if (userCount >= users.length) {
			users = Arrays.copyOf(users, users.length * 2); // 배열 크기 두 배로 확장
		}
		users[userCount++] = new User(name, role, id, password, schedule);
	}

	public User getById(String id) {
		for (int i = 0; i < userCount; i++) {
			if (users[i].getId().equalsIgnoreCase(id)) {
				return users[i];
			}
		}
		return null;
	}
	
	public User getByPassword(String password) {
		for (int i = 0; i < userCount; i++) {
			if (users[i].getPassword().equalsIgnoreCase(password)) {
				return users[i];
			}
		}
		return null;
	}

	public User getByIdAndPassword(String id, String password) {
		for (int i = 0; i < userCount; i++) {
			if (users[i].getId().equalsIgnoreCase(id) && users[i].getPassword().equals(password)) {
				return users[i];
			}
		}
		return null;
	}

	public void updatePassword(String id, String password) {
		for (int i = 0; i < userCount; i++) {
			if (users[i].getId().equalsIgnoreCase(id)) {
				users[i].setPassword(password);
				return;
			}
		}
	}
}
