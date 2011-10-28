package br.jabuti;

import br.jabuti.project.JabutiProject;

public class GetCoverageByProject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String prjFile = "/home/aendo/arquivos/QualipsoFolder/tools/JaBUTi/vending/proj.jbt";
		
		JabutiProject jbtProject = JabutiProject.reloadProj(prjFile, true);
		
		//System.out.println(jbtProject.coverage2TXT(""));
		CoverageDetails cd[] = CoverageDetails.generate(jbtProject);
		for (int i = 0; i < cd.length; i++) {
			System.out.println(cd[i].toString());
		}
	}
}
