import java.util.Scanner;

public class Railway {
	Scanner r = new Scanner(System.in);

	void main_menu() throws Exception {
		System.out.println(
				"~~~~~~~~~~~~~~~~~~~~~~~~~~MAIN MENU~~~~~~~~~~~~~~~~~~~~~~~~~\n1. Admin Mode\n2. User Mode\n3. Exit");
		int ch = r.nextInt();
		switch (ch) {
			case 1:
				admin_log();
				break;
			case 2:
				u_user_login();
				break;
			default:
				System.exit(0);
				break;

		}

	}

	void admin_log() throws Exception {
		System.out.print("Enter password : ");
		String ps = r.next();
		if (ps.equals("toluene")) {
			admin_mode();
		} else {
			System.out.println("Wrong password contact to the creator!!!!!!");
			main_menu();
		}
	}

	void admin_mode() throws Exception {

		Admin ad = new Admin();
		System.out.println(
				"~~~~~~~~~~~~~~~~~~~~~~~~~~ADMINISTRATOR MENU~~~~~~~~~~~~~~~~~~~~~~~~~\n1. Create detail database of trains\n2. Add Details of trains\n3. Display all the database of trains\n4. Display Chart of a train\n5. Display all users\n6. Update train date\n7. Return to main menu \n8. Exit");
		int ch = r.nextInt();
		switch (ch) {
			case 1:
				ad.cr_train_info();
				break;
			case 2:
				ad.train_info();
				break;
			case 3:
				ad.dis_train_db();
				break;
			case 4:
				ad.disp_chart();
				break;
			case 5:
				ad.disp_user();
				break;
			case 6:
				ad.train_update_date();
				break;
			case 7:
				main_menu();
				break;
			default:
				System.exit(0);
				break;
		}

	}

	void u_user_login() throws Exception {
		Admin ad = new Admin();
		System.out.println(
				"~~~~~~~~~~~~~~~~~~~~~~~~~~WELCOME TO USER MENU~~~~~~~~~~~~~~~~~~~~~~~~~\n1. Login\n2. Sign Up\n3. Return to main menu\n4. Exit");
		int ch = r.nextInt();
		switch (ch) {
			case 1:
				user_mode();
				break;
			case 2:
				ad.user_signup();
				break;
			case 3:
				main_menu();
				break;
			default:
				System.exit(0);
				break;
		}
	}

	void user_mode() throws Exception {
		User us = new User();
		Admin ad = new Admin();
		if (ad.user_login() == 1) {
			System.out.println("1. Book a ticket\n2. Cancel a ticket\n3. Enquiry\n4. Return to main menu\n5. Exit");
			int ch = r.nextInt();
			switch (ch) {
				case 1:
					us.inputreserve();
					break;
				case 2:
					us.cancel1();
					break;
				case 3:
					us.enquiry1();
					break;
				case 4:
					main_menu();
					break;
				default:

					System.exit(0);
					break;
			}
		} else {
			System.out.println("Wrong username or password!!!!!!");
			u_user_login();

		}
	}

	public static void main(String args[]) throws Exception {
		System.out.println(
				"**************************************************************************************************************");
		System.out.println(
				"                                      RAILWAY RESERVATION SYSTEM                                              ");
		System.out.println(
				"**************************************************************************************************************");
		System.out.println();
		System.out.println();
		Railway rail = new Railway();
		rail.main_menu();
	}
}
