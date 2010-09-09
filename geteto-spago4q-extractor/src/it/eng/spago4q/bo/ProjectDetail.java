package it.eng.spago4q.bo;

import it.eng.spago4q.metadata.QpsProjectDetail;

public class ProjectDetail extends QpsProjectDetail {
	private static final long serialVersionUID = 1447463233967472709L;

	public ProjectDetail() {
		super();
	}

	public ProjectDetail(Integer id, String code, String detail) {
		super(id, code, detail);
	}
}
