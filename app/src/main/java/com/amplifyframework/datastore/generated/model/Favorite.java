package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

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

/** This is an auto generated class representing the Favorite type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Favorites", authRules = {
  @AuthRule(allow = AuthStrategy.PRIVATE, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
@Index(name = "byUser", fields = {"userID"})
public final class Favorite implements Model {
  public static final QueryField ID = field("Favorite", "id");
  public static final QueryField TITLE_FAV = field("Favorite", "titleFav");
  public static final QueryField IMAGE_FAV = field("Favorite", "imageFav");
  public static final QueryField PRICE_FAV = field("Favorite", "priceFav");
  public static final QueryField SIZE_FAV = field("Favorite", "sizeFav");
  public static final QueryField CATEGORY_FAV = field("Favorite", "categoryFav");
  public static final QueryField USER_ID = field("Favorite", "userID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String titleFav;
  private final @ModelField(targetType="String", isRequired = true) String imageFav;
  private final @ModelField(targetType="String", isRequired = true) String priceFav;
  private final @ModelField(targetType="String", isRequired = true) String sizeFav;
  private final @ModelField(targetType="String", isRequired = true) String categoryFav;
  private final @ModelField(targetType="ID") String userID;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getTitleFav() {
      return titleFav;
  }
  
  public String getImageFav() {
      return imageFav;
  }
  
  public String getPriceFav() {
      return priceFav;
  }
  
  public String getSizeFav() {
      return sizeFav;
  }
  
  public String getCategoryFav() {
      return categoryFav;
  }
  
  public String getUserId() {
      return userID;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Favorite(String id, String titleFav, String imageFav, String priceFav, String sizeFav, String categoryFav, String userID) {
    this.id = id;
    this.titleFav = titleFav;
    this.imageFav = imageFav;
    this.priceFav = priceFav;
    this.sizeFav = sizeFav;
    this.categoryFav = categoryFav;
    this.userID = userID;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Favorite favorite = (Favorite) obj;
      return ObjectsCompat.equals(getId(), favorite.getId()) &&
              ObjectsCompat.equals(getTitleFav(), favorite.getTitleFav()) &&
              ObjectsCompat.equals(getImageFav(), favorite.getImageFav()) &&
              ObjectsCompat.equals(getPriceFav(), favorite.getPriceFav()) &&
              ObjectsCompat.equals(getSizeFav(), favorite.getSizeFav()) &&
              ObjectsCompat.equals(getCategoryFav(), favorite.getCategoryFav()) &&
              ObjectsCompat.equals(getUserId(), favorite.getUserId()) &&
              ObjectsCompat.equals(getCreatedAt(), favorite.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), favorite.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTitleFav())
      .append(getImageFav())
      .append(getPriceFav())
      .append(getSizeFav())
      .append(getCategoryFav())
      .append(getUserId())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Favorite {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("titleFav=" + String.valueOf(getTitleFav()) + ", ")
      .append("imageFav=" + String.valueOf(getImageFav()) + ", ")
      .append("priceFav=" + String.valueOf(getPriceFav()) + ", ")
      .append("sizeFav=" + String.valueOf(getSizeFav()) + ", ")
      .append("categoryFav=" + String.valueOf(getCategoryFav()) + ", ")
      .append("userID=" + String.valueOf(getUserId()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static TitleFavStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Favorite justId(String id) {
    return new Favorite(
      id,
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
      titleFav,
      imageFav,
      priceFav,
      sizeFav,
      categoryFav,
      userID);
  }
  public interface TitleFavStep {
    ImageFavStep titleFav(String titleFav);
  }
  

  public interface ImageFavStep {
    PriceFavStep imageFav(String imageFav);
  }
  

  public interface PriceFavStep {
    SizeFavStep priceFav(String priceFav);
  }
  

  public interface SizeFavStep {
    CategoryFavStep sizeFav(String sizeFav);
  }
  

  public interface CategoryFavStep {
    BuildStep categoryFav(String categoryFav);
  }
  

  public interface BuildStep {
    Favorite build();
    BuildStep id(String id);
    BuildStep userId(String userId);
  }
  

  public static class Builder implements TitleFavStep, ImageFavStep, PriceFavStep, SizeFavStep, CategoryFavStep, BuildStep {
    private String id;
    private String titleFav;
    private String imageFav;
    private String priceFav;
    private String sizeFav;
    private String categoryFav;
    private String userID;
    @Override
     public Favorite build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Favorite(
          id,
          titleFav,
          imageFav,
          priceFav,
          sizeFav,
          categoryFav,
          userID);
    }
    
    @Override
     public ImageFavStep titleFav(String titleFav) {
        Objects.requireNonNull(titleFav);
        this.titleFav = titleFav;
        return this;
    }
    
    @Override
     public PriceFavStep imageFav(String imageFav) {
        Objects.requireNonNull(imageFav);
        this.imageFav = imageFav;
        return this;
    }
    
    @Override
     public SizeFavStep priceFav(String priceFav) {
        Objects.requireNonNull(priceFav);
        this.priceFav = priceFav;
        return this;
    }
    
    @Override
     public CategoryFavStep sizeFav(String sizeFav) {
        Objects.requireNonNull(sizeFav);
        this.sizeFav = sizeFav;
        return this;
    }
    
    @Override
     public BuildStep categoryFav(String categoryFav) {
        Objects.requireNonNull(categoryFav);
        this.categoryFav = categoryFav;
        return this;
    }
    
    @Override
     public BuildStep userId(String userId) {
        this.userID = userId;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String titleFav, String imageFav, String priceFav, String sizeFav, String categoryFav, String userId) {
      super.id(id);
      super.titleFav(titleFav)
        .imageFav(imageFav)
        .priceFav(priceFav)
        .sizeFav(sizeFav)
        .categoryFav(categoryFav)
        .userId(userId);
    }
    
    @Override
     public CopyOfBuilder titleFav(String titleFav) {
      return (CopyOfBuilder) super.titleFav(titleFav);
    }
    
    @Override
     public CopyOfBuilder imageFav(String imageFav) {
      return (CopyOfBuilder) super.imageFav(imageFav);
    }
    
    @Override
     public CopyOfBuilder priceFav(String priceFav) {
      return (CopyOfBuilder) super.priceFav(priceFav);
    }
    
    @Override
     public CopyOfBuilder sizeFav(String sizeFav) {
      return (CopyOfBuilder) super.sizeFav(sizeFav);
    }
    
    @Override
     public CopyOfBuilder categoryFav(String categoryFav) {
      return (CopyOfBuilder) super.categoryFav(categoryFav);
    }
    
    @Override
     public CopyOfBuilder userId(String userId) {
      return (CopyOfBuilder) super.userId(userId);
    }
  }
  
}
