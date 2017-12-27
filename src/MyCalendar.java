import java.io.BufferedReader;
import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.*;

/**
 * Class for MyCalendar. Uses TreeSets and ArrayLists to read, parse, add,
 * remove, and list Event objects.
 * 
 * @author Aayush Dixit
 *
 */
public class MyCalendar {
	/**
	 * Enum for months
	 *
	 */
	enum MONTHS {
		Jan, Feb, March, Apr, May, June, July, Aug, Sep, Oct, Nov, Dec;
	}

	/**
	 * Enum for days
	 *
	 */
	enum DAYS {
		Sun, Mon, Tue, Wed, Thur, Fri, Sat;
	}

	private TreeSet<Event> e; // instance variable for TreeSet of events
	private Event l; // instance variable for event

	/**
	 * Constructor for MyCalendar. Initializes the TreeSet.
	 */
	public MyCalendar() {
		this.e = new TreeSet<>();
	}

	/**
	 * Reads the file "events.txt". Each line represents an Event. Adds each line to
	 * an ArrayList of strings. Iteratively loops through each line of the txt file
	 * and parses each line to an Event object.
	 * 
	 * @throws IOException
	 */
	public void load() throws IOException {
		BufferedReader x = new BufferedReader(new FileReader("src/events.txt"));
		ArrayList<String> j = new ArrayList<>();
		String line1 = null;
		while ((line1 = x.readLine()) != null) {
			j.add(line1);
		}
		x.close();
		String[] r;
		for (int i = 0; i < j.size(); i++) {
			r = j.get(i).split(" ");
			String[] date = r[0].split("/");
			int[] dates = new int[date.length];
			for (int m = 0; m < date.length; m++) {
				try {
					dates[m] = Integer.parseInt(date[m]);
				} catch (NumberFormatException nfe) {
				}
			}
			String delimiterOne = "-";
			String delimiterTwo = ":";
			r[1] = r[1].replaceAll(delimiterOne, delimiterTwo);
			String[] time = r[1].split(delimiterTwo);
			int[] times = new int[time.length];
			for (int n = 0; n < times.length; n++) {
				try {
					times[n] = Integer.parseInt(time[n]);
				} catch (NumberFormatException nfe) {
				}
			}
			String name = "";
			for (int y = 2; y < r.length; y++) {
				name += r[y] + " ";
			}
			if (time.length < 4) {
				l = new Event(name, dates[2], dates[0], dates[1], times[0], times[1]);
			} else {
				l = new Event(name, dates[2], dates[0], dates[1], times[0], times[1], times[2], times[3]);
			}
			e.add(l);
		}
	}

	/**
	 * Adds an event to the TreeSet of Events. Automatically updates the TreeSet
	 * based on compareTo method from Event class.
	 * 
	 * @param r
	 *            event that is being added
	 */
	public void add(Event r) {
		e.add(r);
	}

	/**
	 * Removes all events by clearing the TreeSet.
	 */
	public void removeAll() {
		e.clear();
	}

	/**
	 * Remove events on a selected day. Uses an iterator through iterate through the
	 * TreeSet of events and removes events on the selected day of the calendar in
	 * the parameter.
	 * 
	 * @param r
	 *            the day
	 */
	public void removeByDay(Calendar r) {
		Iterator<Event> iter = e.iterator();
		int month = r.get(Calendar.MONTH);
		if (r.get(Calendar.MONTH) == 0) {
			month = 11;
		}
		while (iter.hasNext()) {
			Event x = iter.next();
			int someMonth = x.getStartTime().get(Calendar.MONTH) - 1;
			int someYear = x.getStartTime().get(Calendar.YEAR);
			if (x.getStartTime().get(Calendar.MONTH) == 0) {
				someMonth = 11;
				someYear = someYear - 1;
			}
			if (someYear == r.get(Calendar.YEAR)) {
				if (someMonth == month) {
					if (x.getStartTime().get(Calendar.DAY_OF_MONTH) == r.get(Calendar.DAY_OF_MONTH)) {
						iter.remove();
					}
				}
			}
		}
	}

