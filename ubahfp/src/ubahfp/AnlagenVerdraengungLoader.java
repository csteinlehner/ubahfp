package ubahfp;

import processing.core.PApplet;

public class AnlagenVerdraengungLoader extends AnlagenLoader {

	public AnlagenVerdraengungLoader(PApplet p) {
		super(p);

	}
	@Override
	protected void addAnlage(String[][] csv, int j){
		try {
			AnlageVerdraengung a = new AnlageVerdraengung(Float.valueOf((csv[j][22]).trim())
					.floatValue(), Float.valueOf((csv[j][23]).trim())
					.floatValue());

			a.setData(csv[j][0], csv[j][1], csv[j][21],
					Integer.parseInt(csv[j][5]),
					Float.valueOf((csv[j][6]).trim()).floatValue(), 
					Float.valueOf((csv[j][17]).trim()).floatValue(),
					Float.valueOf((csv[j][18]).trim()).floatValue(),
					Integer.parseInt(csv[j][8]),
					Integer.parseInt(csv[j][9]),
					Integer.parseInt(csv[j][10]),
					Integer.parseInt(csv[j][11]),
					Integer.parseInt(csv[j][12]),
					Integer.parseInt(csv[j][13]),
					Integer.parseInt(csv[j][14]),
					Integer.parseInt(csv[j][15])
		);
			

			positions.add(a);
			// println(Float.valueOf((csv[j][23]).trim()).floatValue());
		} catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException in row " + (j + 1)
					+ " : " + nfe.getMessage());
		}

		
	}
	}