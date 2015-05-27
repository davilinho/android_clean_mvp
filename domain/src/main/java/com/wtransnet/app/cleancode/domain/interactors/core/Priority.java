package com.wtransnet.app.cleancode.domain.interactors.core;

public enum Priority {
  LOW(1),
  MEDIUM(50),
  HIGH(100),
  URGENT(200);

  private int priority;

  Priority(int priority) {
    this.priority = priority;
  }

  public int getPriorityValue() {
    return priority;
  }
}
