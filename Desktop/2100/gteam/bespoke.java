
/* ShoeSize - Eric McCreath 2015 - GPL
 * This class stores a persons shoe size.
 */

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class ShoeSize {
    private static final String SHOESIZEENAME = "SHOESIZE";
    public static final int SHOESIZEMAX = 15;
    public static final int SHOESIZEMIN = 3;

    static final String FILENAME = "shoesize.txt";

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
        try {
            BufferedReader br = Files.newBufferedReader(
                    new File(FILENAME).toPath(), Charset.forName("US-ASCII"));
            String line= br.readLine();
            ss.shoesize = Integer.parseInt(line);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return ss;
    }

    void save() {
        try{
            BufferedWriter bw = Files.newBufferedWriter(new File(FILENAME).toPath());
            bw.append(this.shoesize.toString());
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
