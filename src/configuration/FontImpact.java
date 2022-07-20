package configuration;

import java.awt.GraphicsEnvironment;

public class FontImpact {
  public static void main(String[] a) {
    GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
    String[] fontnames = e.getAvailableFontFamilyNames();
    for (String s : fontnames) {
      System.out.println(s);
    }
  }
}

