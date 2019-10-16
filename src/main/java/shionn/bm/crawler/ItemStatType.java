package shionn.bm.crawler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ItemStatType {
	ilvl, //
	slot, //
	type, //
	strenght, //
	agility, //
	stamina, //
	intellect, //
	spirit, // //
	damage, //
	speed, //
	armor, //
	durability,
	attackpower, //
	attackcrit, //
	attackhit, //
	spellpower, //
	spellcrit, //
	spellhit, //
	spellhealpower, //
	unknown, //
	;//
	public static List<ItemStatType> allValues() {
		return Arrays.stream(values()).filter(type -> type != ItemStatType.unknown).collect(Collectors.toList());
	}
}
