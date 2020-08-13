package com.zhoutao123.architect.layered.service;

import java.util.Optional;
import java.util.function.Function;

public abstract class AbstractConverter<B, D> {

  public abstract B converterTo(D d);

  public abstract D converterFrom(B b);

  public <P, T> T convertNullable(P p, Function<P, T> f) {
    return convertNullable(p, f, null);
  }

  public <P, T> T convertNullable(P p, Function<P, T> f, T defaultValue) {
    return Optional.ofNullable(p).map(f).orElse(defaultValue);
  }
}
