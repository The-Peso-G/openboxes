package org.pih.warehouse.product

import java.util.Date;
import org.pih.warehouse.core.UnitOfMeasure;
import org.pih.warehouse.product.Category;

/**
 * An product is an instance of a generic.  For instance,
 * the product might be Ibuprofen, but the product is Advil 200mg
 *
 * We only track products and lots in the warehouse.  Generics help us
 * report on product availability across a generic product like Ibuprofen
 * no matter what size or shape it is.
 * 
 * We will just support "1 unit" for now.  Variations of products will 
 * eventually be stored as product variants (e.g. a 200 count bottle of 
 * 20 mg tablets vs a 50 count bottle of 20 mg tablets will both be stored 
 * as 20 mg tablets).  
 */
class Product implements Serializable {
	
	// Base product information 
	String name;				// A display name for the product
	String description;			// A description for the product
	String productCode;			// An internal product code	
	Category category;			// primary category
	Boolean coldChain = Boolean.FALSE;

	// Associations 
	List attributes;
	List categories;
	
	// Auditing
	Date dateCreated;
	Date lastUpdated;
	
	static transients = ["rootCategory"];
	
	static hasMany = [ categories : Category, attributes : ProductAttribute, tags : String ]
	
    static constraints = {
		name(nullable:true)
		description(nullable:true)
		productCode(nullable:true)		
		category(nullable:true)
		coldChain(nullable:true)
    }
	
	
	Category getRootCategory() { 
		Category rootCategory = new Category();
		rootCategory.categories = this.categories;
		return rootCategory;
	}
    
}

