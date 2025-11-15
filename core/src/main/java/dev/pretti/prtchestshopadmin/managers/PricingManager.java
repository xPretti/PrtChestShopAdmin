package dev.pretti.prtchestshopadmin.managers;

import dev.pretti.prtchestshopadmin.interfaces.ISignDetails;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class PricingManager
{
  private final HashMap<UUID, ISignDetails> pricingDetails = new HashMap<>();

  public void addSignDetails(UUID uuid, ISignDetails pricingDetails)
  {
    this.pricingDetails.put(uuid, pricingDetails);
  }

  public ISignDetails getSignDetails(UUID uuid)
  {
    return pricingDetails.get(uuid);
  }

  public void removeSignDetails(UUID uuid)
  {
    pricingDetails.remove(uuid);
  }

  public void clear()
  {
    pricingDetails.clear();
  }

  public boolean exist(@NotNull UUID uniqueId) {
    return pricingDetails.containsKey(uniqueId);
  }
}
