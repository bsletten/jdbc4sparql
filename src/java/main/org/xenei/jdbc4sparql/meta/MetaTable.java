package org.xenei.jdbc4sparql.meta;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;


import org.apache.commons.collections.bag.TreeBag;
import org.xenei.jdbc4sparql.ColumnImpl;
import org.xenei.jdbc4sparql.iface.Catalog;
import org.xenei.jdbc4sparql.iface.Column;
import org.xenei.jdbc4sparql.iface.ColumnDef;
import org.xenei.jdbc4sparql.iface.Schema;
import org.xenei.jdbc4sparql.iface.SortKey;
import org.xenei.jdbc4sparql.iface.Table;
import org.xenei.jdbc4sparql.iface.TableDef;

public class MetaTable extends MetaNamespace implements Table
{
	private TableDef tableDef;
	private Collection<Object[]>data;
	private Schema schema;
	
	@SuppressWarnings( "unchecked" )
	MetaTable( Schema schema, TableDef tableDef )
	{
		this.schema = schema;
		this.tableDef= tableDef;
		if (tableDef.getSortKey() == null)
		{
			data = new ArrayList<Object[]>();
		}
		else
		{
			if (tableDef.getSortKey().isUnique())
			{
				data = new TreeSet<Object[]>( tableDef.getSortKey());
			}
			else
			{	// 11 is the default priority queue capacity
				data = new TreeBag( tableDef.getSortKey() );
			}
		}
	}
	
	public void addData( Object[] args )
	{
		tableDef.verify( args );
		data.add( args );
	}

	@Override
	public String getLocalName()
	{
		return tableDef.getName();
	}

	@Override
	public Schema getSchema()
	{
		return schema;
	}
	
	public ResultSet getResultSet()
	{
		return new FixedResultSet( data, this );
	}

	@Override
	public Catalog getCatalog()
	{
		return schema.getCatalog();
	}

	public TableDef getTableDef()
	{
		return tableDef;
	}
	
	public String getType()
	{
		return "TABLE";
	}

	@Override
	public boolean isEmpty()
	{
		return data.isEmpty();
	}

	public String getName()
	{
		return tableDef.getName();
	}

	public List<? extends ColumnDef> getColumnDefs()
	{
		return tableDef.getColumnDefs();
	}

	public ColumnDef getColumnDef( int idx )
	{
		return tableDef.getColumnDef(idx);
	}

	public ColumnDef getColumnDef( String name )
	{
		return tableDef.getColumnDef(name);
	}

	public int getColumnCount()
	{
		return tableDef.getColumnCount();
	}

	public SortKey getSortKey()
	{
		return tableDef.getSortKey();
	}

	public void verify( Object[] row )
	{
		tableDef.verify(row);
	}

	public int getColumnIndex( ColumnDef column )
	{
		return tableDef.getColumnIndex(column);
	}

	public int getColumnIndex( String columnName )
	{
		return tableDef.getColumnIndex(columnName);
	}

	@Override
	public Iterator<Column> getColumns()
	{
		return new Table.ColumnIterator(this, getColumnDefs());
	}

	@Override
	public Column getColumn( int idx )
	{
		return new ColumnImpl( this, getColumnDef(idx));
	}

	@Override
	public Column getColumn( String name )
	{
		return new ColumnImpl( this, getColumnDef(name));
	}

	
}
