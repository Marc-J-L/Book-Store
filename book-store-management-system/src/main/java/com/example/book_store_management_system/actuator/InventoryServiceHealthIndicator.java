package com.example.book_store_management_system.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;


@Component
public class InventoryServiceHealthIndicator implements HealthIndicator {

  private boolean isInventoryServiceUp() {
    return true;
  }

  @Override
  public Health health() {
    if(isInventoryServiceUp()) {
      return Health.up().withDetail("inventory", "Service is UP").build();

    }else{
      return Health.down().withDetail("inventory", "Service is DOWN").build();
    }
  }
  
}
