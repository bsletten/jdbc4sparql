/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.xenei.jdbc4sparql.impl;

import org.xenei.jdbc4sparql.iface.Catalog;
import org.xenei.jdbc4sparql.iface.Column;
import org.xenei.jdbc4sparql.iface.ColumnDef;
import org.xenei.jdbc4sparql.iface.Schema;
import org.xenei.jdbc4sparql.iface.Table;

public class ColumnImpl extends NamespaceImpl implements Column
{
	private final Table table;
	private final ColumnDef columnDef;

	public ColumnImpl( final String namespace, final Table table,
			final ColumnDef columnDef )
	{
		super(namespace, columnDef.getLabel());
		this.table = table;
		this.columnDef = columnDef;
	}

	public ColumnImpl( final Table table, final ColumnDef columnDef )
	{
		this(table.getNamespace(), table, columnDef);
	}

	@Override
	public Catalog getCatalog()
	{
		return getSchema().getCatalog();
	}

	@Override
	public String getColumnClassName()
	{
		return columnDef.getColumnClassName();
	}

	protected ColumnDef getColumnDef()
	{
		return columnDef;
	}

	@Override
	public String getDBName()
	{
		return String.format("%s.%s.%s", getSchema().getLocalName(), getTable()
				.getLocalName(), getLocalName());
	}

	@Override
	public int getDisplaySize()
	{
		return columnDef.getDisplaySize();
	}

	@Override
	public String getLabel()
	{
		return columnDef.getLabel();
	}

	@Override
	public int getNullable()
	{
		return columnDef.getNullable();
	}

	@Override
	public int getPrecision()
	{
		return columnDef.getPrecision();
	}

	@Override
	public int getScale()
	{
		return columnDef.getScale();
	}

	@Override
	public Schema getSchema()
	{
		return table.getSchema();
	}

	@Override
	public Table getTable()
	{
		return table;
	}

	@Override
	public int getType()
	{
		return columnDef.getType();
	}

	@Override
	public String getTypeName()
	{
		return columnDef.getTypeName();
	}

	@Override
	public boolean isAutoIncrement()
	{
		return columnDef.isAutoIncrement();
	}

	@Override
	public boolean isCaseSensitive()
	{
		return columnDef.isCaseSensitive();
	}

	@Override
	public boolean isCurrency()
	{
		return columnDef.isCurrency();
	}

	@Override
	public boolean isDefinitelyWritable()
	{
		return columnDef.isDefinitelyWritable();
	}

	@Override
	public boolean isReadOnly()
	{
		return columnDef.isReadOnly();
	}

	@Override
	public boolean isSearchable()
	{
		return columnDef.isSearchable();
	}

	@Override
	public boolean isSigned()
	{
		return columnDef.isSigned();
	}

	@Override
	public boolean isWritable()
	{
		return columnDef.isWritable();
	}

	@Override
	public String toString()
	{
		return String.format("Column[%s.%s]", getCatalog().getLocalName(),
				getDBName());
	}

}
