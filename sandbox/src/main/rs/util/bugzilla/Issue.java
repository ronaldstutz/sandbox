package rs.util.bugzilla;

public class Issue {
	private int bugzillaId = 0;
	private String label = "";
	public Issue(final int bugzillaId, final String label) {
		super();
		this.bugzillaId = bugzillaId;
		this.label = label;
	}
	public int getBugzillaId() {
		return bugzillaId;
	}
	public void setBugzillaId(final int bugzillaId) {
		this.bugzillaId = bugzillaId;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(final String label) {
		this.label = label;
	}

	public String generateDependencyLink() {
		final StringBuilder sb = new StringBuilder();

        sb.append(" [http://server13/bugzilla/show_bug.cgi?id=").append(bugzillaId).append(" ").append(label)
                .append(" ] [http://server13/bugzilla/showdependencytree.cgi?id=").append(bugzillaId).append(" #").append(bugzillaId)
                .append(" dependency]");

		return sb.toString();
	}
}
