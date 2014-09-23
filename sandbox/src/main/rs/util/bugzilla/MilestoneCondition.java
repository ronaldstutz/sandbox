package rs.util.bugzilla;

import java.util.*;

public class MilestoneCondition implements ConditionIF {

	public static final String TAG = "target_milestone=";

	private List<Milestone> milestones = new ArrayList<Milestone>();

	public MilestoneCondition(List<Milestone> milestones) {
		super();
		this.milestones = milestones;
	}

	public String createCondition() {
		StringBuilder sb = new StringBuilder();
		if (milestones == null || milestones.isEmpty()) {
			throw new IllegalStateException("milestones must not be null or empty");
		}

		for (Milestone milestone : milestones) {
			if (sb.length() != 0) {
				sb.append("&");
			}
			sb.append(TAG).append(milestone.getTag());
		}

		return sb.toString();
	}

}
