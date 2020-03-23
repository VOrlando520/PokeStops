package pokestops.andwhat5.config;

import com.google.gson.annotations.Expose;

public class ItemStruc {

	@Expose
	public String item;
	@Expose
	public double rarity;

	public ItemStruc(String item, double percentage) {
		this.item = item;
		this.rarity = percentage;
	}
}