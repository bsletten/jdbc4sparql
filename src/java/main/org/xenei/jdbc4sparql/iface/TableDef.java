package org.xenei.jdbc4sparql.iface;

import java.util.List;

public interface TableDef
{

	/**
	 * get the name for the table.
	 * @return
	 */
	String getName();
	/**
	 * Get the list of columns in the table
	 * @return
	 */
	List<? extends ColumnDef> getColumnDefs();
	
	ColumnDef getColumnDef(int idx);
	
	ColumnDef getColumnDef(String name);
	
	int getColumnCount();
	
	/**
	 * Get the table sort order key.
	 * returns null if the table is not sorted.
	 * @return
	 */
	SortKey getSortKey();
	
	/**
	 * Verify that the row data matches the definition
	 * @param row
	 * @throws IllegalArgumentException on errors
	 */
	void verify( Object[] row );
	
	int getColumnIndex( ColumnDef column );
	
	int getColumnIndex( String columnName );
}
