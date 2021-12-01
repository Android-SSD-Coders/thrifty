package com.amplifyframework.datastore.generated.model;


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

/** This is an auto generated class representing the Product type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Products", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Product implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField TITLE = field("title");
  public static final QueryField DESCRIPTION = field("description");
  public static final QueryField PRICE = field("price");
  public static final QueryField SIZE = field("size");
  public static final QueryField COLOR = field("color");
  public static final QueryField IMAGE = field("image");
  public static final QueryField CATEGORY_ID = field("categoryID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String title;
  private final @ModelField(targetType="String", isRequired = true) String description;
  private final @ModelField(targetType="String", isRequired = true) String price;
  private final @ModelField(targetType="String", isRequired = true) String size;
  private final @ModelField(targetType="String", isRequired = true) String color;
  private final @ModelField(targetType="String", isRequired = true) String image;
  private final @ModelField(targetType="ID") String categoryID;
  public String getId() {
      return id;
  }
  
  public String getTitle() {
      return title;
  }
  
  public String getDescription() {
      return description;
  }
  
  public String getPrice() {
      return price;
  }
  
  public String getSize() {
      return size;
  }
  
  public String getColor() {
      return color;
  }
  
  public String getImage() {
      return image;
  }
  
  public String getCategoryId() {
      return categoryID;
  }
  
  private Product(String id, String title, String description, String price, String size, String color, String image, String categoryID) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.price = price;
    this.size = size;
    this.color = color;
    this.image = image;
    this.categoryID = categoryID;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Product product = (Product) obj;
      return ObjectsCompat.equals(getId(), product.getId()) &&
              ObjectsCompat.equals(getTitle(), product.getTitle()) &&
              ObjectsCompat.equals(getDescription(), product.getDescription()) &&
              ObjectsCompat.equals(getPrice(), product.getPrice()) &&
              ObjectsCompat.equals(getSize(), product.getSize()) &&
              ObjectsCompat.equals(getColor(), product.getColor()) &&
              ObjectsCompat.equals(getImage(), product.getImage()) &&
              ObjectsCompat.equals(getCategoryId(), product.getCategoryId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTitle())
      .append(getDescription())
      .append(getPrice())
      .append(getSize())
      .append(getColor())
      .append(getImage())
      .append(getCategoryId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Product {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("title=" + String.valueOf(getTitle()) + ", ")
      .append("description=" + String.valueOf(getDescription()) + ", ")
      .append("price=" + String.valueOf(getPrice()) + ", ")
      .append("size=" + String.valueOf(getSize()) + ", ")
      .append("color=" + String.valueOf(getColor()) + ", ")
      .append("image=" + String.valueOf(getImage()) + ", ")
      .append("categoryID=" + String.valueOf(getCategoryId()))
      .append("}")
      .toString();
  }
  
  public static TitleStep builder() {
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
  public static Product justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Product(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      title,
      description,
      price,
      size,
      color,
      image,
      categoryID);
  }
  public interface TitleStep {
    DescriptionStep title(String title);
  }
  

  public interface DescriptionStep {
    PriceStep description(String description);
  }
  

  public interface PriceStep {
    SizeStep price(String price);
  }
  

  public interface SizeStep {
    ColorStep size(String size);
  }
  

  public interface ColorStep {
    ImageStep color(String color);
  }
  

  public interface ImageStep {
    BuildStep image(String image);
  }
  

  public interface BuildStep {
    Product build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep categoryId(String categoryId);
  }
  

  public static class Builder implements TitleStep, DescriptionStep, PriceStep, SizeStep, ColorStep, ImageStep, BuildStep {
    private String id;
    private String title;
    private String description;
    private String price;
    private String size;
    private String color;
    private String image;
    private String categoryID;
    @Override
     public Product build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Product(
          id,
          title,
          description,
          price,
          size,
          color,
          image,
          categoryID);
    }
    
    @Override
     public DescriptionStep title(String title) {
        Objects.requireNonNull(title);
        this.title = title;
        return this;
    }
    
    @Override
     public PriceStep description(String description) {
        Objects.requireNonNull(description);
        this.description = description;
        return this;
    }
    
    @Override
     public SizeStep price(String price) {
        Objects.requireNonNull(price);
        this.price = price;
        return this;
    }
    
    @Override
     public ColorStep size(String size) {
        Objects.requireNonNull(size);
        this.size = size;
        return this;
    }
    
    @Override
     public ImageStep color(String color) {
        Objects.requireNonNull(color);
        this.color = color;
        return this;
    }
    
    @Override
     public BuildStep image(String image) {
        Objects.requireNonNull(image);
        this.image = image;
        return this;
    }
    
    @Override
     public BuildStep categoryId(String categoryId) {
        this.categoryID = categoryId;
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
    private CopyOfBuilder(String id, String title, String description, String price, String size, String color, String image, String categoryId) {
      super.id(id);
      super.title(title)
        .description(description)
        .price(price)
        .size(size)
        .color(color)
        .image(image)
        .categoryId(categoryId);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
    
    @Override
     public CopyOfBuilder description(String description) {
      return (CopyOfBuilder) super.description(description);
    }
    
    @Override
     public CopyOfBuilder price(String price) {
      return (CopyOfBuilder) super.price(price);
    }
    
    @Override
     public CopyOfBuilder size(String size) {
      return (CopyOfBuilder) super.size(size);
    }
    
    @Override
     public CopyOfBuilder color(String color) {
      return (CopyOfBuilder) super.color(color);
    }
    
    @Override
     public CopyOfBuilder image(String image) {
      return (CopyOfBuilder) super.image(image);
    }
    
    @Override
     public CopyOfBuilder categoryId(String categoryId) {
      return (CopyOfBuilder) super.categoryId(categoryId);
    }
  }
  
}
