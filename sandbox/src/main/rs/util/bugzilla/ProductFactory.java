package rs.util.bugzilla;

import java.util.ArrayList;
import java.util.List;

public class ProductFactory {
    public static Product assembleVmAndVsr() {
        final List<Principal> relevantPrincipals = new ArrayList<Principal>();
        relevantPrincipals.add(new Principal("hs", "hansjoerg.stucki%40nimbus.ch"));
        relevantPrincipals.add(new Principal("fej", "juerg.feldmann%40nimbus.ch"));
        relevantPrincipals.add(new Principal("str", "ronald.stutz%40nimbus.ch"));
        relevantPrincipals.add(new Principal("knh", "hanspeter.knill%40nimbus.ch"));
        relevantPrincipals.add(new Principal("vop", "patrick.volery%40nimbus.ch"));
        relevantPrincipals.add(new Principal("wim", "markus.widmer%40nimbus.ch"));
        relevantPrincipals.add(new Principal("erm", "martin.erni%40nimbus.ch"));
        relevantPrincipals.add(new Principal("juon", "christian@juon.ch"));

        final List<Release> releases = new ArrayList<Release>();
        {
            final List<Milestone> milestones = new ArrayList<Milestone>();
            // milestones.add(new Milestone("20100904", "20100904"));
            releases.add(new Release("GV%20Saison%202015", "GV Saison 2015", milestones));
        }

        final List<Issue> sammelIssues = new ArrayList<Issue>();
        //      sammelIssues.add(new Issue(3359, "Gewichtete Instruktionen"));
        //      sammelIssues.add(new Issue(3642, "Abstimmmodus"));
        //      sammelIssues.add(new Issue(3626, "Finderreports"));
        //      sammelIssues.add(new Issue(3374, "Instruktionen-Finder"));
        //      sammelIssues.add(new Issue(3742, "Adressänderungen aus VM Rücklauf ausführen"));
        //      sammelIssues.add(new Issue(3873, "Export AR / Import VM"));

        final List<Issue> focussedIssues = new ArrayList<Issue>();
        focussedIssues.add(new Issue(3742, "VOP,STR: Adressänderungen aus VM Rücklauf ausführen"));
        //      focussedIssues.add(new Issue(3873, "STR: Export AR / Import VM"));

        final Product product = new Product(new String[] { "VM%2FGV" }, "VM/GV", "Produkt Nimbus GV", releases, relevantPrincipals, sammelIssues,
                focussedIssues);

        return product;
    }

    public static Product assembleEventManager() {
        final List<Principal> relevantPrincipals = new ArrayList<Principal>();
        relevantPrincipals.add(new Principal("hs", "hansjoerg.stucki%40nimbus.ch"));
        relevantPrincipals.add(new Principal("fej", "juerg.feldmann%40nimbus.ch"));
        relevantPrincipals.add(new Principal("str", "ronald.stutz%40nimbus.ch"));
        relevantPrincipals.add(new Principal("knh", "hanspeter.knill%40nimbus.ch"));
        relevantPrincipals.add(new Principal("vop", "patrick.volery%40nimbus.ch"));
        relevantPrincipals.add(new Principal("wim", "markus.widmer%40nimbus.ch"));
        relevantPrincipals.add(new Principal("erm", "martin.erni%40nimbus.ch"));
        relevantPrincipals.add(new Principal("juon", "christian@juon.ch"));

        final List<Release> releases = new ArrayList<Release>();
        {
            final List<Milestone> milestones = new ArrayList<Milestone>();
            // milestones.add(new Milestone("20100904", "20100904"));
            releases.add(new Release("GV%20Saison%202015", "GV Saison 2015", milestones));
        }

        final List<Issue> sammelIssues = new ArrayList<Issue>();
        //      sammelIssues.add(new Issue(3359, "Gewichtete Instruktionen"));
        //      sammelIssues.add(new Issue(3642, "Abstimmmodus"));
        //      sammelIssues.add(new Issue(3626, "Finderreports"));
        //      sammelIssues.add(new Issue(3374, "Instruktionen-Finder"));
        //      sammelIssues.add(new Issue(3742, "Adressänderungen aus VM Rücklauf ausführen"));
        //      sammelIssues.add(new Issue(3873, "Export AR / Import VM"));

        final List<Issue> focussedIssues = new ArrayList<Issue>();
        focussedIssues.add(new Issue(5191, "VOP,WIM: Traktandentexte mehrsprachig im VM bewirtschaften"));
        //      focussedIssues.add(new Issue(3873, "STR: Export AR / Import VM"));

        final Product product = new Product(new String[] { "EventManager" }, "EventManager", "EventManager", releases, relevantPrincipals,
                sammelIssues, focussedIssues);

        return product;
    }

