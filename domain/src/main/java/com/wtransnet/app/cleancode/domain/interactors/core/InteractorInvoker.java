package com.wtransnet.app.cleancode.domain.interactors.core;

public interface InteractorInvoker {
  void execute(Interactor interactor);

  void execute(Interactor interactor, InteractorPriority priority);
}
