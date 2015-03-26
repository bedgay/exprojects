package hibernate.complete.discriminator;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PRODUCT")
public class ProductCategory extends Category {

}
