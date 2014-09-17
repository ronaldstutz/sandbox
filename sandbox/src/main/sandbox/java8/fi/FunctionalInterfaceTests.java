package sandbox.java8.fi;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;

public class FunctionalInterfaceTests {
	public static void main(String[] args) {
		Runnable r1 = () -> System.out.println("My Runnable");
		// X r1 = ("a") -> return "b";
		// Y y1 = (new ArrayList<Integer>(12);) -> return
		// (int x, int y) -> x+y;

		System.out.println(convert(input -> (input - 32) * 5.0 / 9.0, 98.6));

		// Convert Kilometers to Miles
		System.out.println(convert(input -> input / 1.609344, 8));

		System.out.println(stab(input -> input + input, "abc"));

		// ###############################
		List<String> myList = Arrays.asList("element1", "element2", "element3");
		for (String element : myList) {
			System.out.println(element);
		}
		// ---
		myList.forEach(new Consumer<String>() {
			public void accept(String element) {
				System.out.println(element);
			}
		});
		// ---
		myList.forEach((String element) -> System.out.println(element));
		// ---
		myList.forEach(element -> System.out.println(element));

		// #############################
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 99);
		numbers.forEach(irgendwas -> System.out.println(irgendwas));

		// ##############################
		Collection<String> myList1 = Arrays.asList("Hello", "Java", "wasgeht");
		long countLongStrings = myList1.stream()
				.filter(new Predicate<String>() {
					@Override
					public boolean test(String element) {
						return element.length() > 4;
					}
				}).count();
		System.out.println(countLongStrings);
		// ---
		countLongStrings = myList1.stream()
				.filter(element -> element.length() > 4).count();
		System.out.println(countLongStrings);

		// ######################
		int factor = 1000; // 1
		IntUnaryOperator times1000 = (int x) -> {
			return x * factor;
		}; // 2
		Arrays.stream(new int[] { 1, 2, 3, 4, 5 }).map(times1000)
				.forEach(System.out::println);

		// #######################
		myList1.forEach(System.out::println);

		// #######################
		System.out.println(A.make("roni"));
		System.out.println(A.make(""));
		System.out.println(A.make(null));
		// ---
		myList1.forEach(new B()::apply);
	}

	static class B implements Function<String, String> {
		@Override
		public String apply(String s) {
			try {
				return "Length of '" + s + "'=" + String.valueOf(s.length());
			} catch (Exception e) {
				return "Length of " + s + "=n/a";
			}
		}
	}

	static class A {
		public static String make(String s) {
			try {
				return "Length of '" + s + "'=" + String.valueOf(s.length());
			} catch (Exception e) {
				return "Length of " + s + "=n/a";
			}
		}
	}

	static double convert(Converter converter, double input) {
		return converter.convert(input);
	}

	static String stab(StringStab stab, String input) {
		return stab.doIt(input);
	}

	interface X {
		String m(String arg);
	}

	interface Y {
		int m(Iterable<Integer> arg);
	}

	interface Converter {
		double convert(double input);
	}

	interface StringStab {
		String doIt(String input);
	}
}
