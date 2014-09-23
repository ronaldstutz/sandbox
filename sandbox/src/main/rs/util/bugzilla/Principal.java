package rs.util.bugzilla;

import java.util.List;

public class Principal {
	private String name = "";
	private String email = "";

	public Principal(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String generateCondition(int idx, boolean assigned, boolean qa) {
		StringBuilder sb = new StringBuilder();

		sb.append("&emailtype").append(idx).append("=substring");
		sb.append("&email").append(idx).append("=").append(email);

		if (assigned) {
			sb.append("&emailassigned_to").append(idx).append("=1");
		}

		if (qa) {
			sb.append("&emailqa_contact").append(idx).append("=1");
		}

		return sb.toString();
	}

	public static String generateNegatedCondition(int idx, boolean assigned, boolean qa, List<Principal> relevantPrincipals) {
		StringBuilder sb = new StringBuilder();

		sb.append("negate").append(idx).append("=1");
		int principalIdx = 0;
		for (Principal principal : relevantPrincipals) {
			if (assigned) {
				sb.append("&field").append(idx).append("-0-").append(principalIdx).append("=assigned_to");
				sb.append("&type").append(idx).append("-0-").append(principalIdx).append("=equals");
				sb.append("&value").append(idx).append("-0-").append(principalIdx).append("=").append(principal.getEmail());
				principalIdx++;
			}

			if (qa) {
				sb.append("&field").append(idx).append("-0-").append(principalIdx).append("=qa_contact");
				sb.append("&type").append(idx).append("-0-").append(principalIdx).append("=equals");
				sb.append("&value").append(idx).append("-0-").append(principalIdx).append("=").append(principal.getEmail());
				principalIdx++;
			}			
		}

		return sb.toString();
	}
}
