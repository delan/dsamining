// The following interface has been trivially modified to suit the class and
// method design of the production code. Changes marked with "MODIFICATION".

import java.util.*;	// for Iterator and Iterable

/**
 * IShed is the interface for all Sheds in the STMining application
 *
 * @author	Patrick Peursum
 * @version	5.0
 */


public interface IShed extends Iterable<OrePile> {
   public String getName();
   public void setName(String newName);
   // MODIFICATION: change type from int to OreType.
   public OreType getOreType();
   
   public void addPile(OrePile newPile);
   public double satisfyOrder(ShipmentOrder order);
   
   public double calcTotalOreWeight();
   public double calcTotalMetalWeight();

   public Iterator<OrePile> iterator();
}
