package queryBuilder;

import java.util.ArrayList;

public class SqlQueryBuilder {

	private QueryType queryType;
	private String table;
	private ArrayList<String> where = new ArrayList<>();
	private ArrayList<String> join = new ArrayList<>();
	private String[] selectedCols;

	private SqlQueryBuilder() {}
	
	public static SqlQueryBuilder newBuilder() { return new SqlQueryBuilder();}
	
	public SqlQueryBuilder queryType(QueryType qt) {
		this.queryType = qt;
		return this;
	}
	
	public SqlQueryBuilder table(String tableName) {
		this.table = tableName;
		return this;
	}
	
	public SqlQueryBuilder where(String elt, String elt2, compareType comp) {
		String whereCond = elt + " ";
		
		if(comp == compareType.EQUAL) {
			whereCond = whereCond + "=";			
		}else if(comp == compareType.GREATER) {
			whereCond = whereCond + ">";			
		}else if(comp == compareType.LOWER) {
			whereCond = whereCond + "<";			
		}else {
			whereCond = whereCond + comp;			
		}
		
		whereCond = whereCond + " " + elt2;
		
		this.where.add(whereCond);
		return this;
	}
	
	public SqlQueryBuilder join(String table1, String elt_table1, String elt_table) {
		String StringJoin = "JOIN " + table1 + " ON "
				+ table1 + "."+ elt_table1 + " = " + this.table + "." + elt_table;
		
		this.join.add(StringJoin);
		return this;
	}

	public SqlQueryBuilder join(String table1, String elt_table1, String table, String elt_table) {
		String StringJoin = "JOIN " + table1 + " ON "
				+ table1 + "."+ elt_table1 + " = " + table + "." + elt_table;
		
		this.join.add(StringJoin);
		return this;
	}
	
	public SqlQueryBuilder selectCols(String...strings) {
		this.selectedCols = strings;
		return this;
	}
	
	public String getQuery() {
		StringBuilder qry = new StringBuilder()
				.append(this.queryType)
				.append(" ");
		if(this.queryType == QueryType.SELECT) {

			for(int i = 0; i < this.selectedCols.length ; i++) {
				qry.append(this.selectedCols[i]);
				
				if(i != this.selectedCols.length - 1) {					
					qry.append(", ");
				}
			}
			
			//FROM
			qry.append(" FROM ");
			qry.append(this.table);
			qry.append(" ");
			//JOIN
			for(int i = 0; i < this.join.size(); i++) {
				qry.append(this.join.get(i));
				if(i != this.join.size() - 1 ) {
					qry.append(" ");
				}
			}
			
			//WHERE
			qry.append(" WHERE ");
			for(int i = 0; i < this.where.size(); i++) {
				qry.append(this.where.get(i));
				if(i != this.where.size() - 1) {
					qry.append(" AND ");
				}
			}
		}
		
		return qry.toString();
	}
	

	
	
}
