package wasidevestak.start;

import wasidevestak.dao.PersonDAOImp;
import wasidevestak.view.PrincipalWindow;

public class Main {
	
	public static void main(String[] args) {
		PersonDAOImp dao = PersonDAOImp.getInstance();
		new PrincipalWindow(dao);
		
	}
		
}
