package rs.util.bugzilla;


import java.util.*;

public class BugzillaLinkGenerator {

	private List<String> products = new ArrayList<String>();
	private List<String[]> persons = new ArrayList<String[]>();
	private Map<String[], List<String>> milestonesBySeason = new LinkedHashMap<String[], List<String>>();
	private int fieldCnt = 0;

	final private static String LINK_PREFIX = "http://server13/bugzilla/buglist.cgi?columnlist=bug_severity%2Cpriority%2Cassigned_to%2Cbug_status%2Cresolution%2Cshort_desc%2Cestimated_time%2Cactual_time%2Cremaining_time%2Ccf_issue_typ%2Ccf_product_proposal%2Ctarget_milestone&query_format=advanced";

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		boolean doVM_VSR = true;
		boolean doCCC = false;

		if (doVM_VSR) {
			BugzillaLinkGenerator gen = new BugzillaLinkGenerator();

			gen.persons.add(new String[] { "hs", "hansjoerg.stucki%40nimbus.ch" });
			gen.persons.add(new String[] { "fej", "juerg.feldmann%40nimbus.ch" });
			gen.persons.add(new String[] { "str", "ronald.stutz%40nimbus.ch" });
			gen.persons.add(new String[] { "knh", "hanspeter.knill%40nimbus.ch" });
			gen.persons.add(new String[] { "vop", "patrick.volery%40nimbus.ch" });
			gen.persons.add(new String[] { "wim", "markus.widmer%40nimbus.ch" });
			gen.persons.add(new String[] { "erm", "martin.erni%40nimbus.ch" });
			gen.persons.add(new String[] { "juon", "christian@juon.ch" });

			gen.products.add("VM%2FGV");
			gen.products.add("&product=VSR");

			{
				List<String> milestones = new ArrayList<String>();
				milestones.add("20091009");
				milestones.add("20091106");
				milestones.add("20091204");
				milestones.add("20010127");
				milestones.add("20010205");
				milestones.add("20010212");
				milestones.add("20010226");
				gen.milestonesBySeason.put(new String[] { "GV Saison 2010", "GV%20Saison%202010" }, milestones);
			}
			{
				List<String> milestones = new ArrayList<String>();
				milestones.add("20100904");
				milestones.add("20101101");
				milestones.add("20110103");
				gen.milestonesBySeason.put(new String[] { "GV Saison 2011", "GV%20Saison%202011" }, milestones);
			}
			{
				List<String> milestones = new ArrayList<String>();
//				milestones.add("20100904");
//				milestones.add("20101101");
//				milestones.add("20110103");
				gen.milestonesBySeason.put(new String[] { "GV Saison 2012", "GV%20Saison%202012" }, milestones);
			}
			{
				List<String> milestones = new ArrayList<String>();
//				milestones.add("20100904");
//				milestones.add("20101101");
//				milestones.add("20110103");
				gen.milestonesBySeason.put(new String[] { "GV Saison 2013", "GV%20Saison%202013" }, milestones);
			}
			sb.append(gen.generate("'''VM/GV & VSR'''"));
		}

		if (doCCC) {
			BugzillaLinkGenerator gen = new BugzillaLinkGenerator();

			gen.persons.add(new String[] { "hs", "hansjoerg.stucki%40nimbus.ch" });
			gen.persons.add(new String[] { "fej", "juerg.feldmann%40nimbus.ch" });
			gen.persons.add(new String[] { "str", "ronald.stutz%40nimbus.ch" });
			gen.persons.add(new String[] { "knh", "hanspeter.knill%40nimbus.ch" });
			gen.persons.add(new String[] { "vop", "patrick.volery%40nimbus.ch" });
			gen.persons.add(new String[] { "wim", "markus.widmer%40nimbus.ch" });
			gen.persons.add(new String[] { "erm", "martin.erni%40nimbus.ch" });
			gen.persons.add(new String[] { "juon", "christian@juon.ch" });

			gen.products.add("CCC");

			{
				List<String> milestones = new ArrayList<String>();
				milestones.add("20100904");
				milestones.add("20101101");
				milestones.add("20110103");
				gen.milestonesBySeason.put(new String[] { "GV Saison 2011", "GV%20Saison%202011" }, milestones);
			}
			sb.append("\n\n").append(gen.generate("'''CCC'''"));
		}

