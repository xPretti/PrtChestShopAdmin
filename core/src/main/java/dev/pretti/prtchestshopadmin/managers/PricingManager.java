package dev.pretti.prtchestshopadmin.managers;

import dev.pretti.prtchestshopadmin.types.PricingDetails;

import java.util.HashMap;
import java.util.UUID;

public class PricingManager
{
  private final HashMap<UUID, PricingDetails> pricingDetails = new HashMap<>();

  public void addPricingDetails(UUID uuid, PricingDetails pricingDetails)
  {
    this.pricingDetails.put(uuid, pricingDetails);
  }

  public PricingDetails getPricingDetails(UUID uuid)
  {
    return pricingDetails.get(uuid);
  }

  public void removePricingDetails(UUID uuid)
  {
    pricingDetails.remove(uuid);
  }

  public void clear()
  {
    pricingDetails.clear();
  }
}
