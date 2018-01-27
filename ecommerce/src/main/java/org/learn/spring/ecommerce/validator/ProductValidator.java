package org.learn.spring.ecommerce.validator;

import org.learn.spring.ecommerce_backend.dto.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProductValidator implements Validator{

	@Override
	public boolean supports(Class<?> iClass) {
		return Product.class.equals(iClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product product = (Product) target;
		//whether file has been selected
		if(product.getFile() == null ||
				product.getFile().getOriginalFilename().equals("")) {
			errors.rejectValue("file", null, "Please select an image file to upload!");
			return;
		}
		
		if(!((product.getFile().getContentType().equals("image/jpeg")) ||
				(product.getFile().getContentType().equals("image/png")) ||
				(product.getFile().getContentType().equals("image/gif")))) {
			errors.rejectValue("file", null, "Please use only image file for upload!");
			return;
		}
			
	}

}
