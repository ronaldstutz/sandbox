package rs.util.bugzilla;

public enum BugStatus {
	UNCONFIRMED("UNCONFIRMED"), NEW("NEW"), ASSIGNED("ASSIGNED"), REOPENED("REOPENED"), RESOLVED("RESOLVED"), VERIFIED("VERIFIED"), CLOSED("CLOSED"), _OPEN_("_OPEN_"), _INVOLVED_("_INVOLVED_"), _ASSIGNED_("_ASSIGNED_");

	public static final String TOKEN = "bug_status=";

	private String name = null;

	BugStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String createCondition(BugStatus... bugStatus) {
		StringBuilder sb = new StringBuilder();

		for (BugStatus bs : bugStatus) {
			if (sb.length() != 0) {
				sb.append("&");
			}

			switch (bs) {
			case _OPEN_:
				sb.append(createCondition(UNCONFIRMED, NEW, ASSIGNED, REOPENED, RESOLVED));
				break;
			case _INVOLVED_:
				sb.append(createCondition(UNCONFIRMED, NEW, ASSIGNED, REOPENED, RESOLVED));
				break;
			case _ASSIGNED_:
				sb.append(createCondition(UNCONFIRMED, NEW, ASSIGNED, REOPENED));
				break;

			default:
				sb.append(TOKEN);
				sb.append(bs.name);
				break;
			}
		}

		return sb.toString();
	}
}
