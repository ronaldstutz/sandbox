package sandbox.java8.fi;

import static java.lang.System.out;

import java.util.function.Consumer;

public class SaveGetter {
	public String operation4(String s) {return "aaaa"+s;}
	
	public static String get(Consumer<SaveGetter> block) {
		SaveGetter resource = new SaveGetter();
		try {
			block.accept(resource);
			return "aaa";
		} catch (Exception e) {
			return "n/a";
		}
    }
	
	public static void main(String... args) {
//		String s1 = null;
//		String s2 = "aaa";
		
//		int i1 = s1.length();
//		int i2 = s2.length();
		
		
		String b = SaveGetter.get(resource -> resource.operation4("bbbbbb"));
		System.out.println(b);
	}
}
