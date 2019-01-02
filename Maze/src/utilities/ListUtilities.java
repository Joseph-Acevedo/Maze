package utilities;

import java.util.ArrayList;

public class ListUtilities {
	
	public static void copyArrayList(ArrayList source, ArrayList destination) {
		for (int i = 0; i < source.size(); i++) {
			destination.set(i, source.get(i));
		}
			
	}

}
