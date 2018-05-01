
/* ShoeSize - Eric McCreath 2015 - GPL
 * This class stores a persons shoe size.
 */

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ShoeSize {
    private static final String SHOESIZEENAME = "SHOESIZE";
    public static final int SHOESIZEMAX = 15;
    public static final int SHOESIZEMIN = 3;

    static final String FILENAME = "shoesize.json";

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
        ShoeSize ss = new ShoeSize();
        File f = new File(FILENAME);
        try {
            JSONObject OBJ = (JSONObject) JSONValue.parse(new FileReader(f));
            ss.shoesize = ((Long) OBJ.get(SHOESIZEENAME)).intValue();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ss;
    }

    void save() {
        File f = new File(FILENAME);
        JSONObject obj = new JSONObject();
        obj.put(SHOESIZEENAME,shoesize);
        FileWriter out;
        try {
            out = new FileWriter(f);
            obj.writeJSONString(out);
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
