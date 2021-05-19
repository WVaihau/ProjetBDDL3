package queryBuilder;

public class Builder {
	public static void main(String[] args) throws Exception {
		String sqlQuery = SqlQueryBuilder.newBuilder()
				.queryType(QueryType.SELECT)
				.selectCols("cols1", "cols2", "cols3", "cols4")
				.table("table")
				.join("table1", "cols11", "cols1")
				.join("table2", "cols21", "table", "cols2")
				.where("cols1","200", compareType.EQUAL)
				.where("cols2","(1,2,3)", compareType.IN)
				.getQuery();
		
		System.out.println(sqlQuery);
	}
}
