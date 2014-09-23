package rs.util.bugzilla;

import java.util.ArrayList;
import java.util.List;

public class WikiBugzillaLinkGenerator {

	private List<Product> products = null;

	public void addProduct(final Product product) {
		if (products == null) {
			products = new ArrayList<Product>();
		}
		products.add(product);
	}

	public String generateWikiTable() {
		final StringBuilder sb = new StringBuilder();

		for (final Product product : products) {

			sb.append("='").append(product.getLabel()).append("' Produkt Backlog=");
			sb.append("\n\n=> Zum Produkt: [[").append(product.getProductPage()).append("]]");
			sb.append("\n==Sinn und Zweck dieses Abschnitts==");
			sb.append("\nSammlung von Bugzilla Abfragen");

			sb.append("\n");
			sb.append("\n");

			sb.append("\n===Aktuell im Fokus===");
			for (final Issue issue : product.getFocussedIssues()) {
				sb.append("\n").append(issue.generateDependencyLink());
			}

			sb.append("\n===Backlogs and Sprints===");
			// --- header
			sb.append("\n\n{| style=\"color:black; background-color:#ffffcc;\" cellpadding=\"5\" cellspacing=\"0\" border=\"1\"");
			sb.append("\n! Backlog !! Selected Backlog !! Sprint/Milestone     !! ALLE");
			for (final Principal principal : product.getRelevantPrincipals()) {
				sb.append("!! ").append(principal.getName());
			}

			sb.append("!! ANDERE");
//			sb.append("!! Niemand");

			sb.append(product.generate());

			// --- footer
			sb.append("\n|}");
			sb.append("\n");
			sb.append("\n");

			sb.append("\n===Themen===");
			for (final Issue issue : product.getSammelIssues()) {
				sb.append("\n").append(issue.generateDependencyLink());
			}
		}


		return sb.toString();
	}

	// ---------------------------------------------------------------------------------------------------------------

	public static void main(final String[] args) {
		final StringBuilder sb = new StringBuilder();
        final boolean doVM_VSR = false;
        final boolean doEventManager = true;
        final boolean doCCC = false;
        final boolean doShApp = false;
		final WikiBugzillaLinkGenerator gen = new WikiBugzillaLinkGenerator();

		if (doVM_VSR) {
            gen.addProduct(ProductFactory.assembleVmAndVsr());
        }

        if (doEventManager) {
            gen.addProduct(ProductFactory.assembleEventManager());
        }

        if (doCCC) {
            gen.addProduct(ProductFactory.assembleCCC());
        }

        if (doShApp) {
            gen.addProduct(ProductFactory.assembleShApp());
        }
		sb.append(gen.generateWikiTable());

		System.out.println(sb.toString());
	}

}
