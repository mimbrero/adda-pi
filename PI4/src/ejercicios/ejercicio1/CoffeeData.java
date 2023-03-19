package ejercicios.ejercicio1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CoffeeData {

	private final List<CoffeeType> types;
	private final List<CoffeeVariety> varieties;

	public CoffeeData(List<CoffeeType> types, List<CoffeeVariety> varieties) {
		this.types = types;
		this.varieties = varieties;
	}

	public static CoffeeData fromFile(Path path) throws IOException {
		Iterator<String> lineIterator = Files.lines(path)
				.filter(line -> !line.isBlank())
				.map(String::strip)
				.skip(1) // eliminamos la primera línea, // TIPOS
				.iterator();

		List<CoffeeType> types = new ArrayList<>();
		Map<String, CoffeeType> typesByName = new HashMap<>(); // necesario para obtener luego las variedades

		// por cada línea del archivo hasta que llegue a una línea que sea // VARIEDADES
		while (lineIterator.hasNext()) {
			String line = lineIterator.next();
			if (line.equals("// VARIEDADES"))
				break;

			CoffeeType type = CoffeeType.parse(line);
			types.add(type);
			typesByName.put(type.id(), type);
		}

		List<CoffeeVariety> varieties = new ArrayList<>();
		// por cada línea restante hasta el final
		while (lineIterator.hasNext()) {
			varieties.add(CoffeeVariety.parse(typesByName, lineIterator.next()));
		}
		
		return new CoffeeData(types, varieties);
	}

	public Integer getTypesNumber() {
		return this.types.size();
	}
	
	public Integer getVarietiesNumber() {
		return this.varieties.size();
	}
	
	public Integer getVarietyProfit(Integer variety) {
		return this.varieties.get(variety).profit();
	}
	
	public Integer getTypeQuantity(Integer type) {
		return this.types.get(type).quantity();
	}
	
	public Double getTypePercentage(Integer variety, Integer typeIndex) {
		CoffeeType type = this.types.get(typeIndex);
		return this.varieties.get(variety).composition().getOrDefault(type, 0D);
	}

	public List<CoffeeType> getTypes() {
		return types;
	}

	public List<CoffeeVariety> getVarieties() {
		return varieties;
	}
}
