package effective_java.item_2_builder;

public class NutritionFacts {
  private final int servingSize;
  private final int servings;
  private final int calories;
  private final int fat;
  private final int sodium;
  private final int carbohydrate;

  private NutritionFacts(Builder builder) {
    servingSize = builder.servingSize;
    servings = builder.servings;
    calories = builder.calories;
    sodium = builder.sodium;
    fat = builder.fat;
    carbohydrate = builder.carbohydrate;
  }

  // every class should have its Builder in order to use this pattern.
  public static class Builder {
    private final int servingSize;
    private final int servings;
    private int calories;
    private int fat;
    private int sodium;
    private int carbohydrate;

    public Builder(int servingSize, int servings) {
      this.servingSize = servingSize;
      this.servings = servings;
    }

    public Builder calories(int val) {
      calories = val;
      return this;
    }

    public Builder fat(int val) {
      fat = val;
      return this;
    }

    public Builder sodium(int val) {
      sodium = val;
      return this;
    }

    public Builder carbohydrate(int val) {
      carbohydrate = val;
      return this;
    }

    public NutritionFacts build() {
      return new NutritionFacts(this);
    }
  }

  public static void main(String[] args) {
    var nutrients = new NutritionFacts.Builder(2, 3)
      .calories(100)
      .fat(100)
      .sodium(20)
      .carbohydrate(30)
      .build();
  }
}