		System.out.println(sb.toString());
	}

	private String generate(String product) {
		StringBuilder sb = new StringBuilder();

		// --- header
		sb.append("{| style=\"color:black; background-color:#ffffcc;\" cellpadding=\"5\" cellspacing=\"0\" border=\"1\"");
		sb.append("\n! Backlog !! Selected Backlog !! Sprint/Milestone     !! ALLE");
		for (int i = 0; i < persons.size(); i++) {
			String kuerzel = persons.get(i)[0];
			sb.append("!! ").append(kuerzel);
		}
		sb.append("!! Andere !! Niemand");

		// ---
		sb.append("\n|-");
		sb.append("\n| ").append(product);
		sb.append(" ||");
		sb.append(" ||");
		sb.append(generateLinksByStatus(null/* season */));
		sb.append(generateLinksByPerson(null/* season */));

		// ---
		for (Map.Entry<String[], List<String>> entry : milestonesBySeason.entrySet()) {
			String seasonTag = entry.getKey()[0];
			String seasonLink = entry.getKey()[1];
			List<String> msList = entry.getValue();
			sb.append("\n|-");
			sb.append("\n| || ").append(seasonTag);
			sb.append(" ||");
			sb.append(generateLinksByStatus(seasonLink));
			sb.append(generateLinksByPerson(seasonLink));

			for (String ms : msList) {
				sb.append("\n|-");
				sb.append("\n| || || ").append(ms);
				sb.append(generateLinksByStatus(ms));
				sb.append(generateLinksByPerson(ms));
			}
		}

		// --- footer
		sb.append("\n|}");

		return sb.toString();
	}

	private String generateLinksByStatus(String milestone) {
		StringBuilder sb = new StringBuilder();

		sb.append(" || [").append(LINK_PREFIX);
		sb.append("&bug_status=UNCONFIRMED");
		sb.append("&bug_status=NEW");
		sb.append("&bug_status=ASSIGNED");
		sb.append("&bug_status=REOPENED");
		sb.append("&").append(generateProducts(false/* negate */));
		if (milestone != null) {
			sb.append("&").append(generateMilestone(milestone));
		}
		sb.append(" open]");

		sb.append(" [").append(LINK_PREFIX);
		sb.append("&bug_status=RESOLVED");
		sb.append("&").append(generateProducts(false/* negate */));
		if (milestone != null) {
			sb.append("&").append(generateMilestone(milestone));
		}
		sb.append(" verify]");

		sb.append(" <br /> [").append(LINK_PREFIX);
		sb.append("&bug_status=VERIFIED");
		sb.append("&").append(generateProducts(false/* negate */));
		if (milestone != null) {
			sb.append("&").append(generateMilestone(milestone));
		}
		sb.append(" finished]");

		sb.append(" [").append(LINK_PREFIX);
		sb.append("&bug_status=CLOSED");
		sb.append("&").append(generateProducts(false/* negate */));
		if (milestone != null) {
			sb.append("&").append(generateMilestone(milestone));
		}
		sb.append(" closed]");

		return sb.toString();
	}

	private String generateLinksByPerson(String milestone) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < persons.size(); i++) {
			String email = persons.get(i)[1];
			sb.append(" || [").append(LINK_PREFIX);
			sb.append("&bug_status=UNCONFIRMED");
			sb.append("&bug_status=NEW");
			sb.append("&bug_status=ASSIGNED");
			sb.append("&bug_status=REOPENED");
			sb.append("&bug_status=RESOLVED");
			sb.append("&").append(generateProducts(false/* negate */));
			if (milestone != null) {
				sb.append("&").append(generateMilestone(milestone));
			}
			sb.append("&").append(generatePersons(true/* assigned */, true/* qa */, email)).append(" involved]");

			sb.append(" <br /> [").append(LINK_PREFIX);
			sb.append("&bug_status=UNCONFIRMED");
			sb.append("&bug_status=NEW");
			sb.append("&bug_status=ASSIGNED");
			sb.append("&bug_status=REOPENED");
			sb.append("&").append(generateProducts(false/* negate */));
			if (milestone != null) {
				sb.append("&").append(generateMilestone(milestone));
			}
			sb.append("&").append(generatePersons(true/* assigned */, false/* qa */, email)).append(" ass.]");

			sb.append("  [").append(LINK_PREFIX);
			sb.append("&bug_status=RESOLVED");
			sb.append("&").append(generateProducts(false/* negate */));
			if (milestone != null) {
				sb.append("&").append(generateMilestone(milestone));
			}
			sb.append("&").append(generatePersons(false/* assigned */, true/* qa */, email)).append(" qs]");
		}
		sb.append(" || [http://server13/bugzilla/buglist.cgi?cmdtype=dorem&remaction=run&namedcmd=VM%2FVSR%3AGVSaison2010%3AOTHER&sharer_id=14 andere]");
		sb.append(" || [http://server13/bugzilla/buglist.cgi?cmdtype=dorem&remaction=run&namedcmd=VM%2FVSR%3AGVSaison2010%3AVM&sharer_id=14 niemand]");

		return sb.toString();
	}

	private String generateProducts(boolean negate) {
		StringBuilder sb = new StringBuilder();
		if (!negate) {
			for (String product : products) {
				if (sb.length() != 0) {
					sb.append("&");
				}
				sb.append("product=").append(product);
			}
		} else {
			sb.append("negate").append(fieldCnt).append("=1");
			for (int i = 0; i < products.size(); i++) {
				sb.append("field").append(fieldCnt).append("-0-").append(i).append("product");
				sb.append("type").append(fieldCnt).append("-0-").append(i).append("equals");
				sb.append("value").append(fieldCnt).append("-0-").append(i).append(products.get(i));
			}
			fieldCnt++;
		}

		return sb.toString();
	}

	private String generatePersons(boolean assigned, boolean qa, String email) {
		StringBuilder sb = new StringBuilder();

		sb.append("&emailtype1=substring");
		sb.append("&email1=").append(email);

		if (assigned) {
			sb.append("&emailassigned_to1=1");
		}

		if (qa) {
			sb.append("&emailqa_contact1=1");
		}

		return sb.toString();
	}

	private String generateMilestone(String ms) {
		StringBuilder sb = new StringBuilder();
		sb.append("target_milestone=").append(ms);
		return sb.toString();
	}
}
