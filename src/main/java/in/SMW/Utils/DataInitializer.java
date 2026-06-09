//package in.SMW.Utils;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import in.SMW.Entity.Role;
//import in.SMW.Repo.RoleRepo;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//	@Autowired
//	private RoleRepo roleRepo;
//
//	@Override
//	public void run(String... args) throws Exception {
//
//		if (roleRepo.findByRoleName("ROLE_ADMIN") == null) {
//
//			Role adminRole = Role.builder().roleName("ROLE_ADMIN").build();
//
//			roleRepo.save(adminRole);
//		}
//
//		if (roleRepo.findByRoleName("ROLE_CUSTOMER") == null) {
//
//			Role customerRole = Role.builder().roleName("ROLE_CUSTOMER").build();
//
//			roleRepo.save(customerRole);
//		}
//
//		System.out.println("Milky-Way Roles Initialized Successfully");
//	}
//}


package in.SMW.Utils;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import in.SMW.Entity.Category;
import in.SMW.Entity.Product;
import in.SMW.Entity.Role;
import in.SMW.Repo.CategoryRepo;
import in.SMW.Repo.ProductRepo;
import in.SMW.Repo.RoleRepo;


/**
 * DataInitializer is a Spring component that implements CommandLineRunner to initialize
 * the database with default roles, categories, and products when the application starts.
 */
@Component
public class DataInitializer
		implements CommandLineRunner {

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ProductRepo productRepo;

	@Override
	public void run(
			String... args)
			throws Exception {

		initializeRoles();

		initializeCategories();

		initializeProducts();

		System.out.println(
				"Milky-Way Initial Data Loaded Successfully");
	}

	private void initializeRoles() {

		if (roleRepo.findByRoleName(
				"ROLE_ADMIN") == null) {

			roleRepo.save(
					Role.builder()
							.roleName(
									"ROLE_ADMIN")
							.build());
		}

		if (roleRepo.findByRoleName(
				"ROLE_CUSTOMER") == null) {

			roleRepo.save(
					Role.builder()
							.roleName(
									"ROLE_CUSTOMER")
							.build());
		}
	}

	private void initializeCategories() {

		createCategoryIfNotExists(
				"Milk",
				"Fresh Farm Milk");

		createCategoryIfNotExists(
				"Curd",
				"Fresh Dairy Curd");

		createCategoryIfNotExists(
				"Paneer",
				"Fresh Soft Paneer");

		createCategoryIfNotExists(
				"Butter",
				"Pure Dairy Butter");

		createCategoryIfNotExists(
				"Ghee",
				"Pure Cow Ghee");

		createCategoryIfNotExists(
				"Cheese",
				"Premium Cheese Collection");
				
		createCategoryIfNotExists(
				"Cream",
				"Fresh Dairy Cream");
				
		createCategoryIfNotExists(
				"Yogurt",
				"Fresh Dairy Yogurt");		
	}

	private void initializeProducts() {

		Category milk =
				categoryRepo.findByCategoryName(
						"Milk");

		Category curd =
				categoryRepo.findByCategoryName(
						"Curd");

		Category paneer =
				categoryRepo.findByCategoryName(
						"Paneer");

		Category ghee =
				categoryRepo.findByCategoryName(
						"Ghee");
		
		Category cheese =
				categoryRepo.findByCategoryName(
						"Cheese");
					
		Category cream =
				categoryRepo.findByCategoryName(
						"Cream");
		
		Category yogurt =
				categoryRepo.findByCategoryName(
						"Yogurt");

		createProductIfNotExists(

				"Cow Milk 1L",

				"Fresh Cow Milk",

				new BigDecimal("55.00"),

				100,

				milk);

		createProductIfNotExists(

				"Buffalo Milk 1L",

				"Fresh Buffalo Milk",

				new BigDecimal("65.00"),

				100,

				milk);

		createProductIfNotExists(

				"Fresh Curd 500g",

				"Homemade Style Fresh Curd",

				new BigDecimal("45.00"),

				100,

				curd);

		createProductIfNotExists(

				"Paneer 250g",

				"Fresh Soft Paneer",

				new BigDecimal("90.00"),

				100,

				paneer);

		createProductIfNotExists(

				"Pure Ghee 500ml",

				"Traditional Cow Ghee",

				new BigDecimal("350.00"),

				100,

				ghee);
		
		createProductIfNotExists(

				"Cheese 500g",

				"Homemade Style Cheese",

				new BigDecimal("90.00"),

				100,

				cheese);	

		createProductIfNotExists(

				"Cream 100g",

				"Fresh Cream",

				new BigDecimal("60.00"),

				100,

				cream);	
				
		createProductIfNotExists(

				"Yogurt 50g",

				"Fresh Yogurt",

				new BigDecimal("40.00"),

				100,

				yogurt);		
	}

	private void createCategoryIfNotExists(

			String categoryName,

			String description) {

		if (!categoryRepo.existsByCategoryName(
				categoryName)) {

			categoryRepo.save(

					Category.builder()

							.categoryName(
									categoryName)

							.description(
									description)

							.build());
		}
	}

	private void createProductIfNotExists(

			String productName,

			String description,

			BigDecimal price,

			Integer quantity,

			Category category) {

		if (!productRepo.existsByProductName(
				productName)) {

			productRepo.save(

					Product.builder()

							.productName(
									productName)

							.description(
									description)

							.price(
									price)

							.quantity(
									quantity)

							.active(
									true)

							.imageUrl(
									"https://placeholder.com/product.jpg")

							.category(
									category)

							.build());
		}
	}
}

