package hibernate.complete.discriminator;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NEWS")
public class NewsCategory extends Category {

}