	/**
	 * Prints all the events of the day using an ArrayList.
	 * 
	 * @param r
	 *            the day
	 */
	public void printEventsOfDay(Calendar r) {
		List<Event> jj = new ArrayList<Event>(e);
		System.out.println(r.get(Calendar.YEAR));
		MONTHS[] arrayOfMonths = MONTHS.values();
		DAYS[] arrayOfDays = DAYS.values();
		System.out.print(arrayOfDays[r.get(Calendar.DAY_OF_WEEK) - 1]);
		System.out.print(" ");
		System.out.print(arrayOfMonths[r.get(Calendar.MONTH)]);
		System.out.print(" ");
		System.out.print(r.get(Calendar.DAY_OF_MONTH));
		System.out.print(" ");
		System.out.println();
		int month = r.get(Calendar.MONTH);
		if (r.get(Calendar.MONTH) == 0) {
			month = 11;
		}
		for (int i = 0; i < jj.size(); i++) {
			int jjMonth = jj.get(i).getStartTime().get(Calendar.MONTH) - 1;
			int jjYear = jj.get(i).getStartTime().get(Calendar.YEAR);
			if (jj.get(i).getStartTime().get(Calendar.MONTH) == 0) {
				jjMonth = 11;
				jjYear = jjYear - 1;
			}
			if (jjYear == r.get(Calendar.YEAR)) {
				if (jjMonth == month) {
					if (jj.get(i).getStartTime().get(Calendar.DAY_OF_MONTH) == r.get(Calendar.DAY_OF_MONTH)) {
						System.out.println(jj.get(i).toString());
					}
				}
			}
		}
	}

	/**
	 * Prints the calendar in month format and highlights the days which have events
	 * in the month.
	 * 
	 * @param r
	 *            GregorianCalendar object.
	 */
	public void printCalendar(GregorianCalendar r) {
		TreeMap<Integer, Event> map = new TreeMap<Integer, Event>();
		Iterator<Event> iter = e.iterator();
		while (iter.hasNext()) {
			Event x = iter.next();
			map.put(x.getStartTime().get(Calendar.DAY_OF_MONTH), x);
		}
		GregorianCalendar calendar = r;
		MONTHS[] arrayOfMonths = MONTHS.values();
		System.out.println(" " + arrayOfMonths[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.YEAR));
		System.out.println(" Su Mo Tu We Th Fr Sa");
		GregorianCalendar temp = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
		int year = 0;
		int month = 0;
		for (int i = 0; i < temp.get(Calendar.DAY_OF_WEEK) - 1; i++) {
			System.out.print("   ");
		}
		for (int i = 1; i < r.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) + 1; i++) {
			if (map.containsKey(i)) {
				if (map.get(i).getStartTime().get(Calendar.MONTH) == 0) {
					year = map.get(i).getStartTime().get(Calendar.YEAR) - 1;
					month = 11;
				} else {
					year = map.get(i).getStartTime().get(Calendar.YEAR);
					month = map.get(i).getStartTime().get(Calendar.MONTH) - 1;
				}
			}
			if (map.containsKey(i) && month == calendar.get(Calendar.MONTH) && year == calendar.get(Calendar.YEAR)) {
				System.out.printf("[" + i + "]");
			} else {
				System.out.printf("%3d", i);
			}
			if ((i + temp.get(Calendar.DAY_OF_WEEK) - 1) % 7 == 0) {
				System.out.println();
			}
		}
		map.clear();
		System.out.println();
	}

	/**
	 * Writes existing data in TreeSet to a new txt file.
	 * 
	 * @throws FileNotFoundException
	 */
	public void quit() throws FileNotFoundException {
		File file = new File("src/events.txt");
		PrintWriter pW = new PrintWriter(file);
		int year = 0;
		for (Event kk : e) {
			year = kk.getStartTime().get(Calendar.YEAR);
			if (kk.getStartTime().get(Calendar.MONTH) == 0) {
				year = kk.getStartTime().get(Calendar.YEAR) - 1;
			}
			pW.println(year + " " + kk.toString());
		}
		pW.close();
	}

	/**
	 * Lists all the events in the TreeSet.
	 */
	public void list() {
		int year = 0;
		int yearTwo = 0;
		for (Event kk : e) {
			if (kk.getStartTime().get(Calendar.MONTH) == 0) {
				yearTwo = kk.getStartTime().get(Calendar.YEAR) - 1;
			} else {
				yearTwo = kk.getStartTime().get(Calendar.YEAR);
			}
			if (yearTwo > year) {

				year = yearTwo;
				System.out.println(year);
			}
			System.out.println(kk.toString());
		}
		System.out.println();
	}
}
