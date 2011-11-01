package ubahfp;

import java.util.Comparator;
 
public class MaxRadiusComparator implements Comparator<AnlageVerdraengung> {
 /**
  * ACHTUNG reverse order!
  * 
  */
  public int compare(AnlageVerdraengung a1, AnlageVerdraengung a2) {
	  if(a1.getMaxRadius() == a2.getMaxRadius()){
		  return 0;
	  }else if(a1.getMaxRadius() < a2.getMaxRadius()){
		  return 1;
	  }else {
		  return -1;
	  }
  }
}