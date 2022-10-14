package prr.util;

import java.text.Collator;
import java.util.Locale;
import java.util.Comparator;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.Serializable;

public class NaturalLanguageTextComparator implements Comparator<String>, Serializable {

  private transient Collator collator;

  public NaturalLanguageTextComparator() {
    collator = Collator.getInstance(Locale.getDefault());
    collator.setStrength(Collator.PRIMARY);
  }

  private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
    ois.defaultReadObject();
    collator = Collator.getInstance(Locale.getDefault());
    collator.setStrength(Collator.PRIMARY);
  }

  @Override
  public int compare(String s1, String s2) {
    return collator.compare(s1, s2);
  }
}
