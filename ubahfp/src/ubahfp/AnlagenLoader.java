package ubahfp;

import java.util.Vector;

import processing.core.PApplet;

public class AnlagenLoader {
	private Vector<Anlage> positions = new Vector<Anlage>();

	private PApplet p5;

	public AnlagenLoader(PApplet p) {
		p5 = p;

		initLoader("pl_dp_join_2008_02_latlong.csv");
	}

	public void initLoader(String csvPath) {
		String lines[] = p5.loadStrings(csvPath);
		String[][] csv;
		int csvWidth = 0;

		// calculate max width of csv file
		for (int i = 0; i < lines.length; i++) {
			String[] chars = PApplet.split(lines[i], ';');
			if (chars.length > csvWidth) {
				csvWidth = chars.length;
			}
		}

		// create csv array based on # of rows and columns in csv file
		csv = new String[lines.length][csvWidth];

		// parse values into 2d array
		for (int i = 0; i < lines.length; i++) {
			String[] temp = new String[lines.length];
			temp = PApplet.split(lines[i], ';');
			for (int j = 0; j < temp.length; j++) {
				csv[i][j] = temp[j];
			}
		}

		// fill positions vector
		for (int j = 1; j < csv.length; j++) {
			try {
				Anlage a = new Anlage(Float.valueOf((csv[j][22]).trim())
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
		p5.println(positions.size() + " Anlagen in positions");

	}

	public Vector<Anlage> getPositions() {
		return positions;
	}

}
