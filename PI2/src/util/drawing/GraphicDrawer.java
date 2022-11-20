package util.drawing;

import java.nio.file.Path;
import java.util.function.Function;

import us.lsi.common.Pair;
import us.lsi.curvefitting.DataCurveFitting;
import util.curvefitting.GraficosAjuste;
import util.curvefitting.TipoAjuste;

public class GraphicDrawer {

	public void draw(String csvPath, String label, TipoAjuste tipoAjuste) {
		System.out.println(label);

		GraficosAjuste.show(csvPath, tipoAjuste, label);
	}
	
}
