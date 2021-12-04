package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the ProductFavorite type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "ProductFavorites", authRules = {
  @AuthRule(allow = AuthStrategy.PRIVATE, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ }),
  @AuthRule(allow = AuthStrategy.PRIVATE, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
@Index(name = "byProduct", fields = {"productID","favoriteID"})
@Index(name = "byFavorite", fields = {"favoriteID","productID"})
public final class ProductFavorite implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField PRODUCT = field("productID");
  public static final QueryField FAVORITE = field("favoriteID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Product", isRequired = true) @BelongsTo(targetName = "productID", type = Product.class) Product product;
  private final @ModelField(targetType="Favorite", isRequired = true) @BelongsTo(targetName = "favoriteID", type = Favorite.class) Favorite favorite;
  public String getId() {
      return id;
  }
  
  public Product getProduct() {
      return product;
  }
  
  public Favorite getFavorite() {
      return favorite;
  }
  
  private ProductFavorite(String id, Product product, Favorite favorite) {
    this.id = id;
    this.product = product;
    this.favorite = favorite;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      ProductFavorite productFavorite = (ProductFavorite) obj;
      return ObjectsCompat.equals(getId(), productFavorite.getId()) &&
              ObjectsCompat.equals(getProduct(), productFavorite.getProduct()) &&
              ObjectsCompat.equals(getFavorite(), productFavorite.getFavorite());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getProduct())
      .append(getFavorite())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("ProductFavorite {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("product=" + String.valueOf(getProduct()) + ", ")
      .append("favorite=" + String.valueOf(getFavorite()))
      .append("}")
      .toString();
  }
  
  public static ProductStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static ProductFavorite justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new ProductFavorite(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      product,
      favorite);
  }
  public interface ProductStep {
    FavoriteStep product(Product product);
  }
  

  public interface FavoriteStep {
    BuildStep favorite(Favorite favorite);
  }
  

  public interface BuildStep {
    ProductFavorite build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements ProductStep, FavoriteStep, BuildStep {
    private String id;
    private Product product;
    private Favorite favorite;
    @Override
     public ProductFavorite build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new ProductFavorite(
          id,
          product,
          favorite);
    }
    
    @Override
     public FavoriteStep product(Product product) {
        Objects.requireNonNull(product);
        this.product = product;
        return this;
    }
    
    @Override
     public BuildStep favorite(Favorite favorite) {
        Objects.requireNonNull(favorite);
        this.favorite = favorite;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, Product product, Favorite favorite) {
      super.id(id);
      super.product(product)
        .favorite(favorite);
    }
    
    @Override
     public CopyOfBuilder product(Product product) {
      return (CopyOfBuilder) super.product(product);
    }
    
    @Override
     public CopyOfBuilder favorite(Favorite favorite) {
      return (CopyOfBuilder) super.favorite(favorite);
    }
  }
  
}
