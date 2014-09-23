package rs.util.bugzilla;

public class Link {
	final public static String BUGZILLA_SERVER_URL = "http://server13/bugzilla/";;
    final public static String QUERY_PREFIX        = BUGZILLA_SERVER_URL
                                                           + "buglist.cgi?columnlist=bug_severity%2Cpriority%2Cassigned_to_realname%2Cqa_contact_realname%2Cbug_status%2Cresolution%2Cshort_desc%2Cestimated_time%2Cactual_time%2Cremaining_time%2Ccf_issue_typ%2Ccf_product_proposal%2Ctarget_milestone%2Ckeywords&query_format=advanced";
	final public static String REPORT_PREFIX = BUGZILLA_SERVER_URL + "report.cgi?&query_format=report-table&format=table&action=wrap";

	static String createQuery(final String label, final String... params) {
		final StringBuilder sb = new StringBuilder();
		sb.append("[").append(QUERY_PREFIX);
		for (final String s : params) {
			sb.append("&");
			sb.append(s);
		}
		sb.append(" ").append(label).append("]");

		return sb.toString();
	}

	static String createReport(final String label, final String xAxis, final String yAxis, final String zAxis, final String... params) {
		final StringBuilder sb = new StringBuilder();
		sb.append("[").append(REPORT_PREFIX);
		sb.append("&x_axis_field=").append(xAxis);
		sb.append("&y_axis_field=").append(yAxis);
		sb.append("&z_axis_field=").append(zAxis);
		for (final String s : params) {
			sb.append("&");
			sb.append(s);
		}
		sb.append(" ").append(label).append("]");

		return sb.toString();
	}
}
