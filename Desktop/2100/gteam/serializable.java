
/* ShoeSize - Eric McCreath 2015 - GPL
 * This class stores a persons shoe size.
 */

import java.io.*;

public class ShoeSize implements Serializable{
    private static final String SHOESIZEENAME = "SHOESIZE";
    public static final int SHOESIZEMAX = 15;
    public static final int SHOESIZEMIN = 3;

    static final String FILENAME = "shoesize.ser";

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
        ShoeSize ss = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            ss = (ShoeSize) ois.readObject();
            ois.close();
            return ss;
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ShoeSize();
    }

    void save() {
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME));
            oos.writeObject(this);
            oos.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
