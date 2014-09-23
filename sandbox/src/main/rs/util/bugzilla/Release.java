package rs.util.bugzilla;

import java.util.*;

public class Release extends Milestone {
	private List<Milestone> milestones = new ArrayList<Milestone>();

	public Release(String tag, String label, List<Milestone> milestones) {
		super(tag, label);
		this.milestones = milestones;
	}

	public List<Milestone> getMilestones() {
		return milestones;
	}

	public void setMilestones(List<Milestone> milestones) {
		this.milestones = milestones;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
		for (Milestone milestone : milestones) {
			milestone.setProduct(product);
		}
	}

	@Override
	public String generate() {
		StringBuilder sb = new StringBuilder();

		sb.append("\n|-");
		sb.append("\n| ");
		sb.append(" ||");
		sb.append("'''").append(generateMilestoneLabel()).append("'''");
		sb.append(" ||");
		sb.append(generateMilestoneSummaryLinks());
		sb.append(generateMilestonePrincipalLinks());
		sb.append(generateMilestoneOtherPrincipalLinks());
		sb.append(generateMilestoneLinks());

		return sb.toString();
	}

	public String generateMilestoneLinks() {
		StringBuilder sb = new StringBuilder();

		for (Milestone milestone : milestones) {
			sb.append(milestone.generate());
		}

		return sb.toString();
	}

	protected String createCondition() {
		StringBuilder sb = new StringBuilder();

		sb.append(TOKEN).append(tag);
		for (Milestone milestone : milestones) {
			sb.append("&");
			sb.append(milestone.createCondition());
		}

		return sb.toString();
	}

}
