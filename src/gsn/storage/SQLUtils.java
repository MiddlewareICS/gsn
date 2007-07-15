package gsn.storage;

import gsn.utils.CaseInsensitiveComparator;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;

public class SQLUtils {

	public static ArrayList < CharSequence > extractTableNamesUsedInQuery ( CharSequence query ) {
		ArrayList<CharSequence> toReturn = new ArrayList < CharSequence >();
		Pattern pattern = Pattern.compile( "(\"[^\"]*\")|((\\w+)(\\.((\\w+)|\\*)))" , Pattern.CASE_INSENSITIVE );
		Matcher matcher = pattern.matcher( query );
		while ( matcher.find( ) ) {
			if ( matcher.group( 2 ) == null ) continue;
			String tableName = matcher.group( 3 );
			// System.out.println(matcher.group(3));
			toReturn.add(tableName);
		}

		return toReturn;
	}

	/**
	 * Table renaming, note that the renameMapping should be a tree map. This
	 * method gets a sql query and changes the table names using the mappings
	 * provided in the second argument.<br>
	 * 
	 * @param query
	 * @param renameMapping
	 * @return
	 */
	public static StringBuilder newRewrite ( CharSequence query , TreeMap < CharSequence , CharSequence > renameMapping ) {
		// Selecting strings between pair of "" : (\"[^\"]*\")
		// Selecting tableID.tableName or tableID.* : (\\w+(\\.(\w+)|\\*))
		// The combined pattern is : (\"[^\"]*\")|(\\w+\\.((\\w+)|\\*))
		Pattern pattern = Pattern.compile( "(\"[^\"]*\")|((\\w+)(\\.((\\w+)|\\*)))" , Pattern.CASE_INSENSITIVE );
		Matcher matcher = pattern.matcher( query );
		StringBuffer result = new StringBuffer( );
		if ( !( renameMapping.comparator( ) instanceof CaseInsensitiveComparator ) ) throw new RuntimeException( "Query rename needs case insensitive treemap." );
		while ( matcher.find( ) ) {
			if ( matcher.group( 2 ) == null ) continue;
			String tableName = matcher.group( 3 );
			CharSequence replacement = renameMapping.get( tableName );
			// $4 means that the 4th group of the match should be appended to the
			// string (the forth group contains the field name).
			if ( replacement != null ) matcher.appendReplacement( result , new StringBuilder( replacement ).append( "$4" ).toString( ) );
		}
		String toReturn = matcher.appendTail( result ).toString( ).toLowerCase( );
		int indexOfFrom = toReturn.indexOf( " from " )>=0?toReturn.indexOf( " from " ) + " from ".length( ):0;
		int indexOfWhere = ( toReturn.lastIndexOf( " where " ) > 0 ? ( toReturn.lastIndexOf( " where " ) ) : toReturn.length( ) );
		String selection = toReturn.substring( indexOfFrom , indexOfWhere );
		Pattern fromClausePattern = Pattern.compile( "\\s*(\\w+)\\s*" , Pattern.CASE_INSENSITIVE );
		Matcher fromClauseMather = fromClausePattern.matcher( selection );
		result = new StringBuffer( );
		while ( fromClauseMather.find( ) ) {
			if ( fromClauseMather.group( 1 ) == null ) continue;
			String tableName = fromClauseMather.group( 1 );
			CharSequence replacement = renameMapping.get( tableName );
			if ( replacement != null ) 
				fromClauseMather.appendReplacement( result , replacement.toString( ) + " ");         
		}
		String cleanFromClause = fromClauseMather.appendTail( result ).toString( );
		String finalResult = StringUtils.replace( toReturn , selection , cleanFromClause );
		return new StringBuilder(finalResult);
	}
	
	/**
	 * This method gets a sql query and changes the table names which are equal to 
	 * <code>tableNameToRename</code> to the <code>replacement</code> 
	 * provided in the second argument.<br>
	 * 
	 * @param query
	 * @param tableNameToRename Table name to be replaced
	 * @param replaceTo 
	 * @return
	 */
	public static StringBuilder newRewrite ( CharSequence query , CharSequence tableNameToRename, CharSequence replaceTo ) {
		// Selecting strings between pair of "" : (\"[^\"]*\")
		// Selecting tableID.tableName or tableID.* : (\\w+(\\.(\w+)|\\*))
		// The combined pattern is : (\"[^\"]*\")|(\\w+\\.((\\w+)|\\*))
		Pattern pattern = Pattern.compile( "(\"[^\"]*\")|((\\w+)(\\.((\\w+)|\\*)))" , Pattern.CASE_INSENSITIVE );
		Matcher matcher = pattern.matcher( query );
		StringBuffer result = new StringBuffer( );
		while ( matcher.find( ) ) {
			if ( matcher.group( 2 ) == null ) continue;
			String tableName = matcher.group( 3 );
			if(tableName.equals(tableNameToRename)){
				// $4 means that the 4th group of the match should be appended to the
				// string (the forth group contains the field name).
				if ( replaceTo != null ) matcher.appendReplacement( result , new StringBuilder( replaceTo ).append( "$4" ).toString( ) );
			}
		}
		String toReturn = matcher.appendTail( result ).toString( ).toLowerCase( );
		int indexOfFrom = toReturn.indexOf( " from " )>=0?toReturn.indexOf( " from " ) + " from ".length( ):0;
		int indexOfWhere = ( toReturn.lastIndexOf( " where " ) > 0 ? ( toReturn.lastIndexOf( " where " ) ) : toReturn.length( ) );
		String selection = toReturn.substring( indexOfFrom , indexOfWhere );
		Pattern fromClausePattern = Pattern.compile( "\\s*(\\w+)\\s*" , Pattern.CASE_INSENSITIVE );
		Matcher fromClauseMather = fromClausePattern.matcher( selection );
		result = new StringBuffer( );
		while ( fromClauseMather.find( ) ) {
			if ( fromClauseMather.group( 1 ) == null ) continue;
			String tableName = fromClauseMather.group( 1 );
			if (tableName.equals(tableNameToRename) && replaceTo != null)
				fromClauseMather.appendReplacement( result , replaceTo.toString( ) + " ");
		}
		String cleanFromClause = fromClauseMather.appendTail( result ).toString( );
		String finalResult = StringUtils.replace( toReturn , selection , cleanFromClause );
		return new StringBuilder(finalResult);
	}

	public static String extractProjection(String pQuery) {
		String query = pQuery.trim().toLowerCase();
		int indexOfFrom = query.indexOf( " from " ) ;
		int indexOfSelect =query.indexOf("select");
		return pQuery.substring(indexOfSelect+"select".length(), indexOfFrom);
	}

	public static String extractWhereClause(String pQuery) {
		int indexOfWhere = pQuery.toLowerCase().indexOf( " where " ) ;
		if (indexOfWhere<0)
			return " true ";
		String toReturn = pQuery.substring(indexOfWhere+" where".length(),pQuery.length());
		System.out.println(toReturn);
		return toReturn;
	}

	public static void main ( String [ ] args ) {
		TreeMap < CharSequence , CharSequence > map = new TreeMap < CharSequence , CharSequence >( new CaseInsensitiveComparator( ) );
		String query ="seLect ali.fd, x.x, fdfd.fdfd, *.r, * from x,x, bla, x whEre k";
		map.put( "x" , "done" );
		CharSequence out = newRewrite( query , map );
		System.out.println( out.toString( ) );
		System.out.println(extractProjection(query)  		  );
		out = newRewrite( extractProjection(query) , map );
		System.out.println( out.toString( ) );
	}
}
