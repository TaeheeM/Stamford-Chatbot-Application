import javax.swing.JOptionPane;
import java.util.Scanner;

public class Chatbot {
	private UniversityFAQEntry[] faqEntries;
	private int faqCount;
	private Admin admin;
	private String[][] tuitionFees;
	private String[] campusInfo;
	private String contactInfo;

	public Chatbot() {
		System.out.println("Welcome to Stamford Chatbot!\nWhat can I help you with? ^.^");
		System.out.println("==========================================");
		Utility.showMessageDialog("Welcome to Stamford Chatbot!\nWhat can I help you with? ^.^");

		// Initialize FAQs
		this.faqEntries = new UniversityFAQEntry[10];
		this.faqCount = 0;
		initializeFAQs();

		// Initialize tuition fees
		initializeTuitionFees();

		// Initialize campus information
		initializeCampusInfo();

		// Initialize contact information
		initializeContactInfo();

		// Initialize admin with pre-defined users and schedules
		this.admin = new Admin();

		handleUser();
	}

	private void handleUser() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Select an option: Register, Login, Guest, or Quit");
			String choice = scanner.nextLine();

			if ("Register".equalsIgnoreCase(choice)) {
				handleRegistration(scanner);
			} else if ("Login".equalsIgnoreCase(choice)) {
				handleLogin(scanner);
			} else if ("Guest".equalsIgnoreCase(choice)) {
				handleGuestUser();
			} else if ("Quit".equalsIgnoreCase(choice)) {
				reply("Goodbye! Have a nice day! :)");
				System.exit(0);
			} else {
				reply("Invalid option. Please try again.");
			}
		}
	}

	private void handleRegistration(Scanner scanner) {
		System.out.println("Enter your ID:");
		String id = scanner.nextLine();
		User user = admin.getById(id);

		if (user == null) {
			reply("Invalid ID. Please try again.");
			handleRegistration(scanner);
			return;
		}

		System.out.println("Set your password:");
		String password = scanner.nextLine();
		admin.updatePassword(id, password);
		reply("Registration successful! You can now log in.");

		handleLogin(scanner);
	}

	private void handleLogin(Scanner scanner) {
        System.out.println("Enter your ID:");
        String id = scanner.nextLine();
        System.out.println("Enter your password:");
        
        String password = scanner.nextLine();
        User user = admin.getByIdAndPassword(id, password);

        if (user != admin.getById(id)) {
            reply("Invalid Password. Please try again.");
            handleLogin(scanner);
            return;
        }
        else if (user != admin.getByPassword(password)) {
         System.out.println("Invalid ID. Please try again.");
         handleLogin(scanner);
         return;
        }
        else if (user == null) {
         System.out.println("Invalid ID and Password. Please try again");
         handleLogin(scanner);
         return;
        }
        

        reply("Login successful!");
        if ("student".equalsIgnoreCase(user.getRole())) {
            handleStudentUser(user);
        } else if ("professor".equalsIgnoreCase(user.getRole())) {
            handleProfessorUser(user);
        } else {
            reply("Invalid role. Please contact the admin.");
        }

        handleBackOption(scanner);
    }

	

	private void handleBackOption(Scanner scanner) {
		System.out.println("Do you want to go back to the main menu? (yes/no)");
		String choice = scanner.nextLine();
		if ("yes".equalsIgnoreCase(choice)) {
			handleUser();
		} else {
			reply("Goodbye! Have a nice day!");
			System.exit(0);
		}
	}

	public void reply(String message) {
		System.out.println("Chatbot--> " + message);
	}

	private void initializeFAQs() {
		addFAQEntry("admissions",
				"Select one of the following options: Admissions Criteria, Application Methods, Application Process for Thai students, Transfer Requirement, Documents Required to Process a Transfer.");
		addFAQEntry("tuition fees", "Select a major to see the tuition fees.");
		addFAQEntry("campuses", "Select a campus to see the details.");
		addFAQEntry("contact information", "View contact information.");
	}

	private void addFAQEntry(String keyword, String response) {
		if (faqCount >= faqEntries.length) {
			UniversityFAQEntry[] newFaqEntries = new UniversityFAQEntry[faqEntries.length * 2];
			System.arraycopy(faqEntries, 0, newFaqEntries, 0, faqEntries.length);
			faqEntries = newFaqEntries;
		}
		faqEntries[faqCount++] = new UniversityFAQEntry(keyword, response);
	}

	private void initializeTuitionFees() {
		tuitionFees = new String[][] { { "International Hotel Management", "742,400 - 757,400" },
				{ "Airline Business Management", "693,800 - 708,800" },
				{ "International Business Management", "693,800 - 708,800" },
				{ "Entrepreneurship", "693,800 - 708,800" }, { "Marketing", "693,800 - 708,800" },
				{ "Finance and Banking", "693,800 - 708,800" },
				{ "Logistics and Supply Chain Management", "693,800 - 708,800" },
				{ "International Relations", "693,800 - 708,800" }, { "Information Technology", "693,800 - 708,800" } };
	}

	private void initializeCampusInfo() {
		campusInfo = new String[] {
				"RAMA 9 Campus: Stamford’s Rama 9 campus is conveniently located on Bangkok’s Rama IX road, near Hua Mark Airport Link station,"
						+ " \nwhich provides an easy link between the campus and Bangkok’s city center. The building is student-centered and boasts lots of new facilities specific for a variety of programs."
						+ " \nFor the International Hotel Management Program for instance, there is a teaching kitchen and restaurant, a mock hotel front desk, and a mock hotel room."
						+ " \nState-of-the-art studio facilities are provided for Communication Arts students. The on-campus facilities allow students to gain the knowledge and skills that they will need for their future career."
						+ " \nThe Rama 9 Campus offers International and Bilingual Bachelor’s degree program."
						+ " \nContact: +662 769 4000, +662 769 4096, 16, Motorway Road – Km2, Prawet, Bangkok 10250 Thailand.",
				"Asoke Campus Learning Center: The Asoke Campus Learning Center is located at the heart of Bangkok’s business center at the Exchange Tower, which is conveniently connected to BTS and MRT stations."
						+ " \nStamford offers an unconventional urban learning space, encouraging you to think in new ways in an innovative and interactive learning environment."
						+ " \nThe Asoke Campus Learning Center offers Thai and International MBA programs, with a variety of study modes and class times to adapt to modern lifestyles."
						+ " \nContact: +662 769 4000, +662 769 4099, Exchange Tower, G & LG Floor, 388 Sukhumvit, Klongtoey, Bangkok 10110",
				"Hua Hin Campus: Stamford’s Hua Hin campus is located a few hours from Bangkok in the Cha Am-Hua Hin area, one of Thailand’s many world-class tourist destinations."
						+ " \nThe campus is set on a picturesque landscape overlooking the Palm Hills Golf Resort and is equipped with uniquely designed buildings for academic and residential purposes,"
						+ " \nwhich help create a holistic learning experience for students. The Hua Hin campus offers a variety of undergraduate and postgraduate programs."
						+ " \nContact: +662 769 4000, +662 769 4091, 1458 Petchkasem Road. Cha-Am. Petchburi 76120 Thailand." };
	}

	private void initializeContactInfo() {
		contactInfo = "Contact number: +662 769 4000\n\n" + "Facebook: Stamford International University\n"
				+ "Link: https://www.facebook.com/stamfordthailand/\n\n"
				+ "Twitter: Stamford International University\n" + "Link: https://twitter.com/stamfordiu\n\n"
				+ "Instagram: stamfordthailand\n" + "Link: https://www.instagram.com/stamfordthailand/\n\n"
				+ "YouTube: Stamford International University\n"
				+ "Link: https://www.youtube.com/@stamfordiu/videos\n\n"
				+ "LinkedIn: Stamford International University\n"
				+ "Link: https://www.linkedin.com/company/stamford-international-university/?originalSubdomain=th";
	}

	private void handleStudentUser(User user) {
		reply("Would you like to view the course schedule?");
		int studentChoice = Utility.showOptionDialog("Would you like to view the course schedule?");

		if (studentChoice == JOptionPane.OK_OPTION) {
			displaySchedule(user);
		} else {
			System.out.println(user.getName() + "--> Cancelled");
		}
	}

	private void handleProfessorUser(User user) {
		reply("Would you like to view the course schedule?");
		int professorChoice = Utility.showOptionDialog("Would you like to view the course schedule?");

		if (professorChoice == JOptionPane.OK_OPTION) {
			displaySchedule(user);
		} else {
			System.out.println(user.getName() + "--> Cancelled");
		}
	}

	private void handleGuestUser() {
		String userInput = JOptionPane.showInputDialog(null,
				"Enter 'admissions', 'programs', 'tuition fees', 'campuses', or 'contact information' to get information:",
				"Guest User", JOptionPane.QUESTION_MESSAGE);

		if (userInput == null) {
			reply("Cancelled");
			return;
		}

		switch (userInput.toLowerCase()) {
		case "admissions":
			handleAdmissions();
			break;
		case "programs":
			handlePrograms();
			break;
		case "tuition fees":
			handleTuitionFees();
			break;
		case "campuses":
			handleCampuses();
			break;
		case "contact information":
			handleContactInformation();
			break;
		default:
			reply("Invalid option entered.");
			break;
		}

		handleGuestUser();
	}

	private void handleAdmissions() {
		String[] options = { "Admissions Criteria", "Application Methods", "Transfer Requirement",
				"Documents Required to Process a Transfer" };
		Object selectedOption = Utility.showInputDialog("Select an option to get information:", options, "");

		if (selectedOption == null) {
			reply("Cancelled");
			return;
		}

		switch (selectedOption.toString()) {
		case "Admissions Criteria":
			showAdmissionsCriteria();
			break;
		case "Application Methods":
			showApplicationMethods();
			break;
		case "Transfer Requirement":
			showTransferRequirement();
			break;
		case "Documents Required to Process a Transfer":
			showDocumentsRequiredToProcessTransfer();
			break;
		default:
			reply("Invalid option selected.");
			break;
		}
	}

	private void handlePrograms() {
		String[] faculties = { "Bachelor of Business Administration", "Bachelor of Science",
				"Bachelor of Communication Arts", "Bachelor of Arts" };
		Object selectedFaculty = Utility.showInputDialog("Select a faculty:", faculties, "");

		if (selectedFaculty == null) {
			reply("Cancelled");
			return;
		}

		String[] majors;
		switch (selectedFaculty.toString()) {
		case "Bachelor of Business Administration":
			majors = new String[] { "Airline Business Management", "International Business Management",
					"International Hotel Management", "Marketing", "Entrepreneurship", "Finance and Banking",
					"Logistics and Supply Chain Management", "International Relations" };
			break;
		case "Bachelor of Science":
			majors = new String[] { "Information Technology" };
			break;
		case "Bachelor of Communication Arts":
			majors = new String[] { "Creative Media Production and Entertainment",
					"Advertising and Digital Marketing Communication" };
			break;
		case "Bachelor of Arts":
			majors = new String[] { "Creative Media Design", "English for Applied Global Communication" };
			break;
		default:
			reply("Invalid faculty selected.");
			return;
		}

		showMajors(majors);
	}

	private void handleTuitionFees() {
		String[] majors = { "International Hotel Management", "Airline Business Management",
				"International Business Management", "Entrepreneurship", "Marketing", "Finance and Banking",
				"Logistics and Supply Chain Management", "International Relations", "Information Technology" };

		Object selectedMajor = Utility.showInputDialog("Select a major to see the tuition fees:", majors, "");
		if (selectedMajor == null) {
			reply("Cancelled");
			return;
		}

		String selectedMajorStr = (String) selectedMajor;
		String tuitionFee = null;

		for (String[] feeEntry : tuitionFees) {
			if (feeEntry[0].equalsIgnoreCase(selectedMajorStr)) {
				tuitionFee = feeEntry[1];
				break;
			}
		}

		if (tuitionFee != null) {
			JOptionPane.showMessageDialog(null, selectedMajor + " Tuition Fee: " + tuitionFee, "Tuition Fees",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			reply("No tuition fee information available for the selected major.");
		}
	}

	private void handleCampuses() {
		String[] campuses = { "RAMA 9 Campus", "Asoke Campus Learning Center", "Hua Hin Campus" };
		Object selectedCampus = Utility.showInputDialog("Select a campus to see the details:", campuses, "");

		if (selectedCampus == null) {
			reply("Cancelled");
			return;
		}

		int index = -1;
		for (int i = 0; i < campuses.length; i++) {
			if (campuses[i].equalsIgnoreCase(selectedCampus.toString())) {
				index = i;
				break;
			}
		}

		if (index != -1) {
			JOptionPane.showMessageDialog(null, campusInfo[index], "Campus Details", JOptionPane.INFORMATION_MESSAGE);
		} else {
			reply("No campus information available for the selected campus.");
		}
	}

	private void handleContactInformation() {
		JOptionPane.showMessageDialog(null, contactInfo, "Contact Information", JOptionPane.INFORMATION_MESSAGE);
	}

	private void displaySchedule(User user) {
		String schedule = user.getSchedule();

		String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };
		String[] times = { "08:30-10:30", "10:30-12:30", "12:30-14:30", "14:30-16:30", "16:30-18:30" };

		String[] rows = schedule.split("\n");
		String[][] timetable = new String[5][5];

		for (int i = 0; i < rows.length; i++) {
			String[] cols = rows[i].split(",");
			System.arraycopy(cols, 0, timetable[i], 0, cols.length);
		}

		// Print the timetable
		System.out.printf("%-12s", "");
		for (String day : days) {
			System.out.printf("| %-20s", day);
		}
		System.out.println();
		for (int i = 0; i < times.length; i++) {
			System.out.printf("%-12s", times[i]);
			for (int j = 0; j < days.length; j++) {
				if (timetable[i][j] != null) {
					System.out.printf("| %-20s", timetable[i][j]);
				} else {
					System.out.printf("| %-20s", "");
				}
			}
			System.out.println();
		}
	}

	private void showMajors(String[] majors) {
		StringBuilder message = new StringBuilder("Majors:\n");
		for (String major : majors) {
			message.append("- ").append(major).append("\n");
		}
		JOptionPane.showMessageDialog(null, message.toString(), "Majors", JOptionPane.INFORMATION_MESSAGE);
	}

	private void showAdmissionsCriteria() {
		String message = "Admissions Criteria:\n"
				+ "There are two different types of entry requirements for our international undergraduate programs: academic qualifications and English proficiency qualifications.\n\n"
				+ "Academic Qualifications:\n"
				+ "- Completion of high school / upper secondary school (or equivalent), which is generally obtained after 12 years of education.\n"
				+ "- IGCSE / GCSE / GCE O-levels Certificate with no less than C grade\n"
				+ "- IGCSE / GCSE / GCE A-levels Certificate with no less than 3 subjects\n"
				+ "- GED with minimum 145 points in all 4 subjects, along with the high school equivalency diploma.\n"
				+ "- IB certificate with minimum 5 subjects with a minimum score of 4.\n"
				+ "- IB Diploma with minimum 24 points\n" + "- Mathayom 6 with graduation certificate and diploma.\n"
				+ "- Contact the university for other qualifications.\n\n" + "English Proficiency:\n"
				+ "- IELTS 5.5 or TOEFL (IBT) 52 (or equivalent).\n"
				+ "- English placement test on campus if necessary.\n"
				+ "- Stamford English Program for those not ready for an international program.\n";
		JOptionPane.showMessageDialog(null, message, "Admissions Criteria", JOptionPane.INFORMATION_MESSAGE);
	}

	private void showApplicationMethods() {
		String message = "Application Methods:\n"
				+ "- Online application: Required documents can be attached to the form directly. Apply online here.\n"
				+ "- Apply at Stamford International University (Rama 9 Campus, Asoke Campus Learning Center, Hua Hin Campus).\n"
				+ "- Via postal mail: Send the application form and required documents to Stamford International University, 16 Motorway Road (km 2), Prawet, Bangkok 10250, Thailand.\n"
				+ "- For more information, please call +66 2 769 4000.\n";
		JOptionPane.showMessageDialog(null, message, "Application Methods", JOptionPane.INFORMATION_MESSAGE);
	}

	private void showTransferRequirement() {
		String message = "Transfer Requirement:\n"
				+ "Stamford International University accepts transfer students who enrolled previously in any accredited university, provided they meet Stamford admission standards.\n\n"
				+ "Credit Transfer Guidelines:\n"
				+ "- Credits must be transferred only once, at the time of admission.\n"
				+ "- Three quarters (75%) of the course content has to be equivalent to that specified in Stamford’s curriculum.\n"
				+ "- Only courses with an earned grade “C” or higher will be accepted as transferred credit.\n"
				+ "- The maximum number of transferable credits is 108 provided all requirements are met.\n"
				+ "- Each credit transferred must have had 15 classroom hours or equivalent, which is 45 hours for one course.\n"
				+ "- Submission of course curriculum along with official transcripts is required to process a transfer.\n";
		JOptionPane.showMessageDialog(null, message, "Transfer Requirement", JOptionPane.INFORMATION_MESSAGE);
	}

	private void showDocumentsRequiredToProcessTransfer() {
		String message = "Documents Required to Process a Transfer:\n" + "1. A filled undergraduate application form.\n"
				+ "2. High school academic records (please see Admission Requirements for specifications).\n"
				+ "3. Official university transcripts including all courses requested to be transferred.\n"
				+ "4. Course descriptions of courses requested to be transferred.\n"
				+ "5. Copy of passport or citizen ID + house registration (Thai nationals).\n"
				+ "6. IELTS/TOEFL (meriting but not required).\n"
				+ "7. Three recent passport-sized photographs (taken within last 3 months).\n"
				+ "8. Email for domestic students: admissions@stamford.edu\n"
				+ "9. Email for international students: international@stamford.edu\n";
		JOptionPane.showMessageDialog(null, message, "Documents Required to Process a Transfer",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
