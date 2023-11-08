package effective_java.item_2_builder;

import static effective_java.item_2_builder.NyPizza.Size.LARGE;
import static effective_java.item_2_builder.Pizza.Topping.HAM;
import static effective_java.item_2_builder.Pizza.Topping.ONION;
import static effective_java.item_2_builder.Pizza.Topping.PEPPER;
import static effective_java.item_2_builder.Pizza.Topping.SAUSAGE;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public abstract class Pizza {
  public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE}
  final Set<Topping> toppings;

  Pizza(Builder<?> builder) {
    toppings = builder.toppings.clone();
  }

  abstract static class Builder<T extends Builder<T>> {
    EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

    public T addTopping(Topping topping) {
      toppings.add(Objects.requireNonNull(topping));
      return self();
    }

    abstract Pizza build();

    protected abstract T self();
  }
}

class NyPizza extends Pizza {
  public enum Size {SMALL, MEDIUM, LARGE}
  private final Size size;

  public static class Builder extends Pizza.Builder<Builder> {
    private final Size size;
    public Builder(Size size) {
      this.size = Objects.requireNonNull(size);
    }

    @Override NyPizza build() {
      return new NyPizza(this);
    }

    @Override protected Builder self() {
      return this;
    }
  }

  private NyPizza(Builder builder) {
    super(builder);
    size = builder.size;
  }
}

class Calzone extends Pizza {
  private final boolean sauceInside;

  private Calzone(Builder builder) {
    super(builder);
    this.sauceInside = builder.sauceInside;
  }

  public static class Builder extends Pizza.Builder<Builder> {
    private boolean sauceInside = false;

    public Builder sauceInside() {
       sauceInside = true;
       return this;
    }

    @Override Calzone build() {
      return new Calzone(this);
    }

    @Override protected Builder self() {
      return this;
    }
  }
}

class Main {
  public static void main(String[] args) {
    var nyPizza = new NyPizza.Builder(LARGE)
      .addTopping(SAUSAGE).addTopping(HAM)
      .build();

    var calzone = new Calzone.Builder()
      .addTopping(ONION).addTopping(PEPPER)
      .sauceInside()
      .build();


  }
}