package rs.util.bugzilla;

public class Milestone {
	public final static String TOKEN = "target_milestone=";
	protected String tag = null;
	protected String label = null;

	protected Product product = null;

	public Milestone(String tag, String label) {
		super();
		this.tag = tag;
		this.label = label;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String generate() {
		StringBuilder sb = new StringBuilder();

		sb.append("\n|-");
		sb.append("\n| ");
		sb.append(" ||");
		sb.append(" ||");
		sb.append(generateMilestoneLabel()).append("'''");
		sb.append(generateMilestoneSummaryLinks());
		sb.append(generateMilestonePrincipalLinks());
		sb.append(generateMilestoneOtherPrincipalLinks());

		return sb.toString();
	}
	
	protected String generateMilestoneLabel() {
		StringBuilder sb = new StringBuilder();
		String productCondition = product.createCondition();
		String milestoneCondition = createCondition();
		
		sb.append(Link.createQuery(label, productCondition, milestoneCondition));
		sb.append(" <br /> ").append(Link.createReport("status", "bug_status"/*xAxis*/, "target_milestone"/*yAxis*/, "priority"/*zAxis*/, productCondition, milestoneCondition));
		

		return sb.toString();
	}


	public String generateMilestoneSummaryLinks() {
		StringBuilder sb = new StringBuilder();
		String productCondition = product.createCondition();
		String milestoneCondition = createCondition();

		sb.append(" || ").append(Link.createQuery("open", productCondition, milestoneCondition, BugStatus.createCondition(BugStatus._OPEN_)));
		sb.append(" ").append(Link.createQuery("verify", productCondition, milestoneCondition, BugStatus.createCondition(BugStatus.RESOLVED)));
		sb.append(" <br /> ").append(Link.createQuery("finished", productCondition, milestoneCondition, BugStatus.createCondition(BugStatus.VERIFIED)));
		sb.append(" ").append(Link.createQuery("closed", productCondition, milestoneCondition, BugStatus.createCondition(BugStatus.CLOSED)));

		return sb.toString();
	}

	public String generateMilestonePrincipalLinks() {
		StringBuilder sb = new StringBuilder();
		String productCondition = product.createCondition();
		String milestoneCondition = createCondition();
		
		for (Principal principal : product.getRelevantPrincipals()) {
			sb.append(" || ").append(Link.createQuery("involved", productCondition, milestoneCondition, principal.generateCondition(1/* idx */, true/* assigned */, true/* qa */), BugStatus.createCondition(BugStatus._INVOLVED_)));
			sb.append(" <br /> ").append(Link.createQuery("ass.", productCondition, milestoneCondition, principal.generateCondition(1/* idx */, true/* assigned */, false/* qa */), BugStatus.createCondition(BugStatus._ASSIGNED_)));
			sb.append(" ").append(Link.createQuery("qs", productCondition, milestoneCondition, principal.generateCondition(1/* idx */, false/* assigned */, true/* qa */), BugStatus.createCondition(BugStatus.RESOLVED)));
		}

		return sb.toString();
	}

	public String generateMilestoneOtherPrincipalLinks() {
		StringBuilder sb = new StringBuilder();
		String productCondition = product.createCondition();
		String milestoneCondition = createCondition();
		
		sb.append(" || ").append(Link.createQuery("involved", productCondition, milestoneCondition, Principal.generateNegatedCondition(0/* idx */, true/* assigned */, true/* qa */, product.getRelevantPrincipals()), BugStatus.createCondition(BugStatus._INVOLVED_)));
		sb.append(" <br /> ").append(Link.createQuery("ass.", productCondition, milestoneCondition, Principal.generateNegatedCondition(0/* idx */, true/* assigned */, false/* qa */, product.getRelevantPrincipals()), BugStatus.createCondition(BugStatus._ASSIGNED_)));
		sb.append(" ").append(Link.createQuery("qs", productCondition, milestoneCondition, Principal.generateNegatedCondition(0/* idx */, false/* assigned */, true/* qa */, product.getRelevantPrincipals()), BugStatus.createCondition(BugStatus.RESOLVED)));

		return sb.toString();
	}

	protected String createCondition() {
		StringBuilder sb = new StringBuilder();

		sb.append(TOKEN).append(tag);

		return sb.toString();
	}
}
