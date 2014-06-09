package course.labs.todomanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;

// ToDoItem class represents each item of the ListView

public class ToDoItem {

	// Properties are always provided by the Dalvik VM.
	// In this case: system line separator (e.g.: '\n')
	public static final String ITEM_SEP = System.getProperty("line.separator");

	// Enumerated for task's priorities
	public enum Priority {
		LOW, MED, HIGH
	};

	// Enumerated for task's statuses
	public enum Status {
		NOTDONE, DONE
	};

	// Check if it is convenient to place the strings in res/values for internationalization
	public final static String TITLE = "title";
	public final static String PRIORITY = "priority";
	public final static String STATUS = "status";
	public final static String DATE = "date";
	public final static String FILENAME = "filename";

	// Check if it is convenient to use Locale.ES
	public final static SimpleDateFormat FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss", Locale.US);

	// Initialize other class attributes
	private String mTitle = new String();
	private Priority mPriority = Priority.LOW;
	private Status mStatus = Status.NOTDONE;
	private Date mDate = new Date();

	// Constructor for creating a new ToDoItem
	ToDoItem(String title, Priority priority, Status status, Date date) {
		this.mTitle = title;
		this.mPriority = priority;
		this.mStatus = status;
		this.mDate = date;
	}

	// Create a new ToDoItem from data packaged in an Intent
	// We will use this constructor in ToDoManagerActivity, in the onActivityResult() method 
	ToDoItem(Intent intent) {

		// Obtain the ToDoItem attributes from the intent. 
		mTitle = intent.getStringExtra(ToDoItem.TITLE); // Get the title
		mPriority = Priority.valueOf(intent.getStringExtra(ToDoItem.PRIORITY)); // Get priority
		mStatus = Status.valueOf(intent.getStringExtra(ToDoItem.STATUS)); // Get the status

		// Get the date (deadline) of the ToDoItem
		try {
			mDate = ToDoItem.FORMAT.parse(intent.getStringExtra(ToDoItem.DATE));
		} catch (ParseException e) {
			mDate = new Date();
		}
	}

	// Setters & Getters
	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public Priority getPriority() {
		return mPriority;
	}

	public void setPriority(Priority priority) {
		mPriority = priority;
	}

	public Status getStatus() {
		return mStatus;
	}

	public void setStatus(Status status) {
		mStatus = status;
	}

	public Date getDate() {
		return mDate;
	}

	public void setDate(Date date) {
		mDate = date;
	}

	// Take a set of String data values and 
	// package them for transport in an Intent

	public static void packageIntent(Intent intent, String title,
			Priority priority, Status status, String date) {

		intent.putExtra(ToDoItem.TITLE, title);
		intent.putExtra(ToDoItem.PRIORITY, priority.toString());
		intent.putExtra(ToDoItem.STATUS, status.toString());
		intent.putExtra(ToDoItem.DATE, date);
	
	}

	public String toString() {
		return mTitle + ITEM_SEP + mPriority + ITEM_SEP + mStatus + ITEM_SEP
				+ FORMAT.format(mDate);
	}

	public String toLog() {
		return "Title:" + mTitle + ITEM_SEP + "Priority:" + mPriority
				+ ITEM_SEP + "Status:" + mStatus + ITEM_SEP + "Date:"
				+ FORMAT.format(mDate);
	}

}