    public static Product assembleCCC() {
        final List<Principal> relevantPrincipals = new ArrayList<Principal>();
        relevantPrincipals.add(new Principal("hs", "hansjoerg.stucki%40nimbus.ch"));
        relevantPrincipals.add(new Principal("fej", "juerg.feldmann%40nimbus.ch"));
        relevantPrincipals.add(new Principal("str", "ronald.stutz%40nimbus.ch"));
        relevantPrincipals.add(new Principal("knh", "hanspeter.knill%40nimbus.ch"));
        relevantPrincipals.add(new Principal("bir", "rolf.bickel%40nimbus.ch"));
        relevantPrincipals.add(new Principal("vop", "patrick.volery%40nimbus.ch"));
        relevantPrincipals.add(new Principal("wim", "markus.widmer%40nimbus.ch"));
        // relevantPrincipals.add(new Principal("erm",
        // "martin.erni%40nimbus.ch"));
        relevantPrincipals.add(new Principal("juon", "christian@juon.ch"));

        final List<Release> releases = new ArrayList<Release>();
        {
            final List<Milestone> milestones = new ArrayList<Milestone>();
            milestones.add(new Milestone("20100802", "20100802"));
            milestones.add(new Milestone("20100906", "20100906"));
            milestones.add(new Milestone("20101004", "20101004"));
            milestones.add(new Milestone("20101101", "20101101"));
            milestones.add(new Milestone("20101206", "20101206"));
            milestones.add(new Milestone("20110103", "20110103"));
            milestones.add(new Milestone("20110207", "20110207"));
            releases.add(new Release("GV%20Saison%202011", "GV Saison 2011", milestones));
        }
        {
            final List<Milestone> milestones = new ArrayList<Milestone>();
            // milestones.add(new Milestone("20100904", "20100904"));
            // milestones.add(new Milestone("20101101", "20101101"));
            // milestones.add(new Milestone("20110103", "20110103"));
            releases.add(new Release("GV%20Saison%202012", "GV Saison 2012", milestones));
        }
        {
            final List<Milestone> milestones = new ArrayList<Milestone>();
            // milestones.add(new Milestone("20100904", "20100904"));
            releases.add(new Release("GV%20Saison%202013", "GV Saison 2013", milestones));
        }

        final List<Issue> sammelIssues = new ArrayList<Issue>();
        // sammelIssues.add(new Issue(3359, "Gewichtete Instruktionen"));

        final List<Issue> focussedIssues = new ArrayList<Issue>();
        focussedIssues.add(new Issue(3678, "SuisseId: Anwendung mit JavaClient"));

        final Product product = new Product(new String[] { "CCC" }, "CCC", "Produkt 'CCC'", releases, relevantPrincipals, sammelIssues,
                focussedIssues);
        return product;
    }

    public static Product assembleShApp() {
        final List<Principal> relevantPrincipals = new ArrayList<Principal>();
        relevantPrincipals.add(new Principal("hs", "hansjoerg.stucki%40nimbus.ch"));
        relevantPrincipals.add(new Principal("fej", "juerg.feldmann%40nimbus.ch"));
        relevantPrincipals.add(new Principal("str", "ronald.stutz%40nimbus.ch"));
        relevantPrincipals.add(new Principal("knh", "hanspeter.knill%40nimbus.ch"));
        relevantPrincipals.add(new Principal("bir", "rolf.bickel%40nimbus.ch"));
        relevantPrincipals.add(new Principal("vop", "patrick.volery%40nimbus.ch"));
        relevantPrincipals.add(new Principal("wim", "markus.widmer%40nimbus.ch"));
        // relevantPrincipals.add(new Principal("erm",
        // "martin.erni%40nimbus.ch"));
        relevantPrincipals.add(new Principal("juon", "christian@juon.ch"));

        final List<Release> releases = new ArrayList<Release>();
        {
            final List<Milestone> milestones = new ArrayList<Milestone>();
            // milestones.add(new Milestone("20100904", "20100904"));
            releases.add(new Release("GV%20Saison%202015", "GV Saison 2015", milestones));
        }

        final List<Issue> sammelIssues = new ArrayList<Issue>();
        // sammelIssues.add(new Issue(3359, "Gewichtete Instruktionen"));

        final List<Issue> focussedIssues = new ArrayList<Issue>();
        //        focussedIssues.add(new Issue(3678, "SuisseId: Anwendung mit JavaClient"));

        final Product product = new Product(new String[] { "ShApp" }, "ShApp", "Produkt Nimbus ShApp", releases, relevantPrincipals, sammelIssues,
                focussedIssues);
        return product;
    }
}
