package org.syracus.rapid.calendar.taglib;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author snwiem
 * @jsp.tag name="month" body-content="scriptless"
 */
public class MonthCalendarTag extends AbstractCalendarTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static final transient Log log = LogFactory.getLog( MonthCalendarTag.class );
	
	private static final int NROWS = 6;
	private static final int NDAYS = 7;
	
	private int year = 0;
	private int month = 0;
	private int day = 0;
	private Locale locale = null;
	
	//private String styleTable;
	//private String styleMonth;
	//private String styleDay;
	//private String styleToday;
	//private String styleLink;
	
	//private boolean shortMonth = false;
	//private boolean shortDay = true;
	
	//private String nextMonth;
	//private String prevMonth;
	//private String nextYear;
	//private String prevYear;

	/*
	public String getStyleLink() {
		return styleLink;
	}


	public void setStyleLink(String styleLink) {
		this.styleLink = styleLink;
	}


	public String getNextYear() {
		return nextYear;
	}


	public void setNextYear(String nextYear) {
		this.nextYear = nextYear;
	}


	public String getPrevYear() {
		return prevYear;
	}


	public void setPrevYear(String prevYear) {
		this.prevYear = prevYear;
	}


	public String getNextMonth() {
		return nextMonth;
	}


	public void setNextMonth(String nextMonth) {
		this.nextMonth = nextMonth;
	}


	public String getPrevMonth() {
		return prevMonth;
	}


	public void setPrevMonth(String prevMonth) {
		this.prevMonth = prevMonth;
	}


	public boolean isShortDay() {
		return shortDay;
	}


	public void setShortDay(boolean shortDay) {
		this.shortDay = shortDay;
	}


	public boolean isShortMonth() {
		return shortMonth;
	}


	public void setShortMonth(boolean shortMonth) {
		this.shortMonth = shortMonth;
	}


	public String getStyleDay() {
		return styleDay;
	}


	public void setStyleDay(String styleDay) {
		this.styleDay = styleDay;
	}


	public String getStyleMonth() {
		return styleMonth;
	}


	public void setStyleMonth(String styleMonth) {
		this.styleMonth = styleMonth;
	}


	public String getStyleToday() {
		return styleToday;
	}


	public void setStyleToday(String styleToday) {
		this.styleToday = styleToday;
	}


	public String getStyleTable() {
		return styleTable;
	}


	public void setStyleTable(String styleTable) {
		this.styleTable = styleTable;
	}
	*/


	/**
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public int getDay() {
		return day;
	}


	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}


	/**
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public Locale getLocale() {
		return locale;
	}


	public void setLocale(Locale locale) {
		this.locale = locale;
	}


	@Override
	public void doTag() throws JspException {
		if ( log.isDebugEnabled() ) {
			log.debug( "[doEndTag] BEGIN" );
		}
		try {
			getJspBody().invoke( null );
		} catch( IOException e ) {
			throw new JspException( e );
		}
		
		Calendar cal = Calendar.getInstance( Locale.US );
		
		Date now = new Date();
		cal.setTime( now );
		if ( log.isDebugEnabled() ) {
			log.debug( "[doEndTag] current year = " + cal.get( Calendar.YEAR ) );
		}
		if ( 0 < getYear() ) {
			if ( log.isDebugEnabled() ) {
				log.debug( "[doEndTag] setting year to '" + getYear() + "'" );
			}
			cal.set( Calendar.YEAR, getYear() );
		}
		if ( log.isDebugEnabled() ) {
			log.debug( "[doEndTag] new year = " + cal.get( Calendar.YEAR ) );
		}
		if ( log.isDebugEnabled() ) {
			log.debug( "[doEndTag] current month = " + cal.get( Calendar.MONTH ) );
		}
		if ( 0 < getMonth() ) {
			if ( log.isDebugEnabled() ) {
				log.debug( "[doEndTag] setting month to '" + getMonth() + "'" );
			}
			cal.set( Calendar.MONTH, getMonth()-1 );
		}
		if ( log.isDebugEnabled() ) {
			log.debug( "[doEndTag] new month = " + cal.get( Calendar.MONTH ) );
		}
		if ( log.isDebugEnabled() ) {
			log.debug( "[doEndTag] current day = " + cal.get( Calendar.DAY_OF_MONTH ) );
		}
		if ( 0 >= getDay() ) {
			setDay( cal.get( Calendar.DAY_OF_MONTH ) );
		}
		if ( log.isDebugEnabled() ) {
			log.debug( "[doEndTag] new day = " + getDay() );
		}
		int[][] table = new int[ NROWS ][ NDAYS ];
		
		int w = 0;
		int month = cal.get( Calendar.MONTH );
		for ( int i = 1; i <= 31; ++i ) {
			cal.set( Calendar.DAY_OF_MONTH, i );
			if ( month != cal.get( Calendar.MONTH ) ) {
				if ( log.isDebugEnabled() ) {
					log.debug( "[doEndTag] month left." );
				}
				break;
			}
			int d = cal.get( Calendar.DAY_OF_WEEK ) % NDAYS;

			w = cal.get( Calendar.WEEK_OF_MONTH );
			if ( d == 0 ) {
				d = NDAYS;
			}
			if ( log.isDebugEnabled() ) {
				log.debug( "[doEndTag] table[" + (w-1) + "][" + (d-1) + "] = " + i );
			}
			table[w-1][d-1] = i;
		}
		
		try {
			printTable( cal, table );
		} catch( IOException e ) {
			throw new JspException( e );
		}
			
		if ( log.isDebugEnabled() ) {
			log.debug( "[doEndTag] END" );
		}
		//return( EVAL_PAGE );
	}
	
	protected void printTable( Calendar cal, int[][] table ) throws IOException {
		int first = cal.getFirstDayOfWeek();
		DateFormatSymbols sym = new DateFormatSymbols( Locale.US  );
		
		String[] weekdays = sym.getWeekdays();
		String[] months = sym.getMonths();
		
		if ( log.isDebugEnabled() ) {
			for ( int i = 0; i < months.length; ++i ) {
				log.debug( "[printTable] months[" + i + "] = '" + months[i] + "'" );
			}
		}
		
		JspWriter writer = getJspContext().getOut();
		if ( null != getProperty( PROPERTY_CALENDARCLASS ) ) {
			writer.print( "<table border=\"1\" class=\"" + getProperty( PROPERTY_CALENDARCLASS ) + "\">" );
		} else {
			writer.print( "<table border=\"1\">" );
		}
		writer.print( "<tr><td colspan=\"7\">" );
		writer.print( getMonthRow( cal, months) );
		writer.print( "</td></tr>" );
		writer.print( "<tr>" );
		if ( log.isDebugEnabled() ) {
			for ( int i = 0; i < weekdays.length; ++i ) {
				log.debug( "[printTable] weekdays[" + i + "] = '" + weekdays[i] + "'" );
			}
		}
		for ( int i = 0; i < weekdays.length-1; ++i ) {
			if ( null != getProperty( PROPERTY_DAYCLASS ) ) {
				writer.print( "<th class=\"" + getProperty( PROPERTY_DAYCLASS ) + "\">" );
			} else {
				writer.print( "<th>" );
			}
			//if ( isShortDay() ) {
			//	writer.print( weekdays[(first++)].substring(0, 3) );
			//} else {
				writer.print( weekdays[(first++)] );
			//}
			if ( first > weekdays.length-1 ) {
				first = 1;
			}
			writer.print( "</th>" );
		}
		writer.print( "</tr>" );
		/*
		if ( log.isDebugEnabled() ) {
			log.debug( "[printTable] table.length = '" + table.length + "'" );
		}
		*/
		int rows = (true == lastRowEmpty( table )) ? table.length-1 : table.length;
		for ( int i = 0; i < rows; ++i ) {
			writer.print( "<tr>" );
			for ( int j = 0; j < table[i].length; ++j ) {
				if ( 0 == table[i][j] ) {
					writer.print( "<td>&nbsp;</td>" );
				} else if ( table[i][j] == getDay() ) {
					//if ( null != getStyleToday() ) {
					//	writer.print( "<td class=\"" + getStyleToday() + "\">" );
					//} else {
						writer.print( "<td>" );
					//}
					writer.print( table[i][j] );
					writer.print( "</td>" );
				} else {
					writer.print( "<td>" );
					writer.print( table[i][j] );
					writer.print( "</td>" );
				}
			}
			writer.print( "</tr>" );
		}
		writer.print( "</table>" );
	}
	
	private boolean lastRowEmpty( int[][] table ) {
		return( rowEmpty( table.length-1, table ) );
	}
	
	private boolean rowEmpty( int row, int[][] table ) {
		for ( int i = 0; i < table[row].length; ++i ) {
			if ( 0 < table[row][i] ) {
				return( false );
			}
		}
		return( true );
	}
	
	private String getMonthRow( Calendar cal, String[] months ) {
		
		StringBuffer sb = new StringBuffer();
		
		//int span = 7;
		
		sb.append( "<table border=\"0\" width=\"100%\"><tr>" );
		/*
		if ( null != getPrevYear() ) {
			sb.append( "<th align=\"left\">" );
			if ( null != getStyleLink() ) {
				sb.append( "<a class=\"" + getStyleLink() + "\" href=\"" + getPrevYear() + "\">" );
			} else {
				sb.append( "<a href=\"" + getPrevYear() + "\">" );
			}
			sb.append( "&lt;&lt;" );
			sb.append( "</a>" );
			sb.append( "</th>" );
			//--span;
		}
		*/
		/*
		if ( null != getPrevMonth() ) {
			sb.append( "<th align=\"left\">" );
			if ( null != getStyleLink() ) {
				sb.append( "<a class=\"" + getStyleLink() + "\" href=\"" + getPrevMonth() + "\">" );
			} else {
				sb.append( "<a href=\"" + getPrevMonth() + "\">" );
			}
			sb.append( "&lt;" );
			sb.append( "</a>" );
			sb.append( "</th>" );
			//--span;
		}
		*/
		/*
		if ( null != getNextMonth() ) {
			--span;
		}
		if ( null != getNextYear() ) {
			--span;
		}
		*/
		sb.append( getHeader( cal, months ) );
		/*
		if ( null != getNextMonth() ) {
			sb.append( "<th align=\"right\">" );
			if ( null != getStyleLink() ) {
				sb.append( "<a class=\"" + getStyleLink() + "\" href=\"" + getNextMonth() + "\">" );
			} else {
				sb.append( "<a href=\"" + getNextMonth() + "\">" );
			}
			sb.append( "&gt;" );
			sb.append( "</a>" );
			sb.append( "</th>" );
		}
		if ( null != getNextYear() ) {
			sb.append( "<th align=\"right\">" );
			if ( null != getStyleLink() ) {
				sb.append( "<a class=\"" + getStyleLink() + "\" href=\"" + getNextYear() + "\">" );
			} else {
				sb.append( "<a href=\"" + getNextYear() + "\">" );
			}
			sb.append( "&gt;&gt;" );
			sb.append( "</a>" );
			sb.append( "</th>" );
		}
		*/
		sb.append( "</tr></table>" );
		return( sb.toString() );
	}
	
	private String getHeader( Calendar cal, String[] months ) {
		StringBuffer sb = new StringBuffer();
		sb.append( "<th align=\"center\"" );
		if ( null != getProperty( PROPERTY_HEADERCLASS ) ) {
			//sb.append( "<th align=\"center\" colspan=\"" + span + "\" class=\"" + getStyleMonth() + "\">" );
			sb.append( " class=\"" + getProperty( PROPERTY_HEADERCLASS ) + "\"" );
		}
		if ( null != getProperty( PROPERTY_HEADERSTYLE ) ) {
			sb.append( " style=\"" + getProperty( PROPERTY_HEADERSTYLE ) + "\"" );
		}
		sb.append( ">" );
		String monthName = null;
		if ( 0 < getMonth() ) {
			monthName = months[ getMonth()-1 ];
		} else {
			monthName = months[ cal.get( Calendar.MONTH ) ];
		}
		//if ( isShortMonth() ) {
		//	sb.append( monthName.substring(0, 3) );
		//} else {
			sb.append( monthName );
		//}
		sb.append( " " + cal.get( Calendar.YEAR ) );
		sb.append( "</th>" );
		return( sb.toString() );
	}
}
