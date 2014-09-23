package rs.util.bugzilla;

import java.util.*;

public class Product {
	public final static String TOKEN = "product=";
	private String label = "";
	private String[] tags = null;
	private String productPage = "";
	private List<Release> releases = new ArrayList<Release>();
	private List<Principal> relevantPrincipals = new ArrayList<Principal>();
	private List<Issue> sammelIssues = new ArrayList<Issue>();
	private List<Issue> focussedIssues = new ArrayList<Issue>();

	public Product(String[] tags, String label, String productPage, List<Release> releases, List<Principal> relevantPrincipals, List<Issue> sammelIssues, List<Issue> focussedIssues) {
		super();
		this.label = label;
		this.tags = tags;
		this.productPage = productPage;
		this.releases = releases;
		this.relevantPrincipals = relevantPrincipals;
		this.sammelIssues = sammelIssues;
		this.focussedIssues = focussedIssues;

		for (Milestone release : releases) {
			release.setProduct(this);
		}
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getProductPage() {
		return productPage;
	}

	public void setProductPage(String productPage) {
		this.productPage = productPage;
	}

	public List<Release> getReleases() {
		return releases;
	}

	public void setReleases(List<Release> releases) {
		this.releases = releases;
	}

	public List<Principal> getRelevantPrincipals() {
		return relevantPrincipals;
	}

	public void setRelevantPrincipals(List<Principal> relevantPrincipals) {
		this.relevantPrincipals = relevantPrincipals;
	}

	public List<Issue> getSammelIssues() {
		return sammelIssues;
	}

	public void setSammelIssues(List<Issue> sammelIssues) {
		this.sammelIssues = sammelIssues;
	}

	public List<Issue> getFocussedIssues() {
		return focussedIssues;
	}

	public void setFocussedIssues(List<Issue> focussedIssues) {
		this.focussedIssues = focussedIssues;
	}

	public String generate() {
		StringBuilder sb = new StringBuilder();

		sb.append("\n|-");
		sb.append("\n| '''").append(generateProductLabel()).append("'''");
		sb.append(" ||");
		sb.append(" ||");
		sb.append(generateProductSummaryLinks());
		sb.append(generateProductPrincipalLinks());
		sb.append(generateProductOtherPrincipalLinks());
		sb.append(generateReleaseLinks());

		return sb.toString();
	}

	private String generateProductLabel() {
		StringBuilder sb = new StringBuilder();
		String productCondition = createCondition();

		sb.append(Link.createQuery(label, productCondition));
		sb.append(" <br /> ").append(Link.createReport("status", "bug_status"/*xAxis*/, "target_milestone"/*yAxis*/, "priority"/*zAxis*/, productCondition));
		
		
		/*
		http://server13/bugzilla/report.cgi?
			x_axis_field=bug_status
			&y_axis_field=target_milestone
			&z_axis_field=priority
			&product=VM%2FGV
			&product=VSR
			&bug_status=UNCONFIRMED
			&bug_status=NEW
			&bug_status=ASSIGNED
			&bug_status=REOPENED
			&bug_status=RESOLVED
			&bug_status=VERIFIED
			&bug_status=CLOSED
			
	*/
	
		return sb.toString();
	}

	private String generateProductSummaryLinks() {
		StringBuilder sb = new StringBuilder();
		String productCondition = createCondition();

		sb.append(" || ").append(Link.createQuery("open", productCondition, BugStatus.createCondition(BugStatus._OPEN_)));
		sb.append(" ").append(Link.createQuery("verify", productCondition, BugStatus.createCondition(BugStatus.RESOLVED)));
		sb.append(" <br /> ").append(Link.createQuery("finished", productCondition, BugStatus.createCondition(BugStatus.VERIFIED)));
		sb.append(" ").append(Link.createQuery("closed", productCondition, BugStatus.createCondition(BugStatus.CLOSED)));

		return sb.toString();
	}

	private String generateProductPrincipalLinks() {
		StringBuilder sb = new StringBuilder();
		String productCondition = createCondition();

		for (Principal principal : relevantPrincipals) {
			sb.append(" || ").append(Link.createQuery("involved", productCondition, principal.generateCondition(1/* idx */, true/* assigned */, true/* qa */), BugStatus.createCondition(BugStatus._INVOLVED_)));
			sb.append(" <br /> ").append(Link.createQuery("ass.", productCondition, principal.generateCondition(1/* idx */, true/* assigned */, false/* qa */), BugStatus.createCondition(BugStatus._ASSIGNED_)));
			sb.append(" ").append(Link.createQuery("qs", productCondition, principal.generateCondition(1/* idx */, false/* assigned */, true/* qa */), BugStatus.createCondition(BugStatus.RESOLVED)));
		}

		return sb.toString();
	}

	private String generateProductOtherPrincipalLinks() {
		StringBuilder sb = new StringBuilder();
		String productCondition = createCondition();

		sb.append(" || ").append(Link.createQuery("involved", productCondition, Principal.generateNegatedCondition(0/* idx */, true/* assigned */, true/* qa */, relevantPrincipals), BugStatus.createCondition(BugStatus._INVOLVED_)));
		sb.append(" <br /> ").append(Link.createQuery("ass.", productCondition, Principal.generateNegatedCondition(0/* idx */, true/* assigned */, false/* qa */, relevantPrincipals), BugStatus.createCondition(BugStatus._ASSIGNED_)));
		sb.append(" ").append(Link.createQuery("qs", productCondition, Principal.generateNegatedCondition(0/* idx */, false/* assigned */, true/* qa */, relevantPrincipals), BugStatus.createCondition(BugStatus.RESOLVED)));

		return sb.toString();
	}

	private String generateReleaseLinks() {
		StringBuilder sb = new StringBuilder();

		for (Milestone release : releases) {
			sb.append(release.generate());
		}

		return sb.toString();
	}

	public String createCondition() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < tags.length; i++) {
			if (i>0) {
				sb.append("&");
			}
			sb.append(TOKEN).append(tags[i]);
		}		

		return sb.toString();
	}
}
