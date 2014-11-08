package physics;

import java.util.HashMap;

public class Foo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HashMap<String, Double> foo = new HashMap<String, Double>();
		foo.put("asdf", 5.0);
		foo.put("asdfasdf", 6.0);
		foo.put("kevin", 13.0);
		
		for(String s : foo.keySet()){
			System.out.println(s + " " + foo.get(s));
		}

	}

}
