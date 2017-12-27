import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Tester class consisting of a main method which handles user inputs.
 * 
 * @author Aayush Dixit
 *
 */
public class MyCalendarTester {

	/**
	 * Use cases for user inputs. If a user presses "L," data from txt file loads.
	 * If user enters "C," user can create event. If user enters "V," user has the
	 * option of viewing by month or by day. If user enters "G," user can enter a
	 * date and access all events on that date. If users enters "E," user can access
	 * the list of all events. If user enters "D," user has an option of deleting
	 * events by day or all the events listed.
	 * 
	 * @param args
	 *            arguments
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
		GregorianCalendar r = new GregorianCalendar();
		MyCalendar cal = new MyCalendar();
		cal.printCalendar(r);
		System.out.println("Select one of the following options: ");
		System.out.println("[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()) {
			String input = sc.nextLine();
			if (input.equals("L")) {
				cal.load();
				System.out.println("Select one of the following options: ");
				System.out.println("[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
			} else if (input.equals("V")) {
				System.out.println("[D]ay view or [M]view ?");
				String inputTwo = sc.nextLine();
				if (inputTwo.equals("M")) {
					cal.printCalendar(r);
					System.out.println("[P]revious or [N]ext or [M]ain menu ?");
					while (sc.hasNextLine()) {
						String inputThree = sc.nextLine();
						if (inputThree.equals("P")) {
							r.add(Calendar.MONTH, -1);
							cal.printCalendar(r);
							System.out.println("[P]revious or [N]ext or [M]ain menu ?");
						} else if (inputThree.equals("N")) {
							r.add(Calendar.MONTH, 1);
							cal.printCalendar(r);
							System.out.println("[P]revious or [N]ext or [M]ain menu ?");
						} else if (inputThree.equals("M")) {
							GregorianCalendar t = new GregorianCalendar();
							r.set(t.get(Calendar.YEAR), t.get(Calendar.MONTH), t.get(Calendar.DAY_OF_MONTH));
							System.out.println("Select one of the following options: ");
							System.out.println("[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
							break;
						}
					}
				} else if (inputTwo.equals("D")) {
					cal.printEventsOfDay(r);
					System.out.println("[P]revious or [N]ext or [M]ain menu ?");
					while (sc.hasNextLine()) {
						String inputFour = sc.nextLine();
						if (inputFour.equals("P")) {
							r.add(Calendar.DAY_OF_MONTH, -1);
							cal.printEventsOfDay(r);
							System.out.println("[P]revious or [N]ext or [M]ain menu ?");
						} else if (inputFour.equals("N")) {
							r.add(Calendar.DAY_OF_MONTH, 1);
							cal.printEventsOfDay(r);
							System.out.println("[P]revious or [N]ext or [M]ain menu ?");
						} else if (inputFour.equals("M")) {
							GregorianCalendar t = new GregorianCalendar();
							r.set(t.get(Calendar.YEAR), t.get(Calendar.MONTH), t.get(Calendar.DAY_OF_MONTH));
							System.out.println("Select one of the following options: ");
							System.out.println("[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
							break;
						}
					}
				}
			} else if (input.equals("C")) {
				System.out.println("Title: ");
				String title = sc.nextLine();
				System.out.println("Date ((M)M/(D)D/YYYY): ");
				String date = sc.nextLine();
				String[] dateOne = date.split("/");
				int[] dates = new int[dateOne.length];
				for (int m = 0; m < dateOne.length; m++) {
					dates[m] = Integer.parseInt(dateOne[m]);
				}
				int month = dates[0];
				int day = dates[1];
				int year = dates[2];
				System.out.println("Start Time (H(H):MM): ");
				String startTime = sc.nextLine();
				String[] startTimeSplit = startTime.split(":");
				int[] startTimes = new int[startTimeSplit.length];
				for (int n = 0; n < startTimeSplit.length; n++) {
					startTimes[n] = Integer.parseInt(startTimeSplit[n]);
				}
				int startHour = startTimes[0];
				int startMin = startTimes[1];
				System.out.println("End Time (press enter to skip) (H(H):MM): ");
				String endTime = sc.nextLine();
				Event l;
				if (!endTime.equals("")) {
					String[] endTimeSplit = endTime.split(":");
					int[] endTimes = new int[endTimeSplit.length];
					for (int n = 0; n < endTimeSplit.length; n++) {
						endTimes[n] = Integer.parseInt(endTimeSplit[n]);
					}
					int endHour = endTimes[0];
					int endMin = endTimes[1];
					l = new Event(title, year, month, day, startHour, startMin, endHour, endMin);
				} else {
					l = new Event(title, year, month, day, startHour, startMin);
				}
				cal.add(l);
				System.out.println("Select one of the following options: ");
				System.out.println("[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
			}

			else if (input.equals("E")) {
				cal.list();
				System.out.println("Select one of the following options: ");
				System.out.println("[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
			} else if (input.equals("G")) {
				System.out.println("Enter a date((M)M/(D)D/YYYY): ");
				String dateInput = sc.nextLine();
				String[] dateString = dateInput.split("/");
				int[] dateData = new int[dateString.length];
				for (int m = 0; m < dateData.length; m++) {
					dateData[m] = Integer.parseInt(dateString[m]);
				}
				int monthData = dateData[0];
				int dayData = dateData[1];
				int yearData = dateData[2];
				r.set(yearData, monthData - 1, dayData);
				cal.printEventsOfDay(r);
				GregorianCalendar t = new GregorianCalendar();
				r.set(t.get(Calendar.YEAR), t.get(Calendar.MONTH), t.get(Calendar.DAY_OF_MONTH));
				System.out.println("Select one of the following options: ");
				System.out.println("[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
			} else if (input.equals("D")) {
				System.out.println("[S]elected or [A]ll ? ");
				String inputFive = sc.nextLine();
				if (inputFive.equals("S")) {
					System.out.println("Date to delete ((M)M/(D)D/YYYY): ");
					String dateInput = sc.nextLine();
					String[] dateString = dateInput.split("/");
					int[] dateData = new int[dateString.length];
					for (int m = 0; m < dateData.length; m++) {
						dateData[m] = Integer.parseInt(dateString[m]);
					}
					int monthData = dateData[0];
					int dayData = dateData[1];
					int yearData = dateData[2];
					r.set(yearData, monthData - 1, dayData);
					cal.removeByDay(r);
					GregorianCalendar t = new GregorianCalendar();
					r.set(t.get(Calendar.YEAR), t.get(Calendar.MONTH), t.get(Calendar.DAY_OF_MONTH));
					System.out.println("Select one of the following options: ");
					System.out.println("[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
				} else if (inputFive.equals("A")) {
					cal.removeAll();
					System.out.println("Select one of the following options: ");
					System.out.println("[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
				}
			} else if (input.equals("Q")) {
				cal.quit();
				break;
			}
		}
		sc.close();
	}

}
