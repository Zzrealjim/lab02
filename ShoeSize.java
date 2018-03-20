package lab2;


/* ShoeSize - Eric McCreath 2015 - GPL 
 * This class stores a persons shoe size.
 */

public class ShoeSize {
	private static final String SHOESIZEENAME = "SHOESIZE";
	public static final int SHOESIZEMAX = 15;
	public static final int SHOESIZEMIN = 3;

	static final String FILENAME = "shoesize.xml";

	private Integer shoesize;

	public ShoeSize() {
		shoesize = null;
	}

	public String show() {
		return (shoesize == null ? "" : shoesize.toString());
	}

	public boolean set(Integer v) {
		if (v == null || v >= ShoeSize.SHOESIZEMIN && v <= ShoeSize.SHOESIZEMAX) {
			shoesize = v;
			save();
			return true;
		} else {
			shoesize = null;
			return false;
		}
	}

	static ShoeSize load() {
                // add code here that will load shoe size from a file called "FILENAME"
                return new ShoeSize();
	}

	void save() {
		// add code here that will save shoe size into a file called "FILENAME"
	}
}
