package com.wishlist.schedular;

import com.test.database.PopulateDatabase;

public class UpdateCategorySchedular {

	public UpdateCategorySchedular(){
		PopulateDatabase pd = new PopulateDatabase();
		try {
			pd.populateCategory();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
