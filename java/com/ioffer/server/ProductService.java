package ie.ioffer.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProductService {
	
	public List<Product> getAllProducts()
	{
		// Creating two test sample product objects
		Product clutch = new Product("Clutch", 90.21,
				"Used clutch from a 1971 lada. Great condition.",
				"Base64 encoded image",
				(float)-12.31, (float)31.11,
				"Co. Mayo", "Gediminas Saparauskas", "Car Parts");
		
		Product lada = new Product("Lada", 4000.00,
				"Used lada manufactured in 1971",
				"Base64 encoded image",
				(float)98.11, (float)09.11,
				"Co. Dublin", "Peter Walsh", "Vehicles");
		
		// Adding the two test objects into the array list
		List<Product> productList = new ArrayList<>();
		productList.add(clutch);
		productList.add(lada);
		
		// Returning the list
		return productList;
	}
}
