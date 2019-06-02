package hr.java.vjezbe.entitet;
import java.math.BigDecimal;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;


public interface Visokoskolska {

	BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit ispit[],Integer ocjenaObraneZavrsnogRada,Integer ocjenaZavrsnogRada);
	
	default BigDecimal odrediProsjekOcjenaNaIspitima(Ispit ispit[]) {
		Integer zbroj = 0;
		for(int i = 0;i<ispit.length;i++) {
			if(ispit[i].getOcjena()==1) {
				
			}
			zbroj =zbroj+ispit[i].getOcjena();
			
		}
		Integer pomProsjek = zbroj/ispit.length;
		BigDecimal prosjek = new BigDecimal(pomProsjek);
		
		return prosjek;
	};
	
	private Ispit[] filtrirajPolozeneIspite(Ispit ispit[]) {
		Ispit polozeniIspiti[] = new Ispit[ispit.length];
		for (int i = 0;i<ispit.length;i++) {
			int j=0;
			if(ispit[i].getOcjena()>0) {
				polozeniIspiti[j]=ispit[i];
				j++;
			}
		}
		return polozeniIspiti;
	}
	
	default Ispit[] fitrirajIspitePoStudentu(Ispit ispit[],Student stud) {
		Ispit ispitStudenta[] = new Ispit[ispit.length];
		int j= 0;
		for (int i = 0;i<ispit.length;i++) {
			if(ispit[i].getStudent().equals(stud)) {
				ispitStudenta[j]=ispit[i];
				j++;
			}
		}
		return ispitStudenta;
	}
	
}
